package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ThreeWayBooleanTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDoEquality() {
    PealTrue === PealTrue should be(PealTrue)
    PealTrue === PealFalse should be(PealFalse)
    PealFalse === PealTrue should be(PealFalse)
    PealFalse === PealFalse should be(PealTrue)
    PealBottom === PealBottom should be(PealBottom)
  }

  @Test
  def testCheckConjunction() {
    PealTrue && PealTrue should be(PealTrue)
    PealTrue && PealFalse should be(PealFalse)
    PealTrue && PealBottom should be(PealBottom)
    PealFalse && PealTrue should be(PealFalse)
    PealFalse && PealFalse should be(PealFalse)
    PealFalse && PealBottom should be(PealFalse)
    PealBottom && PealBottom should be(PealBottom)
    PealBottom && PealTrue should be(PealBottom)
    PealBottom && PealFalse should be(PealFalse)
  }

  @Test
  def testCheckDisjunction() {
    PealTrue || PealTrue should be(PealTrue)
    PealTrue || PealFalse should be(PealTrue)
    PealTrue || PealBottom should be(PealTrue)
    PealFalse || PealTrue should be(PealTrue)
    PealFalse || PealFalse should be(PealFalse)
    PealFalse || PealBottom should be(PealBottom)
    PealBottom || PealBottom should be(PealBottom)
    PealBottom || PealTrue should be(PealTrue)
    PealBottom || PealFalse should be(PealBottom)
  }
}
