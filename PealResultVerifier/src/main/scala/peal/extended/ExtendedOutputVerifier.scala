package peal.extended

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.verifier.Z3ModelExtractor
import peal.domain._
import peal.synthesis._
import peal.synthesis.AndCondition
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.OrCondition
import peal.synthesis.NotCondition
import peal.synthesis.LessThanThCondition
import peal.synthesis.analysis.Different
import peal.synthesis.analysis.Implies
import peal.synthesis.analysis.Satisfiable
import peal.synthesis.analysis.Equivalent
import peal.domain.operator.{Mul, Max, Min, Plus}
import peal.verifier.util.Rational

class ExtendedOutputVerifier(input: String) {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  def verifyModel(rawModel: String, analysisName: String): (ThreeWayBoolean, Set[String]) = {
    val I = Z3ModelExtractor.extractIUsingRational(rawModel)(analysisName)
    verifyModel(rawModel, analysisName, I, Set())
  }

  def verifyModel(rawModel: String, analysisName: String, I: Map[String, Either[Rational, ThreeWayBoolean]], reMappedPredicates: Set[String]): (ThreeWayBoolean, Set[String]) = {

    doAnalysis(analysisName, I, reMappedPredicates) match {
      case (PealBottom, s) =>

        var truthMapping = I
        val bottomPredicates = predicateNames.filterNot(truthMapping.contains).filterNot(s.contains)
        if (bottomPredicates.isEmpty) {
          return (PealBottom, s)
        }
        println("*** Trying again")
        (s + bottomPredicates.head).foreach {
          m =>
            println("set " + m + " to false")
            truthMapping += m -> Right(PealFalse)
        }
        verifyModel(rawModel, analysisName, truthMapping, s + bottomPredicates.head)
      case (r, s) => (r, s)
    }
  }

  def doAnalysis(analysisName: String, truthMapping: Map[String, Either[Rational, ThreeWayBoolean]], reMappedPredicates: Set[String]): (ThreeWayBoolean, Set[String]) = {
    analyses(analysisName) match {
      case AlwaysTrue(_, condName) =>
        if (truthMapping(condName) == Right(PealFalse)) {
          return (cert(conds(condName), truthMapping, truthMapping(condName).right.get), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be false but is not in " + analysisName)
      case AlwaysFalse(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return (cert(conds(condName), truthMapping, truthMapping(condName).right.get), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Satisfiable(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return (cert(conds(condName), truthMapping, truthMapping(condName).right.get), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Different(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (cert(conds(lhs), truthMapping, truthMapping(lhs).right.get) && cert(conds(rhs), truthMapping, truthMapping(rhs).right.get), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Equivalent(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (cert(conds(lhs), truthMapping, truthMapping(lhs).right.get) && cert(conds(rhs), truthMapping, truthMapping(rhs).right.get), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Implies(_, lhs, rhs) =>
        if (truthMapping(lhs) == Right(PealTrue) && truthMapping(rhs) == Right(PealFalse)) {
          val cert1: ThreeWayBoolean = cert(conds(lhs), truthMapping, truthMapping(lhs).right.get)
          val cert2: ThreeWayBoolean = cert(conds(rhs), truthMapping, truthMapping(rhs).right.get)
          println("lhs is expected to be " + truthMapping(lhs).right.get + ", rhs is expected to be " + truthMapping(rhs).right.get)
          println("cert1 " + cert1 + " cert2 " + cert2)
          return (cert1 && cert2, reMappedPredicates)
        }
        throw new RuntimeException(lhs + " should be true and " + rhs + " should be false, but are not in " + analysisName)
      case _ =>
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }

    throw new RuntimeException("shouldn't get here, no supported analysis specified")
  }

  def cert(cond: Condition, I: Map[String, Either[Rational, ThreeWayBoolean]], v: ThreeWayBoolean): ThreeWayBoolean = {
    try {
      cond match {
        case NotCondition(c) => cert(conds(c), I, !v)
        case AndCondition(lhs, rhs) =>
          if (v == PealTrue) {
            cert(conds(lhs), I, v) && cert(conds(rhs), I, v)
          }
          else {
            cert(conds(lhs), I, v) || cert(conds(rhs), I, v)
          }
        case OrCondition(lhs, rhs) =>
          if (v == PealTrue) {
            cert(conds(lhs), I, v) || cert(conds(rhs), I, v)
          }
          else {
            cert(conds(lhs), I, v) && cert(conds(rhs), I, v)
          }
        case c: LessThanThCondition =>
          val lhsValue: BigDecimal = certValue(Right(c.lhs), I)
          val rhsValue: BigDecimal = certValue(c.rhs, I)
          println("LessThanThCondition: lhs " + lhsValue + " <= rhs " + rhsValue + ", v " + v)
          if (v == PealTrue) {
            ThreeWayBooleanObj.from(lhsValue <= rhsValue)
          } else {
            ThreeWayBooleanObj.from(rhsValue < lhsValue)
          }
        case c: GreaterThanThCondition =>
          val lhsValue: BigDecimal = certValue(Right(c.lhs), I)
          val rhsValue: BigDecimal = certValue(c.rhs, I)
          println("GreaterThanThCondition: lhs " + lhsValue + " > rhs " + rhsValue + ", v " + v)
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

  //if certValue is not BigDecimal, throw exception to be handled in the upper layer
  private def certValue(pSet: Either[BigDecimal, PolicySet], I: Map[String, Either[Rational, ThreeWayBoolean]]): BigDecimal = {

    def eval(e: Multiplier): Rational = {
      e.name match {
        case "" => Rational(e.multiplier.toString())
        case _ if I(e.name).isLeft => Rational(e.multiplier.toString()) * I(e.name).fold(s => s, vf => throw new RuntimeException("illegal variable format"))
        case _ => throw new RuntimeException("Invalid eval case")
      }
    }

    def evaluateFormula(vf: VariableFormula): Rational = {
      vf.operations.foldLeft(Rational("0"))((l, r) => l + eval(r))
    }

    def extractScore(pSet: PolicySet): BigDecimal = {

      def trueScore(score: Score, name: String): Rational = {
        println("score is " + score)
        score.scoreRange match {
          case None => score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f))
          case Some(_) => score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f)) + I(name).fold(s => s, vf => throw new RuntimeException("illegal variable format"))
        }
      }

      pSet match {
        case BasicPolicySet(pol, name) => extractScore(pol)
        case Pol(rules, op, score, name) =>
          if (rules.exists(r => I.getOrElse(r.q.name, Right(PealBottom)).fold(score => PealBottom, pealBool => pealBool) == PealBottom)) {
            //should log
            throw new RuntimeException("PealBottom reached in certValue because some predicates are not defined in I")
          }
          else if (!rules.exists(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue)) {
            trueScore(score, name + "_default_U").value
          }
          else {

            val okRules = rules.filter(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue)
            println("okRules are: " + okRules + " op is " + op)
            val decimal: BigDecimal = op match {
              //TODO need to deal with range here
              case Min => okRules.foldLeft(Rational("1"))((acc, rule) => acc.min(trueScore(rule.score, name + "_" + rule.q.name + "_U"))).value
              case Max => okRules.foldLeft(Rational("0"))((acc, rule) => acc.max(rule.score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f)))).value
              case Plus => okRules.foldLeft(Rational("0"))((acc, rule) => acc + rule.score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f))).value
              case Mul => okRules.foldLeft(Rational("1"))((acc, rule) => acc * rule.score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f))).value
            }
            println("op X: " + decimal)
            decimal
          }
        case MaxPolicySet(lhs, rhs, n) => extractScore(lhs).max(extractScore(rhs))
        case MinPolicySet(lhs, rhs, n) => extractScore(lhs).min(extractScore(rhs))
        case PlusPolicySet(lhs, rhs, n) => extractScore(lhs) + extractScore(rhs)
        case MulPolicySet(lhs, rhs, n) => extractScore(lhs) * extractScore(rhs)
      }
    }

    pSet.fold(score => score, pSet => extractScore(pSet))
  }
}
