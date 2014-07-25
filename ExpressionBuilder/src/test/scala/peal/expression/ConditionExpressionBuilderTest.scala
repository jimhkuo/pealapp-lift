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
    val pSet = new MinPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2), "pSet")
    val phi = new GreaterThanThCondition(pSet, Left(0.6))

    ConditionExpressionBuilder.build(phi) should be(pSet.getPolicySetName + " > 0.6")
  }
}
