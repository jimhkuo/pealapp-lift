package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ConditionTranslatorTest extends ShouldMatchersForJUnit {

  @Test
  def testPredicate() {
     ConditionTranslator.translate(new PredicateCondition("q")) should be ("q")
  }
}
