package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ThreeWayBooleanTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDoEquality() {
    PealTrue === PealTrue should be(PealTrue)
    PealTrue === PealFalse should be(PealFalse)
    PealFalse ===  PealTrue should be(PealFalse)
    PealFalse === PealFalse should be(PealTrue)
    PealBottom === PealBottom should be(PealBottom)
  }

}
