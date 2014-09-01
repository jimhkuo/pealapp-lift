package peal.synthesis

import peal.antlr.util.ParserHelper
import peal.domain.Pol

import scala.collection.JavaConversions._

class ExtendedSynthesiser(input: String) extends Synthesiser {

  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  val pols = pealProgramParser.pols.toMap
  val conds = pealProgramParser.conds.toMap
  val pSets = pealProgramParser.pSets.toMap
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
  val analyses = pealProgramParser.analyses.toMap

  override def generate(doVacuityCheck: Boolean): String = {
    val domainSpecifics: Array[String] = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1).filterNot(_.trim.startsWith("%"))

    ExtendedSynthesiserCore(pols, conds, pSets, analyses, domainSpecifics, predicateNames,variableDefaultScores,variableScores).generate(doVacuityCheck)
  }
}
