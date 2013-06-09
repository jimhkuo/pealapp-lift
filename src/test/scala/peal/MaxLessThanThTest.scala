package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._

class MaxLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSynthesisSimpleCase() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.5)), 0)
    val phi = new MaxLessThanTh(p1, p2, 0.6)

    phi.synthesis should be("(and (or false q1) (or (not q2) q2))")
  }
}
