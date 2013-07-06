package peal

import _root_.z3.scala.{Z3Config, Z3Context}
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Before, After, Ignore, Test}
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Max, Min, Plus}
import peal.util.WhitespaceMatcher

class NonDefaultPolLessThanThTest extends ShouldMatchersForJUnit with WhitespaceMatcher {

  var z3 : Z3Context = null
  @Before def setup() {
    z3 = new Z3Context(new Z3Config("MODEL" -> true))
  }
  @After def tearDown() {
    z3.delete()
  }
  
  @Test
  def testPolLessThVerificationDefaultGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.3)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis(z3) should be("(or q1 q2)")
  }

  @Test
  def testSimpleCaseScoreGreaterThanThForMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.7)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis(z3) should be("false")
  }

  @Ignore("behaviour not confirmed")
  @Test
  def testSimpleCaseScoreEqualToThForMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis(z3) should be("false")
  }

  @Test
  def testSimpleCaseScoreLessThanThDifferentDefaultForMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis(z3)should be("q1")
  }

  @Test
  def testSimpleCaseScoreLessThanThForMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis(z3)should be("q1")
  }

  @Test
  def testSimpleCaseScoresLessAndGreaterThanThForMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.7)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis(z3)should be("q1")
  }

  @Test
  def testSimpleCaseMultipleScoresLessThanThForMin() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.2), new Rule(new Predicate("q3"), 0.4)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis(z3)should be("(or q1 q2 q3)")
  }

  @Test
  def testMaxCase() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6), new Rule(new Predicate("q2"), 0.6)), Max, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.5)
    pSet.synthesis(z3)should be("(not (or q1 q2))")
  }

  @Test
  def testSimpleCaseM1IsEmptyForPlus() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Plus, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    //M1 is the whole set
    pSet.synthesis(z3)should be("(not false)")
  }

  @Test
  def testNotExample2InSynthesisPdfForPlus() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Plus, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.5)
    pSet.synthesis(z3) should beEqualIgnoreWhiteSpace ("(not (or (and q5 q4) (and q5 q2) (and q5 q3) (and q5 q1) (and q4 q2 q3) (and q4 q2 q1) (and q4 q3 q1)))")
  }

  @Test
  def testEnumTwoExample3InSynthesisPdfForMul() {
    val rule5 = new Rule(new Predicate("q5"), 0.1)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.2)
    val rule2 = new Rule(new Predicate("q2"), 0.3)
    val rule1 = new Rule(new Predicate("q1"), 0.5)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Mul, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.25)

    pSet.enumTwoForMul() should have size 4
    pSet.enumTwoForMul() should be(Set(
      Set(rule5),
      Set(rule4),
      Set(rule3),
      Set(rule1, rule2)))
  }

  @Test
  def testExample3InSynthesisPdfForMul() {
    val rule5 = new Rule(new Predicate("q5"), 0.1)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.2)
    val rule2 = new Rule(new Predicate("q2"), 0.3)
    val rule1 = new Rule(new Predicate("q1"), 0.5)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Mul, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.25)
    pSet.synthesis(z3) should be ("(or q5 q4 q3 (and q2 q1))")
  }
}
