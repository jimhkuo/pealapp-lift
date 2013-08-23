package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Min, Max, Plus}
import scala.collection.mutable.ListBuffer
import peal.util.Z3ModelMatcher
import peal.domain.z3.{PealAst, Term}


class NonDefaultThLessThanPolTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test
  def testSimpleCaseM1IsEmptyBecauseSumLessTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.4)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is empty
    pSet.synthesis(consts) should beZ3Model("false")
  }

  @Test
  def testSimpleCaseM1IsEmptyBecauseSumEqualTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is empty
    pSet.synthesis(consts) should beZ3Model("false")
  }

  @Test
  def testSimpleCaseM1IsWholeSet() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is the whole set
    pSet.synthesis(consts) should beZ3Model("q1")
  }

  @Test
  def testSimpleNot() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is the whole set
    pSet.notPhi( consts) should beZ3Model("(not q1)")
  }

  @Test
  def testExample2InSynthesisPdf() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)

    pSet.synthesis(consts) should beZ3Model("(or (and q5 q4) (and q5 q2) (and q5 q3) (and q5 q1) (and q4 q2 q3) (and q4 q2 q1) (and q4 q3 q1))")
  }

  @Test
  def testNotExample2InSynthesisPdf() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)

    pSet.notPhi( consts) should beZ3Model("(not (or (and q5 q4) (and q5 q2) (and q5 q3) (and q5 q1) (and q4 q2 q3) (and q4 q2 q1) (and q4 q3 q1)))")
  }

  @Test
  def testComputeM1AsPerExample2InSynthesisPdf() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)

    pSet.enumOneForPlus() should have size (7)
    pSet.enumOneForPlus() should be(ListBuffer(
      List(rule5, rule4),
      List(rule5, rule2),
      List(rule5, rule3),
      List(rule5, rule1),
      List(rule4, rule2, rule3),
      List(rule4, rule2, rule1),
      List(rule4, rule3, rule1)))
  }

  @Test
  def testRuleSorting() {
    val p = new Pol(List(new Rule(new Predicate("q5"), 0.5),
      new Rule(new Predicate("q2"), 0.2),
      new Rule(new Predicate("q4"), 0.3),
      new Rule(new Predicate("q3"), 0.2),
      new Rule(new Predicate("q1"), 0.1)), Plus, 1)

    val sortedP = p.rules.sortBy(_.score).map(_.q.name).mkString(",")

    sortedP should beZ3Model("q1,q2,q3,q4,q5")
  }

  @Test
  def testComputePartialSum() {
    val s = List(0.1, 0.2, 0.2, 0.3, 0.5)
    val t = List(0.1, 0.3, 0.5, 0.8, 1.3)
    val c = s.scanLeft(0.0)((remaining, item) => remaining + item).drop(1).map(BigDecimal(_).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)
    c should be(t)
  }

  @Test
  def testNoScoreGreaterThanThMax() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.4)), Max, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    pSet.synthesis(consts) should beZ3Model("false")
  }

  @Test
  def testOneScoreGreaterThanThMax() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Max, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    pSet.synthesis(consts) should beZ3Model("q1")
  }

  @Test
  def testMultipleScoresGreaterThanThMax() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6), new Rule(new Predicate("q2"), 0.6)), Max, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    pSet.synthesis(consts) should beZ3Model("(or q1 q2)")
  }

  // phi^ndf_min[th < pol] = phi^ndf_antitone[th < pol] = !phi^ndf_min[pol <= th]
  @Test
  def testSimpleMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.7)), Min, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.6)
    pSet.synthesis(consts) should beZ3Model("(not false)")
  }

  @Test
  def testSimpleCaseScoreLessThanThDifferentDefaultMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val pSet = new NonDefaultThLessThanPol(p, 0.6)
    pSet.synthesis(consts) should beZ3Model("(not q1)")
  }

  @Test
  def testMultipleScoresMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.2), new Rule(new Predicate("q3"), 0.4)), Min, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.6)
    pSet.synthesis(consts) should beZ3Model("(not (or q1 q2 q3))")
  }

  @Test
  def testSimpleMul() {
    val rule5 = new Rule(new Predicate("q5"), 0.1)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.2)
    val rule2 = new Rule(new Predicate("q2"), 0.3)
    val rule1 = new Rule(new Predicate("q1"), 0.5)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Mul, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.25)

    pSet.synthesis(consts) should beZ3Model("(not (or q5 q4 q3 (and q2 q1)))")

  }
}


