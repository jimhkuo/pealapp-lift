package peal.verify

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.synthesis._
import peal.domain._
import peal.domain.operator.{Mul, Max, Min, Plus}
import peal.domain.BasicPolicySet
import peal.synthesis.analysis._
import peal.synthesis.GreaterThanThCondition
import peal.domain.MinPolicySet
import peal.domain.MaxPolicySet
import peal.domain.Pol
import scala.Some
import peal.synthesis.NotCondition
import peal.synthesis.AndCondition
import peal.domain.BasicPolicySet
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.GreaterThanThCondition
import peal.synthesis.LessThanThCondition
import peal.domain.MinPolicySet
import peal.domain.MaxPolicySet
import peal.domain.Pol
import scala.Some
import peal.synthesis.analysis.Different
import peal.synthesis.analysis.Equivalent
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.OrCondition
import peal.synthesis.NotCondition


class ExplicitOutputVerifier(input: String) {

  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  def verifyModel(rawModel: String, analysisName: String): (ThreeWayBoolean, Set[String]) = {
    println("P: " + predicateNames)
    val truthMapping = ExplicitOutputProcessor.assignmentExtractor(rawModel)(analysisName).defines.map(d => (d.name, ThreeWayBooleanObj.from(d.value))).toMap
    verifyModel(rawModel, analysisName, truthMapping, Set())
  }

  def verifyModel(rawModel: String, analysisName: String, I: Map[String, ThreeWayBoolean], reMappedPredicates: Set[String]): (ThreeWayBoolean, Set[String]) = {
    println("I: " + I)

    doAnalysis(analysisName, I, reMappedPredicates) match {
      case (PealBottom, s) =>
        println("YYYYYYY Bottom received, Modified Set: " + s)

        var truthMapping = I
        val bottomPredicates = predicateNames.filterNot(truthMapping.contains).filterNot(s.contains)
        if (bottomPredicates.isEmpty) {
          return (PealBottom, s)
        }
        println("Remaining bottoms: " + bottomPredicates)
        (s + bottomPredicates.head).foreach(truthMapping += _ -> PealFalse)
        println("Adding " + bottomPredicates.head)
        println("I': " + truthMapping)
        verifyModel(rawModel, analysisName, truthMapping, s + bottomPredicates.head)
      case (r, s) => (r, s)
    }
  }

  def doAnalysis(analysisName: String, truthMapping: Map[String, ThreeWayBoolean], reMappedPredicates: Set[String]): (ThreeWayBoolean, Set[String]) = {
    analyses(analysisName) match {
      case AlwaysTrue(analysisName, condName) =>
        if (truthMapping.get(condName) == Some(PealFalse)) {
          return (verify(conds(condName), truthMapping, truthMapping(condName)), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be false but is not")
      case AlwaysFalse(analysisName, condName) =>
        if (truthMapping.get(condName) == Some(PealTrue)) {
          return (verify(conds(condName), truthMapping, truthMapping(condName)), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be true but is not")
      case Satisfiable(analysisName, condName) =>
        if (truthMapping.get(condName) == Some(PealTrue)) {
          return (verify(conds(condName), truthMapping, truthMapping(condName)), reMappedPredicates)
        }
        throw new RuntimeException(condName + " should be true but is not")
      case Different(analysisName, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (verify(conds(lhs), truthMapping, truthMapping(lhs)) && verify(conds(rhs), truthMapping, truthMapping(rhs)), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not")
      case Equivalent(analysisName, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return (verify(conds(lhs), truthMapping, truthMapping(lhs)) && verify(conds(rhs), truthMapping, truthMapping(rhs)), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not")
      case Implies(analysisName, lhs, rhs) =>
        if (truthMapping.get(lhs) == Some(PealTrue) && truthMapping.get(rhs) == Some(PealFalse)) {
          return (verify(conds(lhs), truthMapping, truthMapping(lhs)) && verify(conds(rhs), truthMapping, truthMapping(rhs)), reMappedPredicates)
        }
        throw new RuntimeException(lhs + " should be true and " + rhs + " should be false, but are not")
      case _ =>
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }

    throw new RuntimeException("shouldn't get here, no supported analysis specified")
  }

  def verify(cond: Condition, I: Map[String, ThreeWayBoolean], v: ThreeWayBoolean): ThreeWayBoolean = {
    println("### Verify called with " + cond + ", " + I + ", " + v)
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
            val out = v === evalPol(s.pol, c.rhs.left.get, I)
            println("XXXXXXX Out: " + out)
            out
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

  def evalPol(pol: PolicySet, th: BigDecimal, I: Map[String, ThreeWayBoolean]): ThreeWayBoolean = pol match {
    case p: Pol =>
      val rulesToProcess = p.rules.filterNot(r => I.get(r.q.name) == Some(PealFalse))
      val trueRules = rulesToProcess.filter(r => I.get(r.q.name) == Some(PealTrue))

      println("All: " + p.rules)
      println("nonFalseRules: " + rulesToProcess)
      println("trueRules: " + trueRules)

      p.operator match {
        case Plus =>
          if (!trueRules.isEmpty) {
            if (th < trueRules.map(r => r.score).sum) {
              return PealTrue
            }
            else if (rulesToProcess.map(r => r.score).sum <= th) {
              return PealFalse
            }
            else {
              return PealBottom
            }
          }
          if (th < p.score.left.get && rulesToProcess.forall(r => r.score > th)) {
            return PealTrue
          }
          if (p.score.left.get <= th && rulesToProcess.filter(r => I.get(r.q.name) == Some(PealBottom)).map(r => r.score).sum <= th) {
            return PealFalse
          }

          PealBottom
        case Mul =>
          if (!trueRules.isEmpty) {
            if (trueRules.map(r => r.score).product <= th) {
              return PealFalse
            }
            else if (th < rulesToProcess.map(r => r.score).product) {
              return PealTrue
            }
            else {
              return PealBottom
            }
          }
          if (th < p.score.left.get && rulesToProcess.map(r => r.score).product > th) {
            return PealTrue
          }
          if (p.score.left.get <= th && rulesToProcess.forall(r => r.score <= th)) {
            return PealFalse
          }

          PealBottom
        case Max =>
          if (!trueRules.isEmpty) {
            if (th < trueRules.map(r => r.score).max) {
              return PealTrue
            }
            else if (rulesToProcess.map(r => r.score).max <= th) {
              return PealFalse
            }
            return PealBottom
          }

          if (th < p.score.left.get && rulesToProcess.forall(r => r.score > th)) {
            return PealTrue
          }
          else if (p.score.left.get <= th && rulesToProcess.forall(r => r.score <= th)) {
            return PealFalse
          }

          PealBottom
        case Min =>
          if (!trueRules.isEmpty) {
            if (trueRules.map(r => r.score).min <= th) {
              return PealFalse
            }
            else if (th < rulesToProcess.map(r => r.score).min) {
              return PealTrue
            }
            return PealBottom
          }

          if (th < p.score.left.get && rulesToProcess.forall(r => r.score > th)) {
            return PealTrue
          }
          else if (p.score.left.get <= th && rulesToProcess.forall(r => r.score <= th)) {
            return PealFalse
          }

          PealBottom
      }
  }
}
