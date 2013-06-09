package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}

class PolLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSynthesisSimpleCase() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 1)
    val phi = new PolLessThanTh(p, 0.6)

    phi.synthesis should be("(or false q1)")
  }

  @Test
  def testSimpleCaseScoreLessThanThDifferentDefault() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), 0)
    val phi = new PolLessThanTh(p, 0.6)

    phi.synthesis should be("(or (not q1) q1)")
  }


}
