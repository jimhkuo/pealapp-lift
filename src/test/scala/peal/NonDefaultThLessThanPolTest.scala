package peal

import _root_.z3.scala.{Z3Config, Z3Context}
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{After, Before, Test}
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Min, Max, Plus}
import scala.collection.mutable.ListBuffer
import org.scalatest.matchers.{MatchResult, Matcher}


class NonDefaultThLessThanPolTest extends ShouldMatchersForJUnit {

  var z3: Z3Context = null
  val beEqualIgnoreWhiteSpace = (expected: String) => new EqualsIgnoreWhiteSpaceWord(expected)

  @Before def setup() {
    z3 = new Z3Context(new Z3Config("MODEL" -> true))
  }

  @After def tearDown() {
    z3.delete()
  }

  @Test
  def testSimpleCaseM1IsEmptyBecauseSumLessTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.4)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is empty
    pSet.synthesis(z3) should be("false")
  }

  @Test
  def testSimpleCaseM1IsEmptyBecauseSumEqualTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is empty
    pSet.synthesis(z3) should be("false")
  }

  @Test
  def testSimpleCaseM1IsWholeSet() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is the whole set
    pSet.synthesis(z3) should be("q1")
  }

  @Test
  def testSimpleNot() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Plus, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    //M1 is the whole set
    pSet.notPhi(z3) should be("(not q1)")
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

    pSet.synthesis(z3) should be(beEqualIgnoreWhiteSpace("(or (and q5 q4) (and q5 q2) (and q5 q3) (and q5 q1) (and q4 q2 q3) (and q4 q2 q1) (and q4 q3 q1))"))
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

    pSet.notPhi(z3) should be("(not (or (and q5 q4)\n    (and q5 q2)\n    (and q5 q3)\n    (and q5 q1)\n    (and q4 q2 q3)\n    (and q4 q2 q1)\n    (and q4 q3 q1)))")
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

    sortedP should be("q1,q2,q3,q4,q5")
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
    pSet.synthesis(z3) should be("false")
  }

  @Test
  def testOneScoreGreaterThanThMax() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Max, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    pSet.synthesis(z3) should be("q1")
  }

  @Test
  def testMultipleScoresGreaterThanThMax() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6), new Rule(new Predicate("q2"), 0.6)), Max, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.5)
    pSet.synthesis(z3) should be("(or q1 q2)")
  }

  // phi^ndf_min[th < pol] = phi^ndf_antitone[th < pol] = !phi^ndf_min[pol <= th]
  @Test
  def testSimpleMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.7)), Min, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.6)
    pSet.synthesis(z3) should be("(not false)")
  }

  @Test
  def testSimpleCaseScoreLessThanThDifferentDefaultMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val pSet = new NonDefaultThLessThanPol(p, 0.6)
    pSet.synthesis(z3) should be("(not q1)")
  }

  @Test
  def testMultipleScoresMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.2), new Rule(new Predicate("q3"), 0.4)), Min, 1)
    val pSet = new NonDefaultThLessThanPol(p, 0.6)
    pSet.synthesis(z3) should be("(not (or q1 q2 q3))")
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

    pSet.synthesis(z3) should be("(not (or q5 q4 q3 (and q2 q1)))")

  }
}

class EqualsIgnoreWhiteSpaceWord(expected: String) extends Matcher[String] {
  def apply(actual: String): MatchResult = MatchResult(actual.replaceAll("\n", " ").replaceAll("\\s+", " ").equals(expected),
    actual + " was not equal to "+ expected,
    actual + " was equal to "+ expected)
}
