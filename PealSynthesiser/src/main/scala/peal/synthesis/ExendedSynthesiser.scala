package peal.synthesis

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.domain.Pol

class ExendedSynthesiser(input: String) extends Synthesiser {

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
        def addVariables(set: Set[String]) = p.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
        addVariables(acc)
      case _ => acc
    }
  })
  val nonConstantScores = allRules.foldLeft(Set[String]())((acc, rule) => {
    def addVariables(set: Set[String]) = rule.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
    addVariables(acc)
  })
  val analyses = pealProgramParser.analyses

  private def predicateDeclaration = {

    for {
      p <- pols
      r <- p._2.rules if r.score.scoreRange != None
//      r <- p._2.score.scoreRange if p._2.score.scoreRange != None
    } yield {
      "(declare-const " + p._1 + "_" + r.q.name + "_U Real)\n" +
      "(assert (and (<= " + p._1 + "_" + r.q.name + "_U " + r.score.scoreRange.get.maxValue + ") (<= " + r.score.scoreRange.get.minValue + " " + p._1 + "_" + r.q.name + "_U)))"
    }
  }

  def generate() = {
    val declarations = for (name <- predicateNames) yield "(declare-const " + name + " Bool)\n"
    val variableDeclarations = for (name <- nonConstantScores) yield "(declare-const " + name + " Real)\n"
    val nonConstantScoreDeclarations = for (name <- nonConstantDefaultScores) yield "(declare-const " + name + " Real)\n"
    val policyScoreDeclarations = for (name <- pols.keySet()) yield "(declare-const " + name + "_score" + " Real)\n"
    val policySetScoreDeclarations = for (name <- pSets.keySet()) yield "(declare-const " + name + "_score" + " Real)\n"
    val condDeclarations = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1)

//    println(pols)
//    println(pSets)

    declarations.mkString("") +
      variableDeclarations.mkString("") +
      nonConstantScoreDeclarations.mkString("") +
      condDeclarations.mkString("") +
      policyScoreDeclarations.mkString("") +
      policySetScoreDeclarations.mkString("") +
      domainSpecifics.mkString("", "\n", "\n") +
      predicateDeclaration.mkString("")
  }
}
