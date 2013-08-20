package peal.antlr

import org.junit.{After, Before, Ignore, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import z3.scala.{Z3AST, Z3Config, Z3Context}
import peal.synthesis.analysis.AlwaysTrue
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
  def testCanDealWithTags() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "DOMAIN_SPECIFICS\n" +
      "(declare-const x Real)\n" +
      "(declare-const y Real)\n" +
      "(assert (= q1 (< x (y+1)))\n" +
      "(assert (= q3 (= x (y + 3.5)))\n" +
      "(assert (= q1 (< x (+ y 1))))\n" +
      "ANALYSES\n" +
      "name1 = always_true? cond1\n"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond1").synthesis(z3, consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false))) ")
    pealProgrmParser.analyses("name1") should be(new AlwaysTrue("name1", "cond1"))
  }

  @Test
  def testCanParseDefaultInputInWebapp() {
    val input =
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "pSet = max(b1, b2)" +
        "cond = pSet <= 0.5"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(z3, consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMin() {
    val input =
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "pSet = min(b1, b2)" +
        "cond = pSet <= 0.5"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(z3, consts) should beZ3Model("(or (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMinAndGreaterThanTh() {
    val input =
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "pSet = min(b1, b2)" +
        "cond = 0.5 < pSet "

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(z3, consts) should beZ3Model("(and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))")
  }

  @Test
  def testDealWithMultipleConditions() {
    val input = "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet1 = min(b1, b2)" +
      "pSet2 = min(b1, b2)" +
      "cond1 = 0.5 < pSet1 " +
      "cond2 = pSet2 <= 0.5 "

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond1").synthesis(z3, consts) should beZ3Model("(and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))")
    pealProgrmParser.conds("cond2").synthesis(z3, consts) should beZ3Model("(or (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testCanDoExample1InEmail() {
    val input =
      "b2 = + ((q4 0.2) (q5 0.2) (q6 0.1)) default 0.6\n" +
        "pSet = b2\n" +
        "cond = pSet <= 0.5\n"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(z3, consts) should beZ3Model("(and (or q4 q5 q6) (not false))")
  }

  @Test
  def testCanDoExample2InEmail() {
    val input = "b2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2\ncond = pSet <= 0.5\n"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(z3, consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (and q6 q5 q4)))")
  }

  @Test
  def testCanDoExample3InEmail() {
    val input = "b2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2\ncond = pSet <= 0.2"

    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(z3, consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (or q6 (and q5 q4))))")
  }

  @Test
  def testBrokenCaseForPlus() {
    val input = "POLICIES\nb0 = max ((q4 0.41)) default 0.67\nb1 = * ((q4 0.02)) default 0.58\nb2 = + ((q5 0.54) (q3 0.06)) default 0.44\nb3 = min ((q2 0.69)) default 0.62\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\nCONDITIONS\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds("cond2").synthesis(z3, consts) should beZ3Model ("(or (and (or (not q4) false) (and q4 (not q4))) (and (and (or q5 q3) false) (or (not q2) (not false))))")
  }
  @Test
  def testM2HandleEdgeCaseCorrectly() {
    val input = "POLICIES\nb0 = max ((q4 0.41)) default 0.67\nb1 = * ((q4 1.0) (q3 0.6)) default 0.06\nb2 = + ((q5 0.54) (q3 0.06)) default 0.44\nb3 = min ((q2 0.69)) default 0.62\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\nCONDITIONS\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val pealProgrmParser = getParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds("cond2").synthesis(z3, consts) should beZ3Model ("(or (and (or (not q4) false) (and (or q4 q3) (not q3))) (and (and (or q5 q3) false) (or (not q2) (not false))))")
  }
}
