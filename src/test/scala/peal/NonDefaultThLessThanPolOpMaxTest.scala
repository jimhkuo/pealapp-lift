package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._


class NonDefaultThLessThanPolOpMaxTest extends ShouldMatchersForJUnit {

  @Test
  def testNoScoreGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.4)), 1)
    val pSet = new NonDefaultThLessThanPolOpMax(p, 0.5)
    pSet.synthesis should be("false")
  }

  @Test
  def testOneScoreGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultThLessThanPolOpMax(p, 0.5)
    pSet.synthesis should be("q1")
  }

  @Test
  def testMultipleScoresGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6), new Rule(new Predicate("q2"), 0.6)), 1)
    val pSet = new NonDefaultThLessThanPolOpMax(p, 0.5)
    pSet.synthesis should be("(or q1 q2)")
  }

}
