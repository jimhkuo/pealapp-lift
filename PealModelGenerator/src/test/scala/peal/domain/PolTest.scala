package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.operator._
import scala.collection.JavaConversions._


class PolTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutput() {
    new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1).toString should be("min ((q1 0.5)) default 1.0")
    new Pol(List(new Rule(new Predicate("q2"), 0.1)), Max, 0.5).toString should be("max ((q2 0.1)) default 0.5")
    new Pol(List(new Rule(new Predicate("q3"), 0.5)), Plus, 1).toString should be("+ ((q3 0.5)) default 1.0")
    new Pol(List(new Rule(new Predicate("q4"), 0.5)), Mul, 1).toString should be("* ((q4 0.5)) default 1.0")
  }

}
