package peal

import _root_.z3.scala.{Z3AST, Z3Config, Z3Context}
import org.junit.{After, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min
import peal.util.Z3ModelMatcher

class MaxLessThanThTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val z3: Z3Context = new Z3Context(new Z3Config("MODEL" -> true))
  val consts = Map[String, Z3AST]("q1" -> z3.mkBoolConst("q1"), "q2" -> z3.mkBoolConst("q2"), "q3" -> z3.mkBoolConst("q3"), "q4" -> z3.mkBoolConst("q4"), "q5" -> z3.mkBoolConst("q5"), "q6" -> z3.mkBoolConst("q6"))

  @After def tearDown() {
    z3.delete()
  }

  @Test
  def testCanSynthesisNestedMoreCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new MaxLessThanTh(p1, p2, 0.6)
    val phi2 = new MaxLessThanTh(p1, phi1, 0.6)
    phi2.synthesis(z3, consts) should beZ3Model("(and (and q1 q1) (and (and q1 q1) (or (not q2) q2)))")
    z3.delete()
  }

  @Test
  def testCanSynthesisNestedCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi1 = new PolLessThanTh(p2, 0.6)
    val phi2 = new MaxLessThanTh(p1, phi1, 0.6)

    //    println(phi2.synthesis)
    phi2.synthesis(z3, consts) should beZ3Model("(and (and q1 q1) (or (not q2) q2))")
    z3.delete()
  }

  @Test
  def testCanSynthesisSimpleCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi = new MaxLessThanTh(p1, p2, 0.6)

    phi.synthesis(z3, consts) should beZ3Model("(and (and q1 q1) (or (not q2) q2))")
    z3.delete()
  }
}
