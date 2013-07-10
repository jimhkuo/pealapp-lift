package peal.antlr

import org.junit.{After, Before, Ignore, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import z3.scala.{Z3AST, Z3Config, Z3Context}
import peal.util.Z3ModelMatcher


class PealProgramParserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val z3: Z3Context = new Z3Context(new Z3Config("MODEL" -> true))
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

  @Test(expected = classOf[RuntimeException])
  def testInValidInput() {
    val input = "co" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
  }

  @Test
  def testCanCreateConstsMap() {
    val input = "cond1 = pSet <= 0.5\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "pSet = max(b1, max(b1,b2))"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    pealProgrmParser.pols should have size (2)
    pealProgrmParser.pols should contain key ("b1")
    pealProgrmParser.pols should contain key ("b2")
    pealProgrmParser.conds should have size (1)
    pealProgrmParser.conds should contain key ("cond1")
  }

  @Test
  def testCanDealWithNestedInput() {
    val input = "cond = pSet <= 0.5\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "pSet = max(b1, max(b1,b2))"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)

    val set = pealProgrmParser.pSets.values().head
    set.synthesis(z3, consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false))))")
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

    val set = pealProgrmParser.pSets.values().head
    set.synthesis(z3, consts) should beZ3Model("(and (or q1 q2 q3) (or q1 q2))")
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

    pealProgrmParser.pSets.values().head.synthesis(z3, consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMin() {
    val input = "cond = pSet <= 0.5" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = min(b1, b2)"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.pSets.values().head.synthesis(z3, consts) should beZ3Model("(or (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMinAndGreaterThanTh() {
    val input = "cond = 0.5 < pSet " +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = min(b1, b2)"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.pSets.values().head.synthesis(z3, consts) should beZ3Model("(and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))")
  }

  @Test
  def testCanDoExample1InEmail() {
    val input = "cond = pSet <= 0.5\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.1)) default 0.6\npSet = b2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.pSets.values().head.synthesis(z3, consts) should beZ3Model("(and (or q4 q5 q6) (not false))")
  }

  @Test
  def testCanDoExample2InEmail() {
    val input = "cond = pSet <= 0.5\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.pSets.values().head.synthesis(z3, consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (and q6 q5 q4)))")
  }

  @Test
  def testCanDoExample3InEmail() {
    val input = "cond = pSet <= 0.2\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.pSets.values().head.synthesis(z3, consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (or q6 (and q5 q4))))")
  }
}
