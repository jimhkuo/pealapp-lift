package peal.verifier.util

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test


class RationalTest extends ShouldMatchersForJUnit {

  @Test
  def testCanCreate() {
      Rational("1.0","2.0") should be (Rational("1", "2"))
  }

  @Test
  def testDecimal() {
      Rational("1.5") should be (Rational(3, 2))
  }
}
