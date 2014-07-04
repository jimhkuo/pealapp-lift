package peal.explicit

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.synthesis.{GreaterThanThCondition, LessThanThCondition, Condition}
import peal.synthesis.analysis.{Equivalent, AlwaysTrue, AnalysisGenerator}
import peal.verifier.Z3ModelExtractor

import scala.collection.JavaConversions._


class ExplicitAnalyser(input: String) {

  private val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  private val conds = pealProgramParser.conds.toMap
  private val pols = pealProgramParser.pols.toMap
  private val analyses = pealProgramParser.analyses
  private val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  private def extractPolicySet(pSet : PolicySet): List[String] = pSet match {
    case BasicPolicySet(p, n) => extractPolicySet(p)
    case MaxPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case MinPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case Pol(rs, o, s, n) => n :: Nil
  }

  //This only works for explicit synthesis outputs
  private def pullPolicies(cond : Condition) = cond match {
    case LessThanThCondition(lhs, rhs) => extractPolicySet(lhs) :: Nil
    case GreaterThanThCondition(lhs, rhs) => extractPolicySet(lhs) :: Nil
  }

  private def pullCond(analysis: AnalysisGenerator) = analysis match {
    case AlwaysTrue(_, c) => c :: Nil
    case Equivalent(_, c1, c2) => c1 :: c2 :: Nil
  }


  def analyse(rawModel: String, analysisName: String): String = {
    val I = Z3ModelExtractor.extractI(rawModel)(analysisName)
    //call explicit verifier
    val (ans, reMapped) = new ExplicitOutputVerifier(input).verifyModel(rawModel, analysisName)
    //pull its remapped set, together with I to create the value map
    var completeTruthMapping = I
    reMapped.foreach(completeTruthMapping += _ -> Right(PealFalse))
    //then pull all policies used in the analysis, reconstruct policy descriptions
    analyses.foreach(println(_))
    val condNames = analyses.map(a => pullCond(a._2)).flatten
    println(condNames)
    val bs = for {
      c <- condNames
      b <- pullPolicies(conds(c))
    } yield {
      b
    }

    val policies = bs.flatten.toSet
    println(policies)

    policies.foreach(p => println(p + " = " + pols(p)))

    ???
  }

}
