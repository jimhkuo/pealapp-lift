package peal.antlr

import org.junit.{After, Before, Ignore, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import z3.scala.{Z3AST, Z3Config, Z3Context}


class MajorityVotingTest extends ShouldMatchersForJUnit {
  val z3 : Z3Context = new Z3Context(new Z3Config("MODEL" -> true))
  val consts = Map[String, Z3AST]("q1" -> z3.mkBoolConst("q1"), "q2" -> z3.mkBoolConst("q2"), "q3" -> z3.mkBoolConst("q3"), "q4" -> z3.mkBoolConst("q4"), "q5" -> z3.mkBoolConst("q5"), "q6" -> z3.mkBoolConst("q6"))
  @After def tearDown() {
    z3.delete()
  }
  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  @Ignore("stand alone test")
  @Test
  def testMajorityVoting() {
    val n = 23
    val input = "cond = 0.5 < pSet\nb1 = + (" +
      (for (i <- 0 until n) yield ("(q" + i + " " + 1.0 / n + ")")).mkString("") +
      " ) default 0\npSet = b1"

    println(input)
        val pealProgrmParser = getParser(input)
        pealProgrmParser.program()

        val synthesis = pealProgrmParser.conds.values.head.synthesis(z3,consts)
        println(synthesis)
  }
}
