package peal.antlr

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import peal.synthesis.analysis.AlwaysTrue
import peal.util.Z3ModelMatcher
import peal.domain.z3.{PealAst, Term}
import peal.antlr.util.ParserHelper
import peal.domain.{Score, Pol}


class PealProgramParserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test
  def testCanDealWithNewScoreType() {
    val input =
      "b1 = + ((q1 x [0.1,0.2])) default 1\n" +
        "pSet = b1\n" +
        "cond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules).toSeq
//    allRules(0).attribute.right.get should be (new Score())
//    val predicateNames = allRules.foldLeft(Set[String]())((acc, rule) => {
//      def addVariables(set: Set[String]) = rule.attribute.fold(left => set, right => set ++ right.names)
//      addVariables(acc + rule.q.name)
//    })
//
//    predicateNames should be(Set("x", "q1", "q2", "q3"))
  }

  @Test
  def testNonConstantDefaultScores() {
    val input =
      "b1 = + ((q1 x) (q2 0.5) (q3 0.4)) default x\n" +
        "b2 = min ((q1 x) (q2 0.5) (q3 0.4)) default y\n" +
        "b3 = * ((q1 x) (q2 0.5) (q3 0.4)) default 1.1*x\n" +
        "b4 = max ((q1 x) (q2 0.5) (q3 0.4)) default z * 2.9\n" +
        "pSet = max(b1, b2)\n" +
        "cond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()
    val pols = pealProgramParser.pols

    val nonConstantDefaultScores = pols.foldLeft(Set[String]())((acc, tuple) => {
      tuple._2 match {
        case p: Pol =>
          def addVariables(set: Set[String]) = p.score.fold(score => set, variable => set ++ variable.names)
          addVariables(acc)
        case _ => acc
      }
    })

    nonConstantDefaultScores should be(Set("x", "y", "z"))
  }

  @Test
  def testCanNonConstantFromRules() {
    val input =
      "b1 = + ((q1 x) (q2 0.5) (q3 0.4)) default 1\n" +
        "pSet = b1\n" +
        "cond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules)
    val predicateNames = allRules.foldLeft(Set[String]())((acc, rule) => {
      def addVariables(set: Set[String]) = rule.score.underlyingScore.fold(left => set, right => set ++ right.names)
      addVariables(acc + rule.q.name)
    })

    predicateNames should be(Set("x", "q1", "q2", "q3"))
  }

  @Test
  def testCanHandleNegativeScores() {
    val input =
      "b1 = min ((q1 -0.2) (q2 -0.4) (q3 -0.9)) default 1\n" +
        "pSet = b1\n" +
        "cond = pSet <= 0.5"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules(0).numberScore should be(BigDecimal.valueOf(-0.2))
    pols("b1").rules(1).numberScore should be(BigDecimal.valueOf(-0.4))
    pols("b1").rules(2).numberScore should be(BigDecimal.valueOf(-0.9))
  }

  @Test(expected = classOf[RuntimeException])
  def testInValidInput() {
    val input = "co" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"

    val pealProgrmParser = ParserHelper.getPealParser(input)
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

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond1").synthesis(consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false))) ")
    pealProgrmParser.analyses("name1") should be(new AlwaysTrue("name1", "cond1"))
  }

  @Test
  def testNotCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = !cond1\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(not cond1)")
  }

  @Test
  def testAndCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = cond1 && cond1\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(and cond1 cond1)")
  }

  @Test
  def testOrCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = cond1 || cond1\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or cond1 cond1)")
  }

  @Test
  def testTruthCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = false\n" +
      "cond2 = true\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("true")
    pealProgrmParser.conds("cond1").synthesis(consts) should beZ3Model("false")
  }

  @Test
  def testCanParseDefaultInputInWebapp() {
    val input =
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "pSet = max(b1, b2)" +
        "cond = pSet <= 0.5"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMin() {
    val input =
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "pSet = min(b1, b2)" +
        "cond = pSet <= 0.5"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(or (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMinAndGreaterThanTh() {
    val input =
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "pSet = min(b1, b2)" +
        "cond = 0.5 < pSet "

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))")
  }

  @Test
  def testDealWithMultipleConditions() {
    val input = "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet1 = min(b1, b2)" +
      "pSet2 = min(b1, b2)" +
      "cond1 = 0.5 < pSet1 " +
      "cond2 = pSet2 <= 0.5 "

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond1").synthesis(consts) should beZ3Model("(and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))")
    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testCanDoExample1InEmail() {
    val input =
      "b2 = + ((q4 0.2) (q5 0.2) (q6 0.1)) default 0.6\n" +
        "pSet = b2\n" +
        "cond = pSet <= 0.5\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(and (or q4 q5 q6) (not false))")
  }

  @Test
  def testCanDoExample2InEmail() {
    val input = "b2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2\ncond = pSet <= 0.5\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (and q6 q5 q4)))")
  }

  @Test
  def testCanDoExample3InEmail() {
    val input = "b2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\npSet = b2\ncond = pSet <= 0.2"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (or q6 (and q5 q4))))")
  }

  @Test
  def testBrokenCaseForPlus() {
    val input = "POLICIES\nb0 = max ((q4 0.41)) default 0.67\nb1 = * ((q4 0.02)) default 0.58\nb2 = + ((q5 0.54) (q3 0.06)) default 0.44\nb3 = min ((q2 0.69)) default 0.62\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\nCONDITIONS\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or (and (or (not q4) false) (and q4 (not q4))) (and (and (or q5 q3) false) (or (not q2) (not false))))")
  }

  @Test
  def testM2HandleEdgeCaseCorrectly() {
    val input = "POLICIES\nb0 = max ((q4 0.41)) default 0.67\nb1 = * ((q4 1.0) (q3 0.6)) default 0.06\nb2 = + ((q5 0.54) (q3 0.06)) default 0.44\nb3 = min ((q2 0.69)) default 0.62\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\nCONDITIONS\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or (and (or (not q4) false) (and (or q4 q3) (not q3))) (and (and (or q5 q3) false) (or (not q2) (not false))))")
  }
}
