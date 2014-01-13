package peal.verify

import peal.antlr.util.ParserHelper
import peal.synthesis.analysis.AlwaysTrue
import scala.collection.JavaConversions._
import peal.synthesis.{GreaterThanThCondition, AndCondition, NotCondition, Condition}
import peal.domain.{Pol, BasicPolicySet, PolicySet}
import peal.domain.operator.Plus
import peal.domain.z3.Term


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

  def verifyModel(model: String, analysisName: String) = {

    //this ignores the correct conversion for non boolean types
    val I = ExplicitOutputProcessor.assignmentExtractor(model)(analysisName).defines.map(d => (d.name, d.value.toBoolean)).toMap
    println(I)

    println(predicateNames)
    pealProgramParser.analyses.foreach {
      case (key, analysis) =>
        analysis match {
          //TODO deal with different analyses
          case AlwaysTrue(n, c) =>
            //TODO need to check if I(c) is false
            verify(conds(c), I, I(c))
          case _ => println("not matched")
            false
        }
    }
  }

  def verify(cond: Condition, I: Map[String, Boolean], v: Boolean): Boolean = cond match {
    case NotCondition(c) => verify(conds(c), I, !v)
    case GreaterThanThCondition(lhs, rhs) => lhs match {
      case s: BasicPolicySet => v == evalPol(s.pol, rhs.left.get, I) //this is ok since if rhs is not left then it should fail
    }
  }

  def evalPol(pol: PolicySet, th: BigDecimal, I: Map[String, Boolean]) = pol match {
    case p: Pol => p.operator match {
      case Plus => //TODO need to pull out predicates only
    }
  }

}
