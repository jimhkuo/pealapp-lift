package peal.synthesis

import scala.collection.JavaConversions._
import peal.domain.z3.Term
import peal.antlr.util.ParserHelper

class EagerSynthesiser(input:String) extends Synthesiser{

  def generate(): String = {
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val predicateNames = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSet
    val constsMap = predicateNames.map(t => (t, Term(t))).toMap
    val predicateDeclarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val condDeclarations = for (name <- pealProgramParser.conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedConditions = pealProgramParser.conds.keys.toSeq.sortWith(_ < _)
    val body = for (cond <- sortedConditions) yield {
      "(assert (= " + cond + " " + pealProgramParser.conds(cond).synthesis(constsMap) + "))\n"
    }
    val sortedAnalyses = pealProgramParser.analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {
      "(echo \"Result of analysis [" + pealProgramParser.analyses(analysis).analysisName + "]:\")\n" + pealProgramParser.analyses(analysis).z3SMTInput
    }
    val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1)
    predicateDeclarations.mkString("") + condDeclarations.mkString("") + body.mkString("") + domainSpecifics.mkString("", "\n", "\n") + generatedAnalyses.mkString("")
  }
}
