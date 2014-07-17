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
import scala.util.{Failure, Success, Try}

//TODO new certification strategy
//a. certify all policies. In this stage, if any certification of policies b fails due to bottom predicates, we simply set I(b) to bottom.
//
//b. perform certification according to our notes (all pseucode stays the same), the only change is with certValue() modified to:
//
// certValue(pSet,I) {
// if (pSet == score) { return I(score); }
// elseif (pSet == pol) { return I(pSet)}
//
//c. perform cert(cond, I, v), if it returns bottom, set one bottom predicate to false and go to #a. Keep the predicate remapped map.
class OutputVerifier(input: String) {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val pols = pealProgramParser.pols
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct


  def verifyModel(rawModel: String, analysisName: String): (ThreeWayBoolean, Set[String], Map[String, Either[Rational, ThreeWayBoolean]]) = {
    println("############ analysis " + analysisName)

    val initialI = Z3ModelExtractor.extractIUsingRational(rawModel)(analysisName)
    verifyModel(analysisName, initialI, Set())
  }

  def verifyModel(analysisName: String, I: Map[String, Either[Rational, ThreeWayBoolean]], remap: Set[String]): (ThreeWayBoolean, Set[String], Map[String, Either[Rational, ThreeWayBoolean]]) = {
    def checkPols(valueMap: Map[String, Either[Rational, ThreeWayBoolean]]) = {
      //TODO need fixing, if fails then should carry on
      val newEntries: mutable.Map[String, Either[Rational, ThreeWayBoolean]] = for {
        (name, pol) <- pols if Try(certPol(pol)(valueMap)).isSuccess
      } yield {
        val polValue = certPol(pol)(valueMap)
        valueMap.get(name + "_score") match {
          case None =>
          case Some(x) =>
            val valueInI = x.fold(r => r, b => throw new RuntimeException("checkPols: shouldn't get here"))
            ConsoleLogger.log1("Comparing " + name + " and " + name + "_score")
            if (valueInI != polValue) {
              throw new RuntimeException("pol certification failed, " + name + " came out to be " + polValue + "but should be " + x)
            }
        }
        (name, Left[Rational, ThreeWayBoolean](polValue))
      }

      newEntries.toMap
    }

    val analysedResult: Try[(ThreeWayBoolean, Map[String, Either[Rational, ThreeWayBoolean]])] = for {
      checkedPol <- Try(checkPols(I))
      analysed <- Try(doAnalysis(analysisName)(I ++ checkedPol))
    } yield (analysed, checkedPol)

    val out = analysedResult match {
      case Success(v) =>
        println("success")
        (v._1, remap, v._2)
      case Failure(e) =>
        println("*** analysis failed, try again")
        val bottomPredicates = predicateNames.filterNot(I.contains).filterNot(remap.contains)
        if (bottomPredicates.isEmpty) {
          throw e
        } else {
          val newRemap = remap + bottomPredicates.head
          println("*** remap\n" + newRemap)

          verifyModel(analysisName, I ++ newRemap.map((_, Right(PealFalse))), newRemap)
        }
    }

    println("$$$ returning " + out)

    out
  }

  def doAnalysis(analysisName: String)(implicit truthMapping: Map[String, Either[Rational, ThreeWayBoolean]]): ThreeWayBoolean = {
    ConsoleLogger.log1("I received: " + truthMapping)
    analyses(analysisName) match {
      case AlwaysTrue(_, condName) =>
        if (truthMapping(condName) == Right(PealFalse)) {
          return cert(conds(condName), truthMapping(condName).right.get)(truthMapping)
        }
        throw new RuntimeException(condName + " should be false but is not in " + analysisName)
      case AlwaysFalse(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return cert(conds(condName), truthMapping(condName).right.get)(truthMapping)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Satisfiable(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return cert(conds(condName), truthMapping(condName).right.get)(truthMapping)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Different(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return cert(conds(lhs), truthMapping(lhs).right.get)(truthMapping) && cert(conds(rhs), truthMapping(rhs).right.get)(truthMapping)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Equivalent(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return cert(conds(lhs), truthMapping(lhs).right.get)(truthMapping) && cert(conds(rhs), truthMapping(rhs).right.get)(truthMapping)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Implies(_, lhs, rhs) =>
        if (truthMapping(lhs) == Right(PealTrue) && truthMapping(rhs) == Right(PealFalse)) {
          val cert1: ThreeWayBoolean = cert(conds(lhs), truthMapping(lhs).right.get)(truthMapping)
          val cert2: ThreeWayBoolean = cert(conds(rhs), truthMapping(rhs).right.get)(truthMapping)
          ConsoleLogger.log2("lhs is expected to be " + truthMapping(lhs).right.get + ", rhs is expected to be " + truthMapping(rhs).right.get)
          ConsoleLogger.log2("cert1 " + cert1 + " cert2 " + cert2)
          return cert1 && cert2
        }
        throw new RuntimeException(lhs + " should be true and " + rhs + " should be false, but are not in " + analysisName)
      case _ =>
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }

    throw new RuntimeException("shouldn't get here, no supported analysis specified")
  }

  def cert(cond: Condition, v: ThreeWayBoolean)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]]): ThreeWayBoolean = {
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
  }

  private def certPol(pSet: PolicySet)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]]): Rational = pSet match {
    case Pol(rules, op, score, policyName) =>
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
