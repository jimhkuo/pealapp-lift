package peal.newsynthesis

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Plus, Min, Max}
import peal.synthesis._
import peal.domain.BasicPolicySet
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.NotCondition
import peal.synthesis.GreaterThanThCondition
import peal.domain.MaxPolicySet
import peal.domain.MinPolicySet
import peal.synthesis.LessThanThCondition
import peal.domain.Pol

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
        def addVariables(set: Set[String]) = p.score.fold(score => set, variable => set ++ variable.names)
        addVariables(acc)
      case _ => acc
    }
  })
  val nonConstantScores = allRules.foldLeft(Set[String]())((acc, rule) => {
    def addVariables(set: Set[String]) = rule.attribute.fold(score => set, variable => set ++ variable.names)
    addVariables(acc)
  })
  val analyses = pealProgramParser.analyses

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
      policySetAssertions.mkString("") +
      policyAssertions.mkString("") +
      policyScoreAssertions.mkString("") +
      conditionAssertions.mkString("") +
      domainSpecifics.mkString("", "\n", "\n") +
      analysesAssertions.mkString("")
  }

  private def policySetAssertions = {
    for ((name, pSet) <- pSets) yield {
      pSet match {
        case p: BasicPolicySet => "(assert (= " + name + "_score " + p.underlyingPolicyName + "_score))\n"
        case p: MaxPolicySet => "(assert (= " + name + "_score (ite (> " + p.lhs.getPolicySetName + "_score " + p.rhs.getPolicySetName + "_score) " + p.lhs.getPolicySetName + "_score " + p.rhs.getPolicySetName + "_score)))\n"
        case p: MinPolicySet => "(assert (= " + name + "_score (ite (> " + p.rhs.getPolicySetName + "_score " + p.lhs.getPolicySetName + "_score) " + p.lhs.getPolicySetName + "_score " + p.rhs.getPolicySetName + "_score)))\n"
      }
    }
  }

  private def policyAssertions = {
    pols.flatMap {
      case (name, pol) =>
        pol.operator match {
          case Max => pol.rules.map(r =>
            "(declare-const " + name + "_score_" + r.q.name + " Real)\n" +
              "(assert (implies " + r.q.name + " (<= " + r.scoreString + " " + name + "_score_" + r.q.name + ")))\n")
          case Min => pol.rules.map(r =>
            "(declare-const " + name + "_score_" + r.q.name + " Real)\n" +
              "(assert (implies " + r.q.name + " (<= " + name + "_score_" + r.q.name + " " + r.scoreString + ")))\n")
          case Plus => pol.rules.map(r =>
            "(declare-const " + name + "_score_" + r.q.name + " Real)\n" +
              "(assert (implies " + r.q.name + " (= " + r.scoreString + " " + name + "_score_" + r.q.name + ")))\n" +
              "(assert (implies (not (= 0.0 " + name + "_score_" + r.q.name + ")) " + r.q.name + "))\n")
          case Mul => pol.rules.map(r =>
            "(declare-const " + name + "_score_" + r.q.name + " Real)\n" +
              "(assert (implies " + r.q.name + " (= " + r.scoreString + " " + name + "_score_" + r.q.name + ")))\n" +
              "(assert (implies (not (= 1.0 " + name + "_score_" + r.q.name + ")) " + r.q.name + "))\n")
        }
    }
  }

  private def defaultCase(p: Pol) = {
    "(and (not (or " + p.rules.map(_.q.name).mkString(" ") + ")) (= " + p.getPolicyName + "_score " + p.scoreString + "))"
  }

  private def nonDefaultCase(p: Pol) = {
    val out = for (r <- p.rules) yield {
      "(and " + r.q.name + " (= " + p.policyName + "_score " + r.scoreString + "))"
    }
    out.mkString("", " ", "")
  }

  private def policyScoreAssertions = {
    for ((name, pol) <- pols) yield {
      "(assert (or " + defaultCase(pol) + " " + nonDefaultCase(pol) + "))\n"
    }
  }

  private def analysesAssertions = {
    analyses.flatMap {
      case (name, analysis) => analysis match {
        case AlwaysTrue(n, c) =>
          "(echo \"Result of analysis [" + analysis.analysisName + "]:\")\n" +
          "(push)\n" +
            "(declare-const always_true_" + name + " Bool)\n" +
            "(assert (= always_true_" + name + " " + c + "))\n" +
            "(assert (not always_true_" + name + "))\n" +
            "(check-sat)\n" +
            "(get-model)\n" +
            "(pop)\n"
        case _ => ""
      }
    }
  }

  private def condString(cond: Condition) = cond match {
    case c: LessThanThCondition => "(<= " + c.lhs.getPolicySetName + "_score " + c.rhs.fold(score => score, pSet => pSet.getPolicySetName + "_score") + ")"
    case c: GreaterThanThCondition => "(< " + c.rhs.fold(score => score, pSet => pSet.getPolicySetName + "_score") + " " + c.lhs.getPolicySetName + "_score)"
    case c: NotCondition => "(not " + c.condName + ")"
    case c: AndCondition => "(and " + c.lhs + " " + c.rhs + ")"
    case c: OrCondition => "(or " + c.lhs + " " + c.rhs + ")"
    case c: TrueCondition => "true"
    case c: FalseCondition => "false"
  }

  private def conditionAssertions = {
    conds.flatMap {
      case (name, cond) => "(assert (= " + name + " " + condString(cond) + "))\n"
    }
  }
}
