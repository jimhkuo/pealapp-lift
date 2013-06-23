package peal.antlr

import org.junit.{Ignore, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._


class PealProgramParserTest extends ShouldMatchersForJUnit {

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }


  @Test(expected = classOf[RuntimeException])
  def testInValidInput() {
    val input = "co" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
  }

  @Test
  def testCanParseSampleInput() {
    val input = "cond = pSet <= 0.5" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    println(pealProgrmParser.pSet.synthesis)
  }

  @Ignore("wip")
  @Test
  def testCanParseInputExample1InEmail() {
    val input = "cond = pSet <= 0.5\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.1)) default 0.6\npSet = b2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    //    val pols = pealProgrmParser.pols
    //    pols("b1").rules.size should be(3)
    //    pols("b2").rules.size should be(3)

    println(pealProgrmParser.pSet.synthesis)
    //  expected  (or false (not false))
  }
}
