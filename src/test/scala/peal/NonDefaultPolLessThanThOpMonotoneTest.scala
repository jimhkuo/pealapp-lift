package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._


class NonDefaultPolLessThanThOpMonotoneTest extends ShouldMatchersForJUnit {

  //empty disjunction is false
  //empty conjunction is true

  @Test
  def testSimpleCaseM1IsEmpty() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultPolLessThanThOpMonotone(p, 0.6)
    //M1 is the whole set
    pSet.synthesis should be("(not false)")
  }

  @Test
  def testNotExample2InSynthesisPdf() {
    val rule5 = new Rule(new Predicate("q5"), 0.5)
    val rule3 = new Rule(new Predicate("q3"), 0.2)
    val rule4 = new Rule(new Predicate("q4"), 0.3)
    val rule2 = new Rule(new Predicate("q2"), 0.2)
    val rule1 = new Rule(new Predicate("q1"), 0.1)
    val p = new Pol(List(rule5, rule3, rule4, rule2, rule1), 1)
    val pSet = new NonDefaultPolLessThanThOpMonotone(p, 0.5)

    pSet.synthesis should be("(not (or (and q4 q2 q3) (and q4 q3 q1) (and q5 q2) (and q5 q3) (and q5 q4) (and q5 q1) (and q4 q2 q1)))")
  }

}
