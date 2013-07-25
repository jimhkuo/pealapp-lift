package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.operator.Min
import scala.collection.JavaConversions._


class PolTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutput() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)

    p1.toString should be("min ((q1 0.5)) default 1.0")
  }

}
