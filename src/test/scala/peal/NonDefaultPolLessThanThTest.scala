package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.{Max, Min, Plus}

class NonDefaultPolLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseScoreGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.7)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis should be("false")
  }

  @Ignore("behaviour not confirmed")
  @Test
  def testSimpleCaseScoreEqualToTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis should be("false")
  }

  @Test
  def testSimpleCaseScoreLessThanThDifferentDefault() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis should be("q1")
  }

  @Test
  def testSimpleCaseScoreLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis should be("q1")
  }

  @Test
  def testSimpleCaseScoresLessAndGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.7)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis should be("q1")
  }

  @Test
  def testSimpleCaseMultipleScoresLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.2), new Rule(new Predicate("q3"), 0.4)), Min, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    pSet.synthesis should be("(or q1 q2 q3)")
  }

  @Test
  def testMaxCase() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6), new Rule(new Predicate("q2"), 0.6)), Max, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.5)
    pSet.synthesis should be("(not (or q1 q2))")
  }

  @Test
  def testSimpleCaseM1IsEmptyForPlus() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), Plus, 1)
    val pSet = new NonDefaultPolLessThanTh(p, 0.6)
    //M1 is the whole set
    pSet.synthesis should be("(not false)")
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

    pSet.synthesis should be("(not (or (and q4 q2 q3) (and q4 q3 q1) (and q5 q2) (and q5 q3) (and q5 q4) (and q5 q1) (and q4 q2 q1)))")
  }

  //TODO M2 computation only works for * operator
  @Ignore("wip")
  @Test
  def testExample3InSynthesisPdfAntitone() {
    val rule5 = new Rule(new Predicate("q5"), 0.1)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.2)
    val rule2 = new Rule(new Predicate("q2"), 0.3)
    val rule1 = new Rule(new Predicate("q1"), 0.5)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), Min, 1)
    val pSet = new NonDefaultPolLessThanThOpMul(p, 0.25)

    pSet.synthesis should be("(or q5 q4 q3 q2 q1)")
  }
}
