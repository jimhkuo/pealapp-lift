package peal.z3

import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import z3.scala.{Z3AST, Z3Config, Z3Context}
import scala.collection.JavaConversions._
import z3.ModelGetter


class Z3Analyser {

  var z3 = new Z3Context(new Z3Config("MODEL" -> true))

  def cleanUp {
    z3.delete()
    z3 = null
  }

  def isAlwaysTrue(input: String) = {
    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val n = for (
      pol <- pealProgrmParser.pols.values();
      rule <- pol.rules
    ) yield rule.q.name

    var const = Map[String, Z3AST]()

    for (name <- n.toSeq.distinct) yield (const += name -> z3.mkBoolConst(name))

//    val phi = pealProgrmParser.pSet.synthesisByZ3(const, z3)
//    val solver = z3.mkSolver
//    solver.assertCnstr(z3.mkNot(phi))
//    var (sol, model) = ModelGetter.get(solver)
//
//    sol.get
    false
  }


  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }
}
