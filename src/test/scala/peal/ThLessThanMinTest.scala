package peal

import _root_.z3.scala.{Z3Config, Z3Context}
import org.junit.{After, Before, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class ThLessThanMinTest extends ShouldMatchersForJUnit {
  var z3 : Z3Context = null
  @Before def setup() {
    z3 = new Z3Context(new Z3Config("MODEL" -> true))
  }
  @After def tearDown() {
    z3.delete()
  }

  @Test
  def testCanSynthesisSimpleNestedCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new ThLessThanPol(p2, 0.6)
    val phi2 = new ThLessThanMin(p1, phi1, 0.6)
    phi2.synthesis(z3) should be("(and (or (not q1) (not q1)) (and q2 (not q2)))")
  }

  @Test
  def testCanSynthesisSimpleCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi = new ThLessThanMin(p1, p2, 0.6)
    phi.synthesis(z3) should be("(and (or (not q1) (not q1)) (and q2 (not q2)))")
  }
}
