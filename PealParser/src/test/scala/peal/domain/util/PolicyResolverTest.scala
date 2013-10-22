package peal.domain.util

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain._
import peal.domain.operator.Min
import peal.domain.MinPolicySet
import scala.collection.JavaConversions._
import peal.domain.Pol
import peal.antlr.util.PolicyResolver

class PolicyResolverTest extends ShouldMatchersForJUnit{

  @Test
  def testCanResolvePolicyByName() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val polMap = Map("p1" -> p1, "p2" -> p2)
    val pSet = new MinPolicySet(new BasicPolicySet(p1), new BasicPolicySet(p2))
    val pSetMap = Map("pSet" -> pSet)

    PolicyResolver.getFromOr(polMap, pSetMap, "p1") should be (new BasicPolicySet(p1, "p1"))
    PolicyResolver.getFromOr(polMap, pSetMap, "pSet") should be (pSet)
  }

}
