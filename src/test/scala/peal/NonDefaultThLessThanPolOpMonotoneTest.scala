package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.{Rule, Predicate, Pol}


class NonDefaultThLessThanPolOpMonotoneTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseM1IsEmpty() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.4)), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.5)
    //M1 is the whole set
    pSet.synthesis should be("false")
  }

  @Test
  def testSimpleCaseM1IsWholeSet() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.5)
    //M1 is the whole set
    pSet.synthesis should be("(q1)")
  }

  @Test
  def testSimpleNot() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.5)
    //M1 is the whole set
    pSet.not should be("(not (q1))")
  }

  @Test
  def testExample2InSynthesisPdf() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.5)

    pSet.synthesis should be("(or (and q4 q2 q3) (and q4 q3 q1) (and q5 q2) (and q5 q3) (and q5 q4) (and q5 q1) (and q4 q2 q1))")
  }

  @Test
  def testNotExample2InSynthesisPdf() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.5)

    pSet.not should be("(not (or (and q4 q2 q3) (and q4 q3 q1) (and q5 q2) (and q5 q3) (and q5 q4) (and q5 q1) (and q4 q2 q1)))")
    //    pSet.not should be("(and (or (not q4) (not q2) (not q1)) (or (not q5) (not q2)) (or (not q5) (not q1)) (or (not q5) (not q4)) (or (not q4) (not q2) (not q3)) (or (not q4) (not q3) (not q1)) (or (not q5) (not q3)))")
  }

  @Test
  def testComputeM1AsPerExample2InSynthesisPdf() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.5)

    pSet.enumOne() should have size (7)
    pSet.enumOne() should be(Set(
      Set(rule1, rule3, rule4),
      Set(rule4, rule5),
      Set(rule3, rule5),
      Set(rule2, rule5),
      Set(rule1, rule5),
      Set(rule2, rule3, rule4),
      Set(rule1, rule2, rule4)))
  }

  @Test
  def testRuleSorting() {
    val p = new Pol(List(new Rule(new Predicate("q5"), 0.5),
      new Rule(new Predicate("q2"), 0.2),
      new Rule(new Predicate("q4"), 0.3),
      new Rule(new Predicate("q3"), 0.2),
      new Rule(new Predicate("q1"), 0.1)), 1)

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
}
