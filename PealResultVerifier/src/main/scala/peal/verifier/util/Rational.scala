package peal.verifier.util

object Rational {
  private def gcd(a: BigDecimal, b: BigDecimal): BigDecimal = if (b == 0) a else gcd(b, a % b)

  def apply(num: String): Rational = Rational(num, "1")

  def apply(x: String, y: String) = {
    val numer = x.toDouble
    val denom = y.toDouble
    val commonDivisior = gcd(numer, denom)
    val a = if (numer * denom < 0) -(numer / commonDivisior).abs else (numer / commonDivisior).abs
    val b = (denom / commonDivisior).abs
    new Rational(a, b)
  }
}

case class Rational(numerator: BigDecimal, denominator: BigDecimal) {
  private def less(that: Rational) = numerator * that.denominator < that.numerator * denominator

  def value: BigDecimal = numerator / denominator

  def max(that: Rational) = if (this.less(that)) that else this
}
