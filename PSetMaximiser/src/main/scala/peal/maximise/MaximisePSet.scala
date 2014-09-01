package peal.maximise

import peal.antlr.util.ParserHelper
import peal.domain.Pol
import peal.domain.z3.Model
import peal.synthesis.{ExtendedSynthesiserCore, GreaterThanThCondition}
import peal.synthesis.analysis.Satisfiable
import peal.z3.Z3Caller
import scala.collection.JavaConversions._


case class MaximisePSet(input : String, pSet: String, accuracy: BigDecimal, pol:String = "") {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  val pols = pealProgramParser.pols.toMap
  val allRules = pols.values.flatMap(pol => pol.rules)
  val predicateNames: Set[String] = allRules.map(r => r.q.name).toSet
  val variableDefaultScores: Set[String] = pols.foldLeft(Set[String]())((acc, tuple) => {
    tuple._2 match {
      case p: Pol =>
        def addVariables(set: Set[String]) = p.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
        addVariables(acc)
      case _ => acc
    }
  })
  val variableScores: Set[String] = allRules.foldLeft(Set[String]())((acc, rule) => {
    def addVariables(set: Set[String]) = rule.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
    addVariables(acc)
  })
  val pSets = pealProgramParser.pSets.toMap

  private def satisfiableZ3Code(threshold: BigDecimal, pol: String) = {
    val conds: Map[String, GreaterThanThCondition] = Map("cond1" -> GreaterThanThCondition(pSets(pSet + (if (pol != "") "_" + pol else "")), Left(0.0)))
    val analyses: Map[String, Satisfiable] = Map("name1" -> new Satisfiable("name1", "cond1"))
    val z3Code = ExtendedSynthesiserCore(pols, conds, pSets, analyses, Array(), predicateNames, variableDefaultScores, variableScores).generate()
    val z3RawOutput = Z3Caller.call(z3Code)
    val parser = ParserHelper.getZ3OutputParser(z3RawOutput)
    val model: Map[String, Model] = parser.results().toMap
    model("name1").isSat
  }

  def doIt() = {
//    satisfiableZ3Code(0.0)
    if (pol)
  }

}
