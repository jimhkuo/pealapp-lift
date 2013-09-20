package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class RuleTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutputRules() {
    new Rule(new Predicate("q1"), 0.5).toString should be ("(q1 0.5)")
  }
}
