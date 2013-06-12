package peal.antlr

import org.junit.Test
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit

class PealProgramParserTest extends ShouldMatchersForJUnit {

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  @Test
  def testCanParseSampleInput() {
    val input = "cond = pSet <= 0.5" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    //    val pols = pealProgrmParser.pols

    //    pols("b1").rules.size should be(3)
    //    pols("b2").rules.size should be(3)
    //    pols("b3").rules.size should be(2)
    //    pols("b4").rules.size should be(1)

    println(pealProgrmParser.pSet.synthesis)
  }
}
