package peal.eagersynthesis

import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import scala.collection.JavaConversions._
import peal.domain.z3.Term

class EagerSynthesiser() {

  private def getPealProgramParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  def generate(input:String): String = {
    val pealProgramParser = getPealProgramParser(input)
    pealProgramParser.program()

    println(pealProgramParser.analyses)

    val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct
    val constsMap = predicateNames.toSeq.distinct.map(t => (t, Term(t))).toMap

    val predicateDeclarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val condDeclarations = for (name <- pealProgramParser.conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedKeys = pealProgramParser.conds.keys.toSeq.sortWith(_ < _)
    val sortedAnalyses = pealProgramParser.analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {
      "(echo \"Result of analysis [" + pealProgramParser.analyses(analysis).analysisName + "]:\")\n" + pealProgramParser.analyses(analysis).z3SMTInput
    }
    val body = for (cond <- sortedKeys) yield {
      "(assert (= " + cond + " " + pealProgramParser.conds(cond).synthesis(constsMap) + "))\n"
    }

    println("p-------------------")
    println(predicateDeclarations.mkString("\n"))


    println("c-------------------")
    println(condDeclarations.mkString("\n"))

    println("g-------------------")
    println(generatedAnalyses.mkString("\n"))

    val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1)
    println("d-------------------")
    println(domainSpecifics.mkString("\n"))


    predicateDeclarations.mkString("") + condDeclarations.mkString("") + body.mkString("") + domainSpecifics.mkString("", "\n", "\n") + generatedAnalyses.mkString("")
  }
}
