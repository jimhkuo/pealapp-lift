package peal.verifier.util

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test


class RationalTest extends ShouldMatchersForJUnit {

  @Test
  def testEquivalency() {
    Rational("-1.5") should be(Rational(-3, 2))
    Rational("1.5","-1") should be(Rational(-3, 2))
    Rational("1.0", "2.0") should be(Rational("1", "2"))
    Rational("1.5") should be(Rational("3", "2"))
    Rational("-1.5") should be(Rational("-3", "2"))
    Rational("-1.5") should be(Rational("3", "-2"))
    Rational("3", "2") should be(Rational("3.0", "2"))
    Rational("3", "2") should be(Rational("1.5", "1.0"))
    Rational("30", "20") should be(Rational("15", "10"))
  }

  @Test
  def testMax() {
    Rational("1.5").max(Rational("1")) should be (Rational("1.5"))
    Rational("15", "10").max(Rational("10", "9")) should be (Rational("1.5"))
    Rational("-15", "10").max(Rational("10", "9")) should be (Rational("10","9"))
  }
}
