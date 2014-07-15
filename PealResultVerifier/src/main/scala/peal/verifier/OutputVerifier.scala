package peal.verifier

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.domain.operator.{Max, Min, Mul, Plus}
import peal.synthesis._
import peal.synthesis.analysis.{AlwaysFalse, AlwaysTrue, Different, Equivalent, Implies, Satisfiable}
import peal.util.ConsoleLogger
import peal.verifier.util.ScoreEvaluator
import scala.collection.JavaConversions._


import scala.collection.JavaConversions._
import scala.collection.mutable

class OutputVerifier(input: String) {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val pols = pealProgramParser.pols
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct


  def verifyModel(rawModel: String, analysisName: String): (ThreeWayBoolean, Set[String], Map[String, Either[Rational, ThreeWayBoolean]]) = {
    val initialI = Z3ModelExtractor.extractIUsingRational(rawModel)(analysisName)

    def checkPols(valueMap: Map[String, Either[Rational, ThreeWayBoolean]], remap: Set[String]): (Map[String, Either[Rational, ThreeWayBoolean]], Set[String]) = {
      try {
        val newEntries: mutable.Map[String, Either[Rational, ThreeWayBoolean]] = for {
          (name, pol) <- pols
        } yield {
          //TODO need to compare new entries to *_score
          val polValue = certPol(pol)(valueMap)
          valueMap.get(name + "_score") match {
            case None =>
            case Some(x) =>
              val valueInI = x.fold(r => r, b => throw new RuntimeException("checkPols: shouldn't get here"))
              ConsoleLogger.log1("Comparing " + name + " and " + name +"_score")
              if (valueInI != polValue) {
                throw new RuntimeException("pol certification failed, " + name + " came out to be " + polValue + "but should be " + x)
              }
          }
          (name, Left[Rational, ThreeWayBoolean](polValue))
        }

        (newEntries.toMap, remap)
      } catch {
        case e: RuntimeException =>
          val bottomPredicates = predicateNames.filterNot(valueMap.contains).filterNot(remap.contains)
          if (bottomPredicates.isEmpty) {
            throw e
          }
          val newRemap = remap + bottomPredicates.head
          checkPols(valueMap ++ newRemap.map((_, Right(PealFalse))), newRemap)
      }
    }

    val (newPolicyScoreEntries, remappedPredicates) = checkPols(initialI, Set())

    implicit val I = initialI ++ newPolicyScoreEntries
    (doAnalysis(analysisName), remappedPredicates, newPolicyScoreEntries)
  }

  def doAnalysis(analysisName: String)(implicit truthMapping: Map[String, Either[Rational, ThreeWayBoolean]]): ThreeWayBoolean = {
    ConsoleLogger.log1("I received: " + truthMapping)
    //TODO do vacity check on all conds
    //show above analysis results
    //title: Result of vacuity analyses of all declared conditions
    analyses(analysisName) match {
      case AlwaysTrue(_, condName) =>
        if (truthMapping(condName) == Right(PealFalse)) {
          return (cert(conds(condName), truthMapping(condName).right.get)(truthMapping))
        }
        throw new RuntimeException(condName + " should be false but is not in " + analysisName)
      case AlwaysFalse(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return (cert(conds(condName), truthMapping(condName).right.get)(truthMapping))
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Satisfiable(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return (cert(conds(condName), truthMapping(condName).right.get)(truthMapping))
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Different(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (cert(conds(lhs), truthMapping(lhs).right.get)(truthMapping) && cert(conds(rhs), truthMapping(rhs).right.get)(truthMapping))
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Equivalent(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (cert(conds(lhs), truthMapping(lhs).right.get)(truthMapping) && cert(conds(rhs), truthMapping(rhs).right.get)(truthMapping))
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Implies(_, lhs, rhs) =>
        if (truthMapping(lhs) == Right(PealTrue) && truthMapping(rhs) == Right(PealFalse)) {
          val cert1: ThreeWayBoolean = cert(conds(lhs), truthMapping(lhs).right.get)(truthMapping)
          val cert2: ThreeWayBoolean = cert(conds(rhs), truthMapping(rhs).right.get)(truthMapping)
          ConsoleLogger.log2("lhs is expected to be " + truthMapping(lhs).right.get + ", rhs is expected to be " + truthMapping(rhs).right.get)
          ConsoleLogger.log2("cert1 " + cert1 + " cert2 " + cert2)
          return (cert1 && cert2)
        }
        throw new RuntimeException(lhs + " should be true and " + rhs + " should be false, but are not in " + analysisName)
      case _ =>
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }

    throw new RuntimeException("shouldn't get here, no supported analysis specified")
  }

  def cert(cond: Condition, v: ThreeWayBoolean)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]]): ThreeWayBoolean = {
    try {
      cond match {
        case NotCondition(c) => cert(conds(c), !v)(I)
        case AndCondition(lhs, rhs) =>
          if (v == PealTrue) {
            cert(conds(lhs), v)(I) && cert(conds(rhs), v)(I)
          }
          else {
            cert(conds(lhs), v)(I) || cert(conds(rhs), v)(I)
          }
        case OrCondition(lhs, rhs) =>
          if (v == PealTrue) {
            cert(conds(lhs), v)(I) || cert(conds(rhs), v)(I)
          }
          else {
            cert(conds(lhs), v)(I) && cert(conds(rhs), v)(I)
          }
        case c: LessThanThCondition =>
          val lhsValue: BigDecimal = certValue(Right(c.lhs))(I)
          val rhsValue: BigDecimal = certValue(c.rhs)(I)
          ConsoleLogger.log1("LessThanThCondition: lhs " + lhsValue + " <= rhs " + rhsValue + ", v " + v)
          if (v == PealTrue) {
            ThreeWayBooleanObj.from(lhsValue <= rhsValue)
          } else {
            ThreeWayBooleanObj.from(rhsValue < lhsValue)
          }
        case c: GreaterThanThCondition =>
          val lhsValue: BigDecimal = certValue(Right(c.lhs))(I)
          val rhsValue: BigDecimal = certValue(c.rhs)(I)
          ConsoleLogger.log1("GreaterThanThCondition: lhs " + lhsValue + " > rhs " + rhsValue + ", v " + v)
          if (v == PealTrue) {
            ThreeWayBooleanObj.from(rhsValue < lhsValue)
          } else {
            ThreeWayBooleanObj.from(lhsValue <= rhsValue)
          }
        case c: PredicateCondition => I(c.predicateName).right.getOrElse(PealBottom) === v
      }
    } catch {
      case e: RuntimeException =>
        e.printStackTrace()
        PealBottom
    }
  }

  private def certPol(pSet: PolicySet)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]]): Rational = pSet match {
    case Pol(rules, op, score, policyName) =>
      //TODO can forget about all these
      if (rules.exists(r => I.getOrElse(r.q.name, Right(PealBottom)).fold(score => PealBottom, pealBool => pealBool) == PealBottom)) {
        //should log
        val notDefined = rules.filter(r => I.getOrElse(r.q.name, Right(PealBottom)).fold(score => PealBottom, pealBool => pealBool) == PealBottom)
        throw new RuntimeException("PealBottom reached in certValue because some predicates are not defined in I: " + notDefined + " in " + policyName + " " + rules)
      }
      else if (!rules.exists(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue)) {
        ScoreEvaluator.trueScore(score, policyName + "_default_U")
      }
      else {
        val okScores = rules.filter(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue).map(r => ScoreEvaluator.trueScore(r.score, policyName + "_" + r.q.name + "_U"))
        ConsoleLogger.log2("okScores are: " + okScores + " op is " + op)
        val decimal = op match {
          case Min => okScores.reduceLeft((acc, score) => acc.min(score))
          case Max => okScores.reduceLeft((acc, score) => acc.max(score))
          case Plus => okScores.reduceLeft((acc, score) => acc + score)
          case Mul => okScores.reduceLeft((acc, score) => acc * score)
        }
        ConsoleLogger.log2("op X " + op + " " + policyName + ": " + (for (o <- okScores) yield o).mkString(" ") + ": " + decimal)
        decimal
      }

  }

  //if certValue is not BigDecimal, throw exception to be handled in the upper layer
  private def certValue(pSet: Either[BigDecimal, PolicySet])(implicit I: Map[String, Either[Rational, ThreeWayBoolean]]): BigDecimal = {

    def extractScore(pSet: PolicySet): Rational = {

      val out = pSet match {
        case BasicPolicySet(pol, name) => extractScore(pol)
        case Pol(_, _, _, name) =>
          I.get(name) match {
            case Some(x) if x.isLeft => x.fold(r => r, tw => throw new RuntimeException("should be a rational but is not"))
            case None => throw new RuntimeException("certValue.extractScore(), Pol " + name + " should have been set up by now") //certPol(pols(name))
          }
        case MaxPolicySet(lhs, rhs, n) => extractScore(lhs).max(extractScore(rhs))
        case MinPolicySet(lhs, rhs, n) => extractScore(lhs).min(extractScore(rhs))
        case PlusPolicySet(lhs, rhs, n) => extractScore(lhs) + extractScore(rhs)
        case MulPolicySet(lhs, rhs, n) => extractScore(lhs) * extractScore(rhs)
      }

      ConsoleLogger.log1(pSet.getPolicySetName + " is " + out + " (" + out.value + ")")

      out
    }

    pSet.fold(score => score, pSet => extractScore(pSet).value)
  }
}
