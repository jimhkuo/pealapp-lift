package peal.verify

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.synthesis._
import peal.domain._
import peal.domain.operator.Plus
import peal.domain.BasicPolicySet
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.GreaterThanThCondition
import peal.domain.MinPolicySet
import peal.domain.MaxPolicySet
import peal.domain.Pol
import scala.Some
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.NotCondition


class ExplicitOutputVerifier(input: String) {

  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  //TODO
  //extract predicate truth assignments from Z3 model to create I
  //take v from the truth assignment for the condition in the raw Z3 result, but need to perform external check first, see below (temporarily skipped)
  //need to pass in cond map as some Conditions only hold cond names

  //always_true? c1 -> c1 has to be false
  //always_false? c1 -> c1 has to be true
  //different? c1 c2 -> c1, c2 have to be different
  //equivalent? c1 c2 -> c1, c2 have to be same
  //implies? c1 c2 -> c1 has to be true, c2 has to be false

  //looking for q_i not available in I returns bottom

  //need to sets up 3 way truth value, true, false, and bottom
  //need to pull out the analyses so we can find out what condition needs to be examined

  def verifyModel(model: String, analysisName: String): ThreeWayBoolean = {

    //this ignores the correct conversion for non boolean types
    val truthMapping = ExplicitOutputProcessor.assignmentExtractor(model)(analysisName).defines.map(d => (d.name, ThreeWayBooleanObj.from(d.value))).toMap
    println("I: " + truthMapping)

    println("########### Doing : " + analysisName)

    analyses(analysisName) match {
      case AlwaysTrue(analysisName, condName) =>
        //            println(conds(condName))
        //            println(truthMapping)
        //            println(truthMapping(condName))
        if (truthMapping(condName) != PealFalse) {
          throw new RuntimeException(condName + " should be false but is not")
        }
        return verify(conds(condName), truthMapping, truthMapping(condName))
      case AlwaysFalse(analysisName, condName) =>
        if (truthMapping(condName) != PealTrue) {
          throw new RuntimeException(condName + " should be true but is not")
        }
        return verify(conds(condName), truthMapping, truthMapping(condName))
      case _ =>
        //shouldn't get here
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }
    //shouldn't get here
    throw new RuntimeException("shouldn't get here, no analysis specified")
  }

  def verify(cond: Condition, I: Map[String, ThreeWayBoolean], v: ThreeWayBoolean): ThreeWayBoolean = {
    println("verify called with " + cond + ", " + I + ", " + v)
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
          case s: BasicPolicySet => v === evalPol(s.pol, c.rhs.left.get, I) //this is ok since if rhs is not left then it should fail
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
    case p: Pol => p.operator match {
      case Plus =>
        val rules = p.rules
        //        println(rules)
        val trueRules = rules.filter(r => I.get(r.q.name) == Some(PealTrue))
        //        println(trueRules)
        if (!trueRules.isEmpty) {
          if (th < trueRules.map(r => r.score).sum) {
            return PealTrue
          }
          else if (rules.map(r => r.score).sum <= th) {
            return PealFalse
          }
          else {
            return PealBottom
          }
        }
        //        println(th)
        //        println(p.score.left.get)
        //        println(rules.map(r => r.score).sum)
        if (th < p.score.left.get && rules.forall(r => r.score > th)) {
          return PealTrue
        }
        if (p.score.left.get <= th && rules.filter(r => I.get(r.q.name) == Some(PealBottom)).map(r => r.score).sum <= th) {
          return PealFalse
        }

        PealBottom
    }
  }
}
