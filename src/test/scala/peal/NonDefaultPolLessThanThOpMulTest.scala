package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class NonDefaultPolLessThanThOpMulTest extends ShouldMatchersForJUnit {

  //TODO M2 computation only works for * operator
  @Ignore("wip")
  @Test
  def testExample3InSynthesisPdf() {
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
