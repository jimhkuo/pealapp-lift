package peal.synthesis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min
import peal.util.Z3ModelMatcher
import peal.domain.z3.{PealAst, Term}

class PolLessThanThTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test
  def testDefaultLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val phi = new PolLessThanThCondition(p, 0.6)

    phi.synthesis(consts) should beZ3Model("(or (not q1) q1)")
  }

  @Test
  def testDefaultLessThanThMultipleRules() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.3)), Min, 0)
    val phi = new PolLessThanThCondition(p, 0.6)

    phi.synthesis(consts) should beZ3Model("(or (and (not q1) (not q2)) (or q1 q2))")
  }

  @Test
  def testDefaultGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val phi = new PolLessThanThCondition(p, 0.6)

    phi.synthesis(consts) should beZ3Model("(and q1 q1)")
  }

  @Test
  def testDefaultGreaterThanThMultipleRules() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.3)), Min, 1)
    val phi = new PolLessThanThCondition(p, 0.6)

    phi.synthesis(consts) should beZ3Model("(and (or q1 q2) (or q1 q2))")
  }
}
