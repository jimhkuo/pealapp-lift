package peal

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test


class NonDefaultThLessThanPolOpMonotoneTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleCaseM1IsWholeSet() {
    val p = new Pol(List(new Rule(new Predicate("q1"), 0.6)), 1)
    val pSet = new NonDefaultThLessThanPolOpMonotone(p, 0.6)
    //assume M1 is the whole set
    pSet.synthesis should be("q1") // in CNF
  }
}
