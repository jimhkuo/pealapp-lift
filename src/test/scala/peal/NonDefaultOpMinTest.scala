package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class NonDefaultOpMinTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseScoreGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.7)), 1)
    val pSet = new NonDefaultOpMin(p, 0.6)
    pSet.synthesis should be("") // in CNF
  }

  @Test
  def testSimpleCaseScoreLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 1)
    val pSet = new NonDefaultOpMin(p, 0.6)
    pSet.synthesis should be("q1") // in CNF
  }
}
