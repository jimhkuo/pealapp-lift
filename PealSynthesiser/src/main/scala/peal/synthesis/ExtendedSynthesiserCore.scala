package peal.synthesis

import peal.domain.{PolicySet, Pol}
import peal.domain.operator.{Mul, Plus, Min, Max}
import scala.collection.JavaConversions._
import peal.synthesis.analysis.{AnalysisGenerator, AlwaysFalse, AlwaysTrue}
import peal.synthesis.util.{ConditionTranslator, Z3ScoreGenerator}

case class ExtendedSynthesiserCore(pols: Map[String, Pol], conds: Map[String, Condition], pSets : Map[String, PolicySet], analyses: Map[String, AnalysisGenerator], domainSpecifics: Array[String], predicateNames: Set[String], variableDefaultScores: Set[String], variableScores: Set[String]) {

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

  private def ruleImplicationDisjunction(pol: Pol) = {
    "(or " + pol.rules.map(r => "(and " + r.q.name + " (= " + pol.policyName + "_score " + Z3ScoreGenerator.generate(r.score, pol.policyName + "_" + r.q.name + "_U") + "))").mkString(" ") + ")"
  }

  private def cop(pol: Pol) = pol.operator match {
    case Max => ">="
    case Min => "<="
  }

  private def firstCase(pol: Pol) = {
    for (r <- pol.rules) yield {
      "(assert (implies " + r.q.name + " (" + cop(pol) + " " + pol.policyName + "_score " + Z3ScoreGenerator.generate(r.score, pol.policyName + "_" + r.q.name + "_U") + ")))"
    }
  }

  private def secondCase(pol: Pol) = "(assert (implies (not " + ruleDisjunction(pol) + ") (= " + pol.policyName + "_score " + Z3ScoreGenerator.generate(pol.score, pol.policyName + "_default_U") + ")))"

  private def thirdCase(pol: Pol) = "(assert (implies " + ruleDisjunction(pol) + " " + ruleImplicationDisjunction(pol) + "))"

  private def policyComposition = {
    for (
      (k, p) <- pols
    ) yield {
      p.rules.size match {
        case 0 => "(assert (= " + k + "_score " + Z3ScoreGenerator.generate(p.score, k + "_default_U") + "))"
        case 1 => "(assert (= " + k + "_score (ite " + p.rules(0).q.name + " " + Z3ScoreGenerator.generate(p.rules(0).score, k + "_" + p.rules(0).q.name + "_U") + " " + Z3ScoreGenerator.generate(p.score, k + "_default_U") + ")))"
        case _ =>
          p.operator match {
            case Plus | Mul => "(assert (= " + k + "_score (ite " + ruleDisjunction(p) + " " + ruleScoreDisjunction(p) + " " + Z3ScoreGenerator.generate(p.score, k + "_default_U") + ")))"
            case Max | Min => firstCase(p).mkString("", "\n", "\n") + secondCase(p) + "\n" + thirdCase(p)
          }
      }
    }
  }

  private def predicateDeclaration = {
    for {
      p <- pols
      r <- p._2.rules if r.score.scoreRange != None
    } yield {
      "(declare-const " + p._1 + "_" + r.q.name + "_U Real)\n" +
        "(assert (and (<= " + p._1 + "_" + r.q.name + "_U " + r.score.scoreRange.get.maxValue + ") (<= " + r.score.scoreRange.get.minValue + " " + p._1 + "_" + r.q.name + "_U)))"
    }
  }

  def generate(doVacuityCheck: Boolean): String = {
    val declarations = for (name <- predicateNames) yield "(declare-const " + name + " Bool)\n"
    val allRealDeclarations = (variableScores ++ variableDefaultScores ++ pols.keySet.map(_ + "_score") ++ pSets.keySet.map(_ + "_score")).toList.sorted
    val allVariableDeclarations = for (name <- allRealDeclarations) yield "(declare-const " + name + " Real)\n"
    val condDeclarations = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
//    val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1).filterNot(_.trim.startsWith("%"))

    val condDetails = for (name <- conds.keys) yield {
      "(assert (= " + name + " " + ConditionTranslator.translate(name, conds.toMap) + "))"
    }

    val vacuityChecks = if (doVacuityCheck) {
      val sortedConditions = conds.keys.toSeq.sortWith(_ < _)
      for (cond <- sortedConditions) yield {
        val trueName = "a1_" + cond + "_vct"
        val falseName = "a2_" +  cond + "_vcf"
        Seq(trueName -> AlwaysTrue(trueName, cond), falseName -> AlwaysFalse(falseName, cond))
      }
    } else {
      Seq()
    }

    val completeAnalyses = analyses ++ vacuityChecks.flatten

    val generatedAnalyses: Seq[String] = if (completeAnalyses.size <= 1) {
      val analysis: AnalysisGenerator = completeAnalyses.map(_._2).toSeq(0)
      Seq("(echo \"Result of analysis [" + analysis.analysisName + "]:\")\n" + analysis.z3SMTInput)
    } else {
      val sortedAnalyses = completeAnalyses.keys.toSeq.sortWith(_ < _)
      for (analysis <- sortedAnalyses) yield {
        "(echo \"Result of analysis [" + completeAnalyses(analysis).analysisName + "]:\")\n(push)\n" + completeAnalyses(analysis).z3SMTInput + "(pop)\n"
      }
    }

    declarations.mkString +
      policyDefaultDeclaration.mkString("", "\n", "\n") +
      allVariableDeclarations.mkString +
      condDeclarations.mkString +
      predicateDeclaration.mkString("", "\n", "\n") +
      domainSpecifics.mkString("", "\n", "\n") +
      condDetails.mkString("", "\n", "\n") +
      policyComposition.mkString("", "\n", "\n") +
      generatedAnalyses.mkString
  }

}
