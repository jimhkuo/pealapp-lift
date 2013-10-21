package peal.newsynthesis

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.domain.Pol

class NewSynthesiser(input: String) {

  def generate() = {
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val pols = pealProgramParser.pols
    val conds = pealProgramParser.conds
    val pSets = pealProgramParser.pSets
    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules)
    val predicateNames = allRules.map(r => r.q.name).toSet
    val nonConstantDefaultScores = pols.foldLeft(Set[String]())((acc, tuple) => {
      tuple._2 match {
        case p: Pol =>
          def addVariables(set: Set[String]) = p.score.fold(score => set, variable => set + variable.name)
          addVariables(acc)
        case _ => acc
      }
    })
    val nonConstantScores = allRules.foldLeft(Set[String]())((acc, rule) => {
      def addVariables(set: Set[String]) = rule.attribute.fold(score => set, variable => set + variable.name)
      addVariables(acc)
    })
    val analyses = pealProgramParser.analyses.toMap

    val declarations = for (name <- predicateNames) yield "(declare-const " + name + " Bool)\n"
    val variableDeclarations = for (name <- nonConstantScores) yield "(declare-const " + name + " Real)\n"
    val nonConstantScoreDeclarations = for (name <- nonConstantDefaultScores) yield "(declare-const " + name + " Real)\n"

    declarations.mkString("") + variableDeclarations.mkString("") + nonConstantScoreDeclarations.mkString("")
  }
}
