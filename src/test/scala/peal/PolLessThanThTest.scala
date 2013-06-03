package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class PolLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDoSimpleSynthesisWhenDefaultGreaterThanTh() {
    val q = new Predicate("q1")
    val p = new Pol(List(new Rule(q, 0.5)), 1)
    val defaultSet = new PolLessThanTh(p, 0.6)
    defaultSet.synthesis should be("") // in CNF
  }

  @Test
  def testCanDoSimpleSynthesisWhenDefaultLessThanTh() {
    val q = new Predicate("q1")
    val p = new Pol(List(new Rule(q, 0.5)), 0)
    val defaultSet = new PolLessThanTh(p, 0.6)

    defaultSet.synthesis should be("!q1 ") // in CNF

    println(defaultSet.synthesis)
  }

}
