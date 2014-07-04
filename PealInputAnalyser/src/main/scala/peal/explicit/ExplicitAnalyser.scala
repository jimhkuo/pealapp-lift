package peal.explicit

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.domain.operator.{Max, Min, Mul, Plus}
import peal.synthesis._
import peal.synthesis.analysis._
import peal.verifier.Z3ModelExtractor

import scala.collection.JavaConversions._


class ExplicitAnalyser(input: String) {

  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  def verifyModel(rawModel: String, analysisName: String): (ThreeWayBoolean, Set[String]) = {
    val truthMapping = Z3ModelExtractor.extractI(rawModel)(analysisName)
    verifyModel(rawModel, analysisName, truthMapping, Set())
  }

  def verifyModel(rawModel: String, analysisName: String, I: Map[String, Either[BigDecimal, ThreeWayBoolean]], reMappedPredicates: Set[String]): (ThreeWayBoolean, Set[String]) = {

    doAnalysis(analysisName, I, reMappedPredicates) match {
      case (PealBottom, s) =>

        var truthMapping = I
        val bottomPredicates = predicateNames.filterNot(truthMapping.contains).filterNot(s.contains)
        if (bottomPredicates.isEmpty) {
          return (PealBottom, s)
        }
        (s + bottomPredicates.head).foreach(truthMapping += _ -> Right(PealFalse))
        verifyModel(rawModel, analysisName, truthMapping, s + bottomPredicates.head)
      case (r, s) => (r, s)
    }
  }

  def doAnalysis(analysisName: String, truthMapping: Map[String, Either[BigDecimal, ThreeWayBoolean]], reMappedPredicates: Set[String]): (ThreeWayBoolean, Set[String]) = {
    analyses(analysisName) match {
      case AlwaysTrue(_, condName) =>
        if (truthMapping(condName) == Right(PealFalse)) {
          return (verify(conds(condName), truthMapping, truthMapping(condName).right.get), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be false but is not in " + analysisName)
      case AlwaysFalse(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return (verify(conds(condName), truthMapping, truthMapping(condName).right.get), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Satisfiable(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return (verify(conds(condName), truthMapping, truthMapping(condName).right.get), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Different(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (verify(conds(lhs), truthMapping, truthMapping(lhs).right.get) && verify(conds(rhs), truthMapping, truthMapping(rhs).right.get), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Equivalent(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (verify(conds(lhs), truthMapping, truthMapping(lhs).right.get) && verify(conds(rhs), truthMapping, truthMapping(rhs).right.get), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Implies(_, lhs, rhs) =>
        if (truthMapping(lhs) == Right(PealTrue) && truthMapping(rhs) == Right(PealFalse)) {
          return (verify(conds(lhs), truthMapping, truthMapping(lhs).right.get) && verify(conds(rhs), truthMapping, truthMapping(rhs).right.get), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " should be true and " + rhs + " should be false, but are not in " + analysisName)
      case _ =>
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }

    throw new RuntimeException("shouldn't get here, no supported analysis specified")
  }

  def verify(cond: Condition, I: Map[String, Either[BigDecimal, ThreeWayBoolean]], v: ThreeWayBoolean): ThreeWayBoolean = {
    cond match {
      case NotCondition(c) => verify(conds(c), I, !v)
      case AndCondition(lhs, rhs) =>
        if (v == PealTrue) {
          verify(conds(lhs), I, v) && verify(conds(rhs), I, v)
        }
        else {
          verify(conds(lhs), I, v) || verify(conds(rhs), I, v)
        }
      case OrCondition(lhs, rhs) =>
        if (v == PealTrue) {
          verify(conds(lhs), I, v) || verify(conds(rhs), I, v)
        }
        else {
          verify(conds(lhs), I, v) && verify(conds(rhs), I, v)
        }
      case c: LessThanThCondition =>
        val reversedCond = new GreaterThanThCondition(c.lhs, Left(c.getTh))
        verify(reversedCond, I, !v)
      case c: GreaterThanThCondition =>
        c.lhs match {
          case s: BasicPolicySet =>
            //this is ok since if rhs is not left then it should fail
            v === evalPol(s.pol, c.rhs.left.get, I)
          case s: MaxPolicySet =>
            val lhsCond = new GreaterThanThCondition(s.lhs, Left(c.getTh))
            val rhsCond = new GreaterThanThCondition(s.rhs, Left(c.getTh))
            if (v == PealTrue) {
              verify(lhsCond, I, v) || verify(rhsCond, I, v)
            } else {
              verify(lhsCond, I, v) && verify(rhsCond, I, v)
            }
          case s: MinPolicySet =>
            val lhsCond = new GreaterThanThCondition(s.lhs, Left(c.getTh))
            val rhsCond = new GreaterThanThCondition(s.rhs, Left(c.getTh))
            if (v == PealTrue) {
              verify(lhsCond, I, v) && verify(rhsCond, I, v)
            } else {
              verify(lhsCond, I, v) || verify(rhsCond, I, v)
            }
        }
    }
  }

  def evalPol(pol: PolicySet, th: BigDecimal, I: Map[String, Either[BigDecimal, ThreeWayBoolean]]): ThreeWayBoolean = pol match {
    case p: Pol =>
      val rulesToProcess = p.rules.filterNot(r => I.get(r.q.name) == Some(Right(PealFalse)))
      val trueRules = rulesToProcess.filter(r => I.get(r.q.name) == Some(Right(PealTrue)))

      p.operator match {
        case Plus =>
          if (!trueRules.isEmpty) {
            if (th < trueRules.map(r => r.numberScore).sum) {
              return PealTrue
            }
            else if (rulesToProcess.map(r => r.numberScore).sum <= th) {
              return PealFalse
            }
            else {
              return PealBottom
            }
          }
          if (th < p.score.underlyingScore.left.get && rulesToProcess.forall(r => r.numberScore > th)) {
            return PealTrue
          }
          if (p.score.underlyingScore.left.get <= th && rulesToProcess.filter(r => I.get(r.q.name) == Some(PealBottom)).map(r => r.numberScore).sum <= th) {
            return PealFalse
          }

          PealBottom
        case Mul =>
          if (!trueRules.isEmpty) {
            if (trueRules.map(r => r.numberScore).product <= th) {
              return PealFalse
            }
            else if (th < rulesToProcess.map(r => r.numberScore).product) {
              return PealTrue
            }
            else {
              return PealBottom
            }
          }
          if (th < p.score.underlyingScore.left.get && rulesToProcess.map(r => r.numberScore).product > th) {
            return PealTrue
          }
          if (p.score.underlyingScore.left.get <= th && rulesToProcess.forall(r => r.numberScore <= th)) {
            return PealFalse
          }

          PealBottom
        case Max =>
          if (!trueRules.isEmpty) {
            if (th < trueRules.map(r => r.numberScore).max) {
              return PealTrue
            }
            else if (rulesToProcess.map(r => r.numberScore).max <= th) {
              return PealFalse
            }
            return PealBottom
          }

          if (th < p.score.underlyingScore.left.get && rulesToProcess.forall(r => r.numberScore > th)) {
            return PealTrue
          }
          else if (p.score.underlyingScore.left.get <= th && rulesToProcess.forall(r => r.numberScore <= th)) {
            return PealFalse
          }

          PealBottom
        case Min =>
          if (!trueRules.isEmpty) {
            if (trueRules.map(r => r.numberScore).min <= th) {
              return PealFalse
            }
            else if (th < rulesToProcess.map(r => r.numberScore).min) {
              return PealTrue
            }
            return PealBottom
          }

          if (th < p.score.underlyingScore.left.get && rulesToProcess.forall(r => r.numberScore > th)) {
            return PealTrue
          }
          else if (p.score.underlyingScore.left.get <= th && rulesToProcess.forall(r => r.numberScore <= th)) {
            return PealFalse
          }

          PealBottom
      }
  }
}
