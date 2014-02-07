package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import scala.collection.JavaConversions._
import peal.domain._
import peal.domain.operator.Min
import peal.antlr.util.ParserHelper
import peal.domain.BasicPolicySet
import peal.domain.Pol
import scala.Some

class ConditionTranslatorTest extends ShouldMatchersForJUnit {

  val conds = Map("cond1" -> PredicateCondition("q"),
    "cond2" -> NotCondition("cond1"))

  @Test
  def testPredicate() {
    ConditionTranslator.translate(new PredicateCondition("q"), conds) should be("q")
  }

  @Test
  def testNot() {
    ConditionTranslator.translate(new NotCondition("cond1"), conds) should be("(not q)")
  }

  @Test
  def testOr() {
    ConditionTranslator.translate(new OrCondition("cond1", "cond2"), conds) should be("(or q (not q))")
  }

  @Test
  def testAnd() {
    ConditionTranslator.translate(new AndCondition("cond1", "cond2"), conds) should be("(and q (not q))")
  }

  @Test
  def testLessThanForPolicySets() {
    ConditionTranslator.translate(new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.6), None), "b1")), Right(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.4), None), "b2")))), conds) should be("(<= b1_score b2_score)")
  }

  @Test
  def testLessThanForPolicySetAndNumber() {
    ConditionTranslator.translate(new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.6), None), "b1")), Left(0.5)), conds) should be("(<= b1_score 0.5)")
  }

  @Test
  def testLessThanForPolicySetAndNumberFromInput() {
    val input = "POLICIES\nb1 = + () default 0.5\nb2 = +() default 0.4 [-0.1, 0.3]\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= pSet2"
    println(input)
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    pealProgramParser.pols("b2").score.underlyingScore should be (Left(BigDecimal(0.4)))
    pealProgramParser.pols("b2").score.scoreRange.get.minValue should be (BigDecimal(-0.1))
    pealProgramParser.pols("b2").score.scoreRange.get.maxValue should be (BigDecimal(0.3))
    ConditionTranslator.translate(pealProgramParser.conds("cond1"), pealProgramParser.conds.toMap) should be("(<= b1_score b2_score)")
  }
}
