package peal

import _root_.z3.scala.{Z3AST, Z3Config, Z3Context}
import org.junit.{After, Before, Ignore, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min
import peal.util.Z3ModelMatcher

class PolLessThanThTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val z3: Z3Context = new Z3Context(new Z3Config("MODEL" -> true))
  val consts = Map[String, Z3AST]("q1" -> z3.mkBoolConst("q1"), "q2" -> z3.mkBoolConst("q2"), "q3" -> z3.mkBoolConst("q3"), "q4" -> z3.mkBoolConst("q4"), "q5" -> z3.mkBoolConst("q5"), "q6" -> z3.mkBoolConst("q6"))

  @After def tearDown() {
    z3.delete()
  }

  @Test
  def testDefaultLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val phi = new PolLessThanTh(p, 0.6)

    phi.synthesis(z3, consts) should beZ3Model("(or (not q1) q1)")
  }

  @Test
  def testDefaultLessThanThMultipleRules() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.3)), Min, 0)
    val phi = new PolLessThanTh(p, 0.6)

    phi.synthesis(z3, consts) should beZ3Model("(or (and (not q1) (not q2)) (or q1 q2))")
  }

  @Test
  def testDefaultGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val phi = new PolLessThanTh(p, 0.6)

    phi.synthesis(z3, consts) should beZ3Model("(and q1 q1)")
  }

  @Test
  def testDefaultGreaterThanThMultipleRules() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.3)), Min, 1)
    val phi = new PolLessThanTh(p, 0.6)

    phi.synthesis(z3, consts) should beZ3Model("(and (or q1 q2) (or q1 q2))")
  }
}
