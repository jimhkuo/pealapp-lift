package peal.antlr

import org.junit.{Ignore, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._


class PealProgramParserTest extends ShouldMatchersForJUnit {

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramSpikeLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramSpikeParser(tokenStream)
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
  def testCanDealWithMul() {
    val input = "cond = pSet <= 0.5" +
      "b1 = * ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "pSet = b1"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)

    val set = pealProgrmParser.pSet
    set.synthesis should be("(and (or q1 q2 q3) (or q1 q2))")
  }

  @Test
  def testCanParseDefaultInputInWebapp() {
    val input = "cond = pSet <= 0.5" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.pSet.synthesis should be("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testCanDoExample1InEmail() {
    val input = "cond = pSet <= 0.5\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.1)) default 0.6\npSet = b2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.pSet.synthesis should be("(and (or q4 q5 q6) (not false))")
  }

  @Test
  def testCanDoExample2InEmail() {
    val input = "cond = pSet <= 0.5\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.pSet.synthesis should be("(or (and (not q4) (not q5) (not q6)) (not (and q6 q5 q4)))")
  }

  @Test
  def testCanDoExample3InEmail() {
    val input = "cond = pSet <= 0.2\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.pSet.synthesis should be("(or (and (not q4) (not q5) (not q6)) (not (or q6 (and q5 q4))))")
  }
}
