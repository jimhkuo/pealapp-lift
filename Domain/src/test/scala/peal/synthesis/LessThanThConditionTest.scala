package peal.synthesis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain._
import scala.collection.JavaConversions._
import peal.domain.operator.{Max, Mul, Plus, Min}
import peal.util.Z3ModelMatcher
import peal.domain.z3.{PealAst, Term}
import scala.Predef._
import peal.domain.BasicPolicySet
import peal.domain.MinPolicySet
import peal.domain.MaxPolicySet
import peal.domain.Pol

class LessThanThConditionTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test(expected = classOf[RuntimeException])
  def testNullThrowException() {
    new LessThanThCondition(null, 0.6)
  }

  @Test
  def testNoRules() {
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, 0.6)), 0.6).synthesis(consts) should beZ3Model ("(or true false)")
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Max, 0.5)), 0.6).synthesis(consts) should beZ3Model ("(or true (not false))")
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Plus, 0.5)), 0.6).synthesis(consts) should beZ3Model ("(or true (not false))")
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Mul, 0.5)), 0.6).synthesis(consts) should beZ3Model ("(or true false)")
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, 0.7)), 0.6).synthesis(consts) should beZ3Model ("(and false false)")
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Max, 0.7)), 0.6).synthesis(consts) should beZ3Model ("(and false (not false))")
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Plus, 0.7)), 0.6).synthesis(consts) should beZ3Model ("(and false (not false))")
    new LessThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Mul, 0.7)), 0.6).synthesis(consts) should beZ3Model ("(and false false)")
  }

  @Test
  def testSimpleMinCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet = new MinPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2))
    val phi = new LessThanThCondition(pSet, 0.6)

    phi.synthesis(consts) should beZ3Model("(or (and q1 q1) (or (not q2) q2))")
  }

  @Test
  def testCanSynthesisNestedCaseForMin() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet1 = new MinPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2))
    val pSet2 = new MinPolicySet(new BasicPolicySet(p1), pSet1)
    val phi = new LessThanThCondition(pSet2, 0.6)

    phi.synthesis(consts) should beZ3Model("(or (and q1 q1) (or (and q1 q1) (or (not q2) q2)))")
  }

  @Test
  def testCanSynthesisNestedMoreCaseForMax() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet1 = new MaxPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2))
    val pSet2 = new MaxPolicySet(new BasicPolicySet(p1), pSet1)
    val phi = new LessThanThCondition(pSet2, 0.6)
    phi.synthesis(consts) should beZ3Model("(and (and q1 q1) (and (and q1 q1) (or (not q2) q2)))")
  }
}
