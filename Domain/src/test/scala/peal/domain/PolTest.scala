package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import scala.collection.JavaConversions._
import peal.domain.operator.Min

class PolTest extends ShouldMatchersForJUnit {

  @Test
  def testCanTakeVariable() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, Right(Variable("x")))

  }

}
