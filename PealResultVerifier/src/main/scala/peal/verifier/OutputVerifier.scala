package peal.verifier

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.domain.operator.{Max, Min, Mul, Plus}
import peal.domain.z3.{Unknown, Unsat, Sat}
import peal.synthesis._
import peal.synthesis.analysis.{AlwaysFalse, AlwaysTrue, Different, Equivalent, Implies, Satisfiable}
import peal.util.ConsoleLogger
import peal.verifier.util.ScoreEvaluator
import scala.collection.JavaConversions._


import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.util.{Failure, Success, Try}

case class OutputVerifier(input: String) {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val pols = pealProgramParser.pols
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  implicit private def purgeUnderscore(x: Multiplier): String = {
    ConsoleLogger.log1("purgeUnderscore called for " + x.toNaturalExpression)

    val out = x.name.contains("_score") match {
      case true => x.name.dropRight("_score".length)
      case _ => x.name
    }

    ConsoleLogger.log1("changing " + x.name + " to " + out)
    out
  }

  def verifyModel(rawModel: String, analysisName: String): (ThreeWayBoolean, Set[String], Map[String, Either[Rational, ThreeWayBoolean]]) = {
    ConsoleLogger.log("########## " + analysisName)
    val z3Model = Z3ModelExtractor.extractIAndStatusUsingRational(rawModel)(analysisName)

    z3Model._1 match {
      case Sat => val out = certify(analysisName, z3Model._2, Set(), Map()); (out._1, out._2, out._3)
      case Unsat => (PealTrue, Set(), Map()) //if the model is Unsat, don't perform certification
      case Unknown => (PealBottom, Set(), Map())
    }
  }

  def certify(analysisName: String, I: Map[String, Either[Rational, ThreeWayBoolean]], remap: Set[String], previouslyCheckedPolicies: Map[String, Either[Rational, ThreeWayBoolean]]): (ThreeWayBoolean, Set[String], Map[String, Either[Rational, ThreeWayBoolean]]) = {

    def certifyAllPolicyScores(implicit localI: Map[String, Either[Rational, ThreeWayBoolean]]) = {

      val newEntries: mutable.Map[String, Either[Rational, ThreeWayBoolean]] = for {
        (name, pol) <- pols
        polValue = Try(certPolicy(pol))
        if polValue.isSuccess
      } yield {
        localI.get(name + "_score") match {
          case None =>
          case Some(x) =>
            val valueInI = x.fold(r => r, b => throw new RuntimeException("certifyAllPolicyScores: shouldn't get here"))
            if (valueInI != polValue.get) {
              throw new RuntimeException("pol certification failed, " + name + " came out to be " + polValue + "but should be " + x)
            }
        }
        ConsoleLogger.log1("certifyAllPolicyScores:adding " + name + " -> " + polValue.get)

        (name, Left[Rational, ThreeWayBoolean](polValue.get))
      }

      newEntries.toMap
    }

    val analysedResult: Try[(ThreeWayBoolean, Map[String, Either[Rational, ThreeWayBoolean]])] = for {
      mapOfCheckedPolScores <- Try(certifyAllPolicyScores(I))
      analysed <- Try(doAnalysis(analysisName)(I ++ mapOfCheckedPolScores, purgeUnderscore))
    } yield (analysed, mapOfCheckedPolScores)

    analysedResult match {
      case Success((threeWayBoolean, setOfCheckedPolicies)) =>
        ConsoleLogger.log1("*** setOfCheckedPolicies: " + setOfCheckedPolicies)

        if (threeWayBoolean == PealBottom) {
          val bottomPredicates = predicateNames.filterNot(I.contains).filterNot(remap.contains)
          if (setOfCheckedPolicies != previouslyCheckedPolicies) {
            certify(analysisName, I ++ setOfCheckedPolicies ++ remap.map((_, Right(PealFalse))), remap, setOfCheckedPolicies)
          }
          else if (bottomPredicates.isEmpty) {
            (threeWayBoolean, remap, setOfCheckedPolicies)
          } else {
            val newRemap = remap + bottomPredicates.head
            ConsoleLogger.log1("*** Setting these to false: " + newRemap)
            certify(analysisName, I ++ setOfCheckedPolicies ++ newRemap.map((_, Right(PealFalse))), newRemap, setOfCheckedPolicies)
          }
        }
        else {
          (threeWayBoolean, remap, setOfCheckedPolicies)
        }
      case Failure(e) => throw e
    }
  }

  def doAnalysis(analysisName: String)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String): ThreeWayBoolean = {
    ConsoleLogger.log1("In doAnalysis, I is " + I)
    analyses(analysisName) match {
      //These I accesses are specific for conditions
      case AlwaysTrue(_, condName) =>
        if (I(condName) == Right(PealFalse)) {
          return certCond(conds(condName), I(condName).right.get)
        }
        throw new RuntimeException(condName + " should be false but is not in " + analysisName)
      case AlwaysFalse(_, condName) =>
        if (I(condName) == Right(PealTrue)) {
          return certCond(conds(condName), I(condName).right.get)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Satisfiable(_, condName) =>
        if (I(condName) == Right(PealTrue)) {
          return certCond(conds(condName), I(condName).right.get)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Different(_, lhs, rhs) =>
        if (I.get(lhs) != I.get(rhs)) {
          return certCond(conds(lhs), I(lhs).right.get) && certCond(conds(rhs), I(rhs).right.get)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Equivalent(_, lhs, rhs) =>
        if (I.get(lhs) != I.get(rhs)) {
          return certCond(conds(lhs), I(lhs).right.get) && certCond(conds(rhs), I(rhs).right.get)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Implies(_, lhs, rhs) =>
        if (I(lhs) == Right(PealTrue) && I(rhs) == Right(PealFalse)) {
          val cert1: ThreeWayBoolean = certCond(conds(lhs), I(lhs).right.get)
          val cert2: ThreeWayBoolean = certCond(conds(rhs), I(rhs).right.get)
          ConsoleLogger.log2("lhs is expected to be " + I(lhs).right.get + ", rhs is expected to be " + I(rhs).right.get)
          ConsoleLogger.log2("cert1 " + cert1 + " cert2 " + cert2)
          return cert1 && cert2
        }
        throw new RuntimeException(lhs + " should be true and " + rhs + " should be false, but are not in " + analysisName)
      case _ =>
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }

    throw new RuntimeException("shouldn't get here, no supported analysis specified")
  }

  def certCond(cond: Condition, v: ThreeWayBoolean)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String): ThreeWayBoolean = {
    try {
      cond match {
        case NotCondition(c) => certCond(conds(c), !v)
        case AndCondition(lhs, rhs) =>
          if (v == PealTrue) {
            certCond(conds(lhs), v) && certCond(conds(rhs), v)
          }
          else {
            certCond(conds(lhs), v) || certCond(conds(rhs), v)
          }
        case OrCondition(lhs, rhs) =>
          if (v == PealTrue) {
            certCond(conds(lhs), v) || certCond(conds(rhs), v)
          }
          else {
            certCond(conds(lhs), v) && certCond(conds(rhs), v)
          }
        case c: LessThanThCondition =>
          val lhsValue: BigDecimal = certPSet(Right(c.lhs))
          val rhsValue: BigDecimal = certPSet(c.rhs)
          ConsoleLogger.log1("LessThanThCondition: lhs " + lhsValue + " <= rhs " + rhsValue + ", v " + v)
          if (v == PealTrue) {
            ThreeWayBooleanObj.from(lhsValue <= rhsValue)
          } else {
            ThreeWayBooleanObj.from(rhsValue < lhsValue)
          }
        case c: GreaterThanThCondition =>
          val lhsValue: BigDecimal = certPSet(Right(c.lhs))
          val rhsValue: BigDecimal = certPSet(c.rhs)
          ConsoleLogger.log1("GreaterThanThCondition: lhs " + lhsValue + " > rhs " + rhsValue + ", v " + v)
          if (v == PealTrue) {
            ThreeWayBooleanObj.from(rhsValue < lhsValue)
          } else {
            ThreeWayBooleanObj.from(lhsValue <= rhsValue)
          }
        case c: PredicateCondition => I(c.predicateName).right.getOrElse(PealBottom) === v
      }
    } catch {
      case e: Exception =>
        //        e.printStackTrace()
        PealBottom
    }
  }

  private def certPolicy(pSet: PolicySet)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String): Rational = {
    ConsoleLogger.log1("### certPolicy " + pSet.getPolicySetName)
    val out = pSet match {
      case Pol(rules, op, score, policyName) =>
        //These are getting rule specific I values, so they are ok
        if (rules.exists(r => I.getOrElse(r.q.name, Right(PealBottom)).fold(score => PealBottom, pealBool => pealBool) == PealBottom)) {
          //should log
          val notDefined = rules.filter(r => I.getOrElse(r.q.name, Right(PealBottom)).fold(score => PealBottom, pealBool => pealBool) == PealBottom)
          throw new RuntimeException("PealBottom reached in certPSet because some predicates are not defined in I: " + notDefined + " in " + policyName + " " + rules)
        }
        else if (!rules.exists(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue)) {
          ScoreEvaluator.trueScore(score, policyName + "_default_U").get
        }
        else {
          val okScores = rules.filter(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue).map(r => ScoreEvaluator.trueScore(r.score, policyName + "_" + r.q.name + "_U").get)
          ConsoleLogger.log2("okScores are: " + okScores + " op is " + op)
          val rational = op match {
            case Min => okScores.reduceLeft((acc, score) => acc.min(score))
            case Max => okScores.reduceLeft((acc, score) => acc.max(score))
            case Plus => okScores.reduceLeft((acc, score) => acc + score)
            case Mul => okScores.reduceLeft((acc, score) => acc * score)
          }
          ConsoleLogger.log2("op X " + op + " " + policyName + ": " + (for (o <- okScores) yield o).mkString(" ") + ": " + rational)
          rational
        }
    }

    ConsoleLogger.log1(pSet.getPolicySetName + " is " + out)
    out
  }

  private def certPSet(pSet: Either[BigDecimal, PolicySet])(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String): BigDecimal = {

    def extractScore(pSet: PolicySet): Rational = {

      val out = pSet match {
        case BasicPolicySet(pol, name) => extractScore(pol)
        case Pol(_, _, _, name) =>
          I.get(name) match {
            //if certPSet is not BigDecimal, or hasn't been set up yet, throw exception to be handled in the upper layer
            case Some(x) if x.isLeft => x.fold(r => r, tw => throw new RuntimeException("should be a rational but is not"))
            case None => throw new RuntimeException("certPSet.extractScore(), Pol " + name + " is not set up")
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
