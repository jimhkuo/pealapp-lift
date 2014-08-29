package peal.synthesis

import peal.domain.Pol
import peal.synthesis.analysis.{AnalysisGenerator, AlwaysFalse, AlwaysTrue}

import scala.collection.JavaConversions._
import peal.domain.z3.Term
import peal.antlr.util.ParserHelper

class ExplicitSynthesiser(input: String) extends Synthesiser {

  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  val pols = pealProgramParser.pols
  val conds = pealProgramParser.conds
  val pSets = pealProgramParser.pSets
  val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules)
  val predicateNames = allRules.map(r => r.q.name).toSet
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
  val analyses = pealProgramParser.analyses

  //  val pealProgramParser = ParserHelper.getPealParser(input)
  //  pealProgramParser.program()
  //
  //  val predicateNames = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSet
  //  val sortedConditions = pealProgramParser.conds.keys.toSeq.sortWith(_ < _)
  //  val analyses = pealProgramParser.analyses

  override def generate(doVacuityCheck: Boolean): String = {

    val sortedConditions = conds.keys.toSeq.sortWith(_ < _)
    val constsMap = predicateNames.map(t => (t, Term(t))).toMap

    val body = for (cond <- sortedConditions) yield {
      "(assert (= " + cond + " " + conds(cond).synthesis(constsMap) + "))\n"
    }
    val vacuityChecks = if (doVacuityCheck) {
      val sortedConditions = pealProgramParser.conds.keys.toSeq.sortWith(_ < _)
      for (cond <- sortedConditions) yield {
        Seq(cond + "_vct" -> AlwaysTrue(cond + "_vct", cond), cond + "_vcf" -> AlwaysFalse(cond + "_vcf", cond))
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

    val predicateDeclarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val condDeclarations = for (name <- pealProgramParser.conds.keys) yield "(declare-const " + name + " Bool)\n"

    val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1).filterNot(_.trim.startsWith("%"))
    predicateDeclarations.mkString +
      condDeclarations.mkString +
      body.mkString +
      domainSpecifics.mkString("", "\n", "\n") +
      generatedAnalyses.mkString
  }
}
