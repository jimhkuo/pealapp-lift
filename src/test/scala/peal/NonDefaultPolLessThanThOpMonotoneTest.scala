package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.{Rule, Predicate, Pol}


class NonDefaultPolLessThanThOpMonotoneTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseM1IsEmpty() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultPolLessThanThOpMonotone(p, 0.6)
    //M1 is the whole set
    pSet.synthesis should be("(not false)")
  }

}
