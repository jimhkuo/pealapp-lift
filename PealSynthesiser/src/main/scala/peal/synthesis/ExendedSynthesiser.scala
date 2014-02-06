package peal.synthesis

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.domain.Pol
import peal.domain.operator._
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
    } yield {
      "(declare-const " + p._1 + "_" + r.q.name + "_U Real)\n" +
        "(assert (and (<= " + p._1 + "_" + r.q.name + "_U " + r.score.scoreRange.get.maxValue + ") (<= " + r.score.scoreRange.get.minValue + " " + p._1 + "_" + r.q.name + "_U)))"
    }
  }

  private def policyDefaultDeclaration = {
    for {
      p <- pols if p._2.score.scoreRange != None
    } yield {
      "(declare-const " + p._1 + "_default_U Real)\n" +
        "(assert (and (<= " + p._1 + "_default_U " + p._2.score.scoreRange.get.maxValue + ") (<= " + p._2.score.scoreRange.get.minValue + " " + p._1 + "_default_U)))"
    }
  }

  private def ruleDisjunction(pol: Pol) = pol.rules.size match {
    case 0 => "false"
    case 1 => pol.rules(0).q.name
    case _ => "(or " + pol.rules.map(_.q.name).mkString(" ") + ")"
  }

  private def ruleScoreDisjunction(pol: Pol) = pol.rules.size match {
    case 0 => "false"
    case 1 => pol.rules(0).q.name
    case _ => "(" + pol.operator + " " + pol.rules.map(r => "(ite " + r.q.name + " " + Z3ScoreGenerator.generate(r.score, pol.policyName + "_" + r.q.name + "_U") + " " + pol.operator.unit + ")").mkString(" ") + ")"
  }

  private def policyComposition = {
    for (
      (k, p) <- pols
    ) yield {
      p.rules.size match {
        case 0 => "(assert (= " + k + "_score " + Z3ScoreGenerator.generate(p.score, k + "_default_U") + ")))"
        case 1 => "(assert (= " + k + "_score (ite " + p.rules(0).q.name + " " + Z3ScoreGenerator.generate(p.rules(0).score, k + "_" + p.rules(0).q.name + "_U") + " " + Z3ScoreGenerator.generate(p.score, k + "_default_U") + ")))"
        case _ =>
          p.operator match {
            case Plus | Mul =>
              "(assert (= " + k + "_score (ite " + ruleDisjunction(p) + " " + ruleScoreDisjunction(p) + " " + Z3ScoreGenerator.generate(p.score, k + "_default_U") + ")))"
          }
      }
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

    declarations.mkString("") +
      variableDeclarations.mkString("") +
      nonConstantScoreDeclarations.mkString("") +
      condDeclarations.mkString("") +
      policyScoreDeclarations.mkString("") +
      policySetScoreDeclarations.mkString("") +
      domainSpecifics.mkString("", "\n", "\n") +
      predicateDeclaration.mkString("", "\n", "\n") +
      policyDefaultDeclaration.mkString("", "\n", "\n") +
      policyComposition.mkString("", "\n", "\n")
  }
}
