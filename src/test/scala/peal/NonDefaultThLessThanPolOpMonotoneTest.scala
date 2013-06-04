package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test


class NonDefaultThLessThanPolOpMonotoneTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseM1IsWholeSet() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.6)
    //assume M1 is the whole set
    pSet.synthesis should be("q1") // in CNF
  }

  @Test
  def testCanComputeM1AsPerExample2InSynthesisPdf() {
    val p = new Pol(List(new Rule(new Predicate("q5"), 0.5),
      new Rule(new Predicate("q3"), 0.2),
      new Rule(new Predicate("q4"), 0.3),
      new Rule(new Predicate("q2"), 0.2),
      new Rule(new Predicate("q1"), 0.1)), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.5)

    pSet.enumOne() should be(Set(Set("q4", "q5"),
      Set("q3", "q5"),
      Set("q2", "q5"),
      Set("q2", "q3", "q4"),
      Set("q1", "q3", "q4"),
      Set("q1", "q2", "q4")))
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
}
