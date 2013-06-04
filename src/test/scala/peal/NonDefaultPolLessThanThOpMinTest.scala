package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class NonDefaultPolLessThanThOpMinTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseScoreGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.7)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("") // in CNF
  }

  @Test
  def testSimpleCaseScoreLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("q1") // in CNF
  }

  @Test
  def testSimpleCaseMultipleScoresLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.2), new Rule(new Predicate("q3"), 0.4)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("q1\nq2\nq3") // in CNF
  }
}
