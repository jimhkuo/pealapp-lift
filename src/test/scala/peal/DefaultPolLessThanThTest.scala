package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min


class DefaultPolLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDoSimpleSynthesisWhenDefaultGreaterThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val defaultSet = new DefaultPolLessThanTh(p, 0.6)
    defaultSet.synthesis should be("false")
  }

  @Test
  def testCanDoSimpleSynthesisWhenDefaultLessThanTh() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val defaultSet = new DefaultPolLessThanTh(p, 0.6)

    defaultSet.synthesis should be("(not q1)")
  }

  @Test
  def testCanDoSimpleSynthesisWhenDefaultLessThanThTwoRules() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.3)), Min, 0)
    val defaultSet = new DefaultPolLessThanTh(p, 0.6)

    defaultSet.synthesis should be("(and (not q1) (not q2))")
  }
}
