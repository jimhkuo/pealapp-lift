package peal.antlr

import org.junit.{After, Before, Ignore, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import z3.scala.{Z3Config, Z3Context}


class MajorityVotingTest extends ShouldMatchersForJUnit {
  var z3 : Z3Context = null
  @Before def setup() {
    z3 = new Z3Context(new Z3Config("MODEL" -> true))
  }
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

        val synthesis = pealProgrmParser.pSet.synthesis(z3)
        println(synthesis)
  }
}
