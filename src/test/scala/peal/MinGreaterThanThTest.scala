package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class MinGreaterThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSynthesisSimpleCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), Min, 0)
    val phi = new MinGreaterThanTh(p1, p2, 0.6)

    phi.synthesis should be("(or (or (not q1) (not q1)) (or false (not q2)))")
  }
}
