package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}
import scala.collection.JavaConversions._
import peal.domain.{Score, Rule, Pol, BasicPolicySet}
import peal.domain.operator.Min

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

  @Ignore("not done")
  @Test
  def testLessThanForPolicySetAndNumber() {
    ConditionTranslator.translate(new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, new Score(Left(0.6), None), "b1")), Left(0.5)), conds) should be("(<= b1_score 0.5)")
  }
}
