package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class PolLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDoSimpleSynthesisWhenDefaultGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 1)
    val defaultSet = new PolLessThanTh(p, 0.6)
    defaultSet.synthesis should be("") // in CNF
  }

  @Test
  def testCanDoSimpleSynthesisWhenDefaultLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 0)
    val defaultSet = new PolLessThanTh(p, 0.6)

    defaultSet.synthesis should be("!q1") // in CNF
  }

  @Test
  def testCanDoSimpleSynthesisWhenDefaultLessThanThTwoRules() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.3)), 0)
    val defaultSet = new PolLessThanTh(p, 0.6)

    defaultSet.synthesis should be("!q1 !q2") // in CNF
  }

}
