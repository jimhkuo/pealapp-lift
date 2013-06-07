package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}
import peal.domain.{Rule, Predicate, Pol}

class NonDefaultPolLessThanThOpMinTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseScoreGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.7)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("false")
  }

  @Ignore("not confirmed")
  @Test
  def testSimpleCaseScoreEqualToTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("false")
  }

  @Test
  def testSimpleCaseScoreLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("q1")
  }

  @Test
  def testSimpleCaseScoresLessAndGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.7)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("q1")
  }

  @Test
  def testSimpleCaseMultipleScoresLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.2), new Rule(new Predicate("q3"), 0.4)), 1)
    val pSet = new NonDefaultPolLessThanThOpMin(p, 0.6)
    pSet.synthesis should be("(or q1 q2 q3)")
  }
}
