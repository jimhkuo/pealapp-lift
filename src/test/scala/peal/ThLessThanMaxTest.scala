package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class ThLessThanMaxTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSynthesisSimpleNestedCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new ThLessThanPol(p2, 0.6)
    val phi2 = new ThLessThanMax(p1, phi1, 0.6)
    println(phi2.synthesis)
    phi2.synthesis should be("(or (or (not q1) (not q1)) (and q2 (not q2)))")
  }

  @Test
  def testCanSynthesisSimpleCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi = new ThLessThanMax(p1, p2, 0.6)
    println(phi.synthesis)
    phi.synthesis should be("(or (or (not q1) (not q1)) (and q2 (not q2)))")
  }
}
