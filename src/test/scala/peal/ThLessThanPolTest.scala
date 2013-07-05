package peal

import _root_.z3.scala.{Z3Config, Z3Context}
import org.junit.{After, Before, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class ThLessThanPolTest extends ShouldMatchersForJUnit {
  var z3 : Z3Context = null
  @Before def setup() {
    z3 = new Z3Context(new Z3Config("MODEL" -> true))
  }
  @After def tearDown() {
    z3.delete()
  }

  @Test
  def testThLessThanDefault() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0.7)
    val phi = new ThLessThanPol(p, 0.6)
    phi.synthesis(z3) should be("(or (not q1) (not q1))")
  }

  @Test
  def testDefaultLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val phi = new ThLessThanPol(p, 0.6)
    phi.synthesis(z3) should be("(and q1 (not q1))")
  }
}
