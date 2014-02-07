package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import scala.collection.JavaConversions._
import peal.domain._
import peal.domain.operator.Min
import peal.antlr.util.ParserHelper
import peal.domain.BasicPolicySet
import peal.domain.Pol

class ConditionTranslatorTest extends ShouldMatchersForJUnit {

  val conds = Map("cond1" -> PredicateCondition("q"),
    "cond2" -> NotCondition("cond1"),
    "cond3" -> new OrCondition("cond1", "cond2"),
    "cond4" -> new AndCondition("cond1", "cond2"),
    "cond5" -> new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.6), None), "b1")), Right(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.4), None), "b2")))),
    "cond6" -> new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.6), None), "b1")), Right(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.4), None), "b2")))),
    "cond7" -> new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.6), None), "b1")), Left(0.5)),
    "cond8" -> new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.6), None), "b1")), Left(0.5))
  )

  @Test
  def testPredicate() {
    ConditionTranslator.translate("cond1", conds) should be("q")
  }

  @Test
  def testNot() {
    ConditionTranslator.translate("cond2", conds) should be("(not q)")
  }

  @Test
  def testOr() {
    ConditionTranslator.translate("cond3", conds) should be("(or q (not q))")
  }

  @Test
  def testAnd() {
    ConditionTranslator.translate("cond4", conds) should be("(and q (not q))")
  }

  @Test
  def testLessThanForPolicySets() {
    ConditionTranslator.translate("cond5", conds) should be("(<= b1_score b2_score)")
  }

  @Test
  def testGreaterThanForPolicySets() {
    ConditionTranslator.translate("cond6", conds) should be("(< b2_score b1_score)")
  }

  @Test
  def testLessThanForPolicySetAndNumber() {
    ConditionTranslator.translate("cond7", conds) should be("(<= b1_score 0.5)")
  }

  @Test
  def testGreaterThanForPolicySetAndNumber() {
    ConditionTranslator.translate("cond8", conds) should be("(< 0.5 b1_score)")
  }

  @Test
  def testLessThanForPolicySetAndNumberFromInput() {
    val input = "POLICIES\nb1 = + () default 0.5\nb2 = +() default 0.4 [-0.1, 0.3]\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= pSet2"
    println(input)
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    pealProgramParser.pols("b2").score.underlyingScore should be(Left(BigDecimal(0.4)))
    pealProgramParser.pols("b2").score.scoreRange.get.minValue should be(BigDecimal(-0.1))
    pealProgramParser.pols("b2").score.scoreRange.get.maxValue should be(BigDecimal(0.3))
    ConditionTranslator.translate("cond1", pealProgramParser.conds.toMap) should be("(<= b1_score b2_score)")
  }

  @Test
  def testGreaterThanForPolicySetAndNumberFromInput() {
    val input = "POLICIES\nb1 = + () default 0.5\nb2 = +() default 0.4 [-0.1, 0.3]\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet2 < pSet1"
    println(input)
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    pealProgramParser.pols("b2").score.underlyingScore should be(Left(BigDecimal(0.4)))
    pealProgramParser.pols("b2").score.scoreRange.get.minValue should be(BigDecimal(-0.1))
    pealProgramParser.pols("b2").score.scoreRange.get.maxValue should be(BigDecimal(0.3))
    ConditionTranslator.translate("cond1", pealProgramParser.conds.toMap) should be("(< b2_score b1_score)")
  }

  @Test
  def testMax() {
    val input = "POLICIES\nb1 = + () default 0.5\nb2 = +() default 0.4 [-0.1, 0.3]\nPOLICY_SETS\npSet1 = max(b1, b2)\nCONDITIONS\ncond1 = 0.5 < pSet1"
    println(input)
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    ConditionTranslator.translate("cond1", pealProgramParser.conds.toMap) should be("(< 0.5 (ite (< b1_score b2_score) b2_score b1_score))")
  }

  @Test
  def testMin() {
    val input = "POLICIES\nb1 = + () default 0.5\nb2 = +() default 0.4 [-0.1, 0.3]\nPOLICY_SETS\npSet1 = min(b1, b2)\nCONDITIONS\ncond1 = 0.5 < pSet1"
    println(input)
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    ConditionTranslator.translate("cond1", pealProgramParser.conds.toMap) should be("(< 0.5 (ite (< b1_score b2_score) b1_score b2_score))")
  }

  @Test
  def testPlus() {
    val input = "POLICIES\nb1 = + () default 0.5\nb2 = +() default 0.4 [-0.1, 0.3]\nPOLICY_SETS\npSet1 = + (b1, b2)\nCONDITIONS\ncond1 = 0.5 < pSet1"
    println(input)
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    ConditionTranslator.translate("cond1", pealProgramParser.conds.toMap) should be("(< 0.5 (+ b1_score b2_score))")
  }

  @Test
  def testMul() {
    val input = "POLICIES\nb1 = + () default 0.5\nb2 = +() default 0.4 [-0.1, 0.3]\nPOLICY_SETS\npSet1 = * (b1, b2)\nCONDITIONS\ncond1 = 0.5 < pSet1"
    println(input)
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    ConditionTranslator.translate("cond1", pealProgramParser.conds.toMap) should be("(< 0.5 (* b1_score b2_score))")
  }
}
