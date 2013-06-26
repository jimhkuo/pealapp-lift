package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rule, Predicate, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class ThLessThanPolTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseScoreLessThanThDifferentDefault() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0)
    val phi = new ThLessThanPol(p, 0.6)
    phi.synthesis should be("(or false (not q1))")
  }

  @Test
  def testCanSynthesisSimpleCase() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 0.7)
    val phi = new ThLessThanPol(p, 0.6)
    phi.synthesis should be("(or (not q1) (not q1))")
  }
}
