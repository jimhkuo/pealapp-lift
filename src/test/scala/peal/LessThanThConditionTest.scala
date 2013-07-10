package peal

import _root_.z3.scala.{Z3AST, Z3Config, Z3Context}
import org.junit.{After, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain._
import scala.collection.JavaConversions._
import peal.domain.operator.Min
import peal.util.Z3ModelMatcher
import peal.domain.MinPolicySet
import peal.domain.Pol

class LessThanThConditionTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val z3: Z3Context = new Z3Context(new Z3Config("MODEL" -> true))
  val consts = Map[String, Z3AST]("q1" -> z3.mkBoolConst("q1"), "q2" -> z3.mkBoolConst("q2"), "q3" -> z3.mkBoolConst("q3"), "q4" -> z3.mkBoolConst("q4"), "q5" -> z3.mkBoolConst("q5"), "q6" -> z3.mkBoolConst("q6"))

  @After def tearDown() {
    z3.delete()
  }

  @Test
  def testSimpleMinCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet = new MinPolicySet(new PolPolicySet(p1), new PolPolicySet(p2))
    val phi = new LessThanThCondition(pSet, 0.6)

    phi.synthesis(z3,consts) should beZ3Model("(or (and q1 q1) (or (not q2) q2))")
  }

  @Test
  def testCanSynthesisNestedCaseForMin() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet1 = new MinPolicySet(new PolPolicySet(p1), new PolPolicySet(p2))
    val pSet2 = new MinPolicySet(new PolPolicySet(p1), pSet1)
    val phi = new LessThanThCondition(pSet2, 0.6)

    phi.synthesis(z3,consts) should beZ3Model("(or (and q1 q1) (or (and q1 q1) (or (not q2) q2)))")
  }

  @Test
  def testCanSynthesisNestedMoreCaseForMax() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet1 = new MaxPolicySet(new PolPolicySet(p1), new PolPolicySet(p2))
    val pSet2 = new MaxPolicySet(new PolPolicySet(p1), pSet1)
    val phi = new LessThanThCondition(pSet2, 0.6)
    phi.synthesis(z3, consts) should beZ3Model("(and (and q1 q1) (and (and q1 q1) (or (not q2) q2)))")
  }
}
