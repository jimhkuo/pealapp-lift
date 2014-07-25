package peal.expression

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain._
import peal.domain.operator.Min
import peal.synthesis.GreaterThanThCondition
import scala.collection.JavaConversions._


class ConditionExpressionBuilderTest extends ShouldMatchersForJUnit {

  @Test
  def testCanBuildGreaterThanExpression() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), "0.5")), Min, "1")
    val p2 = new Pol(List(new Rule(new Predicate("q2"), "0.5")), Min, "0")
    val pSet1 = new MinPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2), "pSet1")
    val pSet2 = new MaxPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2), "pSet2")
    val cond1 = new GreaterThanThCondition(pSet1, Left(0.6))
    val cond2 = new GreaterThanThCondition(pSet1, Right(pSet2))

    ConditionExpressionBuilder.build(cond1) should be(pSet1.getPolicySetName + " > 0.6")
    ConditionExpressionBuilder.build(cond2) should be(pSet1.getPolicySetName + " > " + pSet2.getPolicySetName)
  }
}
