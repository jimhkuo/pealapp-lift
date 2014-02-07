package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ConditionTranslatorTest extends ShouldMatchersForJUnit {

  val conds = Map("cond1" -> PredicateCondition("q"))

  @Test
  def testPredicate() {
     ConditionTranslator.translate(new PredicateCondition("q"), conds) should be ("q")
  }

  @Test
  def testNot() {
    ConditionTranslator.translate(new NotCondition("cond1"), conds) should be ("(not q)")
  }
}
