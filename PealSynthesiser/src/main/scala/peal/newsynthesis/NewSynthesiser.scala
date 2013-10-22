package peal.newsynthesis

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.domain.{MaxPolicySet, BasicPolicySet, Pol}

class NewSynthesiser(input: String) {

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

  def generate() = {

    val declarations = for (name <- predicateNames) yield "(declare-const " + name + " Bool)\n"
    val variableDeclarations = for (name <- nonConstantScores) yield "(declare-const " + name + " Real)\n"
    val nonConstantScoreDeclarations = for (name <- nonConstantDefaultScores) yield "(declare-const " + name + " Real)\n"
    val policyScoreDeclarations = for (name <- pols.keySet()) yield "(declare-const " + name + "_score" + " Real)\n"
    val policySetScoreDeclarations = for (name <- pSets.keySet()) yield "(declare-const " + name + "_score" + " Real)\n"

    declarations.mkString("") +
      variableDeclarations.mkString("") +
      nonConstantScoreDeclarations.mkString("") +
      policyScoreDeclarations.mkString("") +
      policySetScoreDeclarations.mkString("") +
      policySetAssertions.mkString("")
  }

  private def policySetAssertions = {
    for ((name, pSet) <- pSets) yield {
      pSet match {
        case p : BasicPolicySet => "(assert (= " + name + "_score " + p.underlyingPolicyName + "_score))\n"
//        case p : MaxPolicySet => "(assert (= " + name + "_score (ite (> " + p.lhs.policySetName + "_score " + p.rhs.policySetName + "_score) " + p.lhs.policySetName + "_score " + p.rhs.policySetName + "_score)))\n"
        case _ => "x\n"
      }
    }
  }
}
