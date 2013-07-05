package peal

import _root_.z3.scala.{Z3Config, Z3Context}
import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class MaxLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSynthesisNestedMoreCase() {
    val z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new MaxLessThanTh(p1, p2, 0.6)
    val phi2 = new MaxLessThanTh(p1, phi1, 0.6)
    phi2.synthesis(z3) should be("(and (and q1 q1) (and (and q1 q1) (or (not q2) q2)))")
    z3.delete()
  }

  @Test
  def testCanSynthesisNestedCase() {
    val z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new PolLessThanTh(p2, 0.6)
    val phi2 = new MaxLessThanTh(p1, phi1, 0.6)

//    println(phi2.synthesis)
    phi2.synthesis(z3) should be("(and (and q1 q1) (or (not q2) q2))")
    z3.delete()
  }

  @Test
  def testCanSynthesisSimpleCase() {
    val z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi = new MaxLessThanTh(p1, p2, 0.6)

    phi.synthesis(z3) should be("(and (and q1 q1) (or (not q2) q2))")
    z3.delete()
  }
}
