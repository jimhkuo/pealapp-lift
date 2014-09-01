package peal.maximise

import peal.antlr.util.ParserHelper
import peal.domain.Pol
import peal.synthesis.{ExtendedSynthesiserCore, GreaterThanThCondition}
import peal.synthesis.analysis.Satisfiable
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

  //  val conds = pealProgramParser.conds.toMap
  //  val analyses = pealProgramParser.analyses.toMap

  private def satisfiableZ3Code(threshold: BigDecimal) = {
    val conds: Map[String, GreaterThanThCondition] = Map("cond1" -> GreaterThanThCondition(pSets(pSet + "_" + pol), Left(0.0)))
    val analyses: Map[String, Satisfiable] = Map("name1" -> new Satisfiable("name1", "cond1"))
    ExtendedSynthesiserCore(pols, conds, pSets, analyses, Array(), predicateNames, variableDefaultScores, variableScores).generate()
  }

  def doIt() = {
    0.0
  }

}
