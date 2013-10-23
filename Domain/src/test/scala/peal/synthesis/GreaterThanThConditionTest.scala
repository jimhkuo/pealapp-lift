package peal.synthesis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain._
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Plus, Max, Min}
import peal.util.Z3ModelMatcher
import peal.domain.MinPolicySet
import peal.domain.Pol
import peal.domain.z3.{PealAst, Term}

class GreaterThanThConditionTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test(expected = classOf[RuntimeException])
  def testNullThrowException() {
    new GreaterThanThCondition(null, Left(0.6))
  }

  @Test
  def testNoRules() {
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, 0.7)), Left(0.6)).synthesis(consts) should beZ3Model ("(or true (not false))")
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Max, 0.7)), Left(0.6)).synthesis(consts) should beZ3Model ("(or true false)")
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Plus, 0.7)), Left(0.6)).synthesis(consts) should beZ3Model ("(or true false)")
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Mul, 0.7)), Left(0.6)).synthesis(consts) should beZ3Model ("(or true (not false))")
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Min, 0.6)), Left(0.6)).synthesis(consts) should beZ3Model ("(and false (not false))")
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Max, 0.6)), Left(0.6)).synthesis(consts) should beZ3Model ("(and false false)")
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Plus, 0.6)), Left(0.6)).synthesis(consts) should beZ3Model ("(and false false)")
    new GreaterThanThCondition(new BasicPolicySet(new Pol(List[Rule](), Mul, 0.6)), Left(0.6)).synthesis(consts) should beZ3Model ("(and false (not false))")
  }

  @Test
  def testSimpleMinCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet = new MinPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2))
    val phi = new GreaterThanThCondition(pSet, Left(0.6))

    phi.synthesis(consts) should beZ3Model("(and (or (not q1) (not q1)) (and q2 (not q2)))")
  }

  @Test
  def testCanSynthesisNestedCaseForMin() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet1 = new BasicPolicySet(p2)
    val pSet2 = new MinPolicySet(new BasicPolicySet(p1), pSet1)
    val phi = new GreaterThanThCondition(pSet2, Left(0.6))

    phi.synthesis(consts) should beZ3Model("(and (or (not q1) (not q1)) (and q2 (not q2)))")
  }

  @Test
  def testCanSynthesisNestedMoreCaseForMax() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val pSet1 = new BasicPolicySet(p2)
    val pSet2 = new MaxPolicySet(new BasicPolicySet(p1), pSet1)
    val phi = new GreaterThanThCondition(pSet2, Left(0.6))
    phi.synthesis(consts) should beZ3Model("(or (or (not q1) (not q1)) (and q2 (not q2)))")
  }

  @Test
  def testBrokenCase() {
    //    POLICIES
    //    b0 = min ((q1 0.26) (q2 0.50)) default 0.07
    //    b1 = max ((q3 0.65)) default 0.32
    //    b2 = + ((q0 0.04)) default 0.63
    //    b3 = * ((q4 0.11)) default 0.64
    //    POLICY_SETS
    //    p0_1 = min(b0,b1)
    //    p2_3 = min(b2,b3)
    //    p0_3 = max(p0_1,p2_3)
    //
    //    CONDITIONS
    //    cond1 = 0.50 < p0_3

    val b0 = new Pol(List(new Rule(new Predicate("q1"), 0.26), new Rule(new Predicate("q2"), 0.50)), Min, 0.07)
    val b1 = new Pol(List(new Rule(new Predicate("q3"), 0.65)), Max, 0.32)
    val b2 = new Pol(List(new Rule(new Predicate("q0"), 0.04)), Plus, 0.63)
    val b3 = new Pol(List(new Rule(new Predicate("q4"), 0.11)), Plus, 0.64)
    val p0_1 = new MinPolicySet(new BasicPolicySet(b0), new BasicPolicySet(b1))
    val p2_3 = new MinPolicySet(new BasicPolicySet(b2), new BasicPolicySet(b3))
    val p0_3 = new MaxPolicySet(p0_1, p2_3)

    println(new ThLessThanPolSynthesiser(b0, 0.50).synthesis(consts))

    val cond1 = new GreaterThanThCondition(p0_3, Left(0.50))

    println(cond1.synthesis(consts))

  }
}
