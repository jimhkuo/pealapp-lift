package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class MinLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSynthesisNestedCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new MinLessThanTh(p1, p2, 0.6)
    val phi2 = new MinLessThanTh(p1, phi1, 0.6)

    phi2.synthesis should be("(or (and q1 q1) (or (and q1 q1) (or (not q2) q2)))")
  }

  @Test
  def testCanSynthesisSimpleNestedCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new PolLessThanTh(p2, 0.6)
    val phi2 = new MinLessThanTh(p1, phi1, 0.6)

    phi2.synthesis should be("(or (and q1 q1) (or (not q2) q2))")
  }

  @Test
  def testCanSynthesisSimpleCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi = new MinLessThanTh(p1, p2, 0.6)

    phi.synthesis should be("(or (and q1 q1) (or (not q2) q2))")
  }
}
