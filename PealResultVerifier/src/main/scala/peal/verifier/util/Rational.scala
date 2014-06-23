package peal.verifier.util

object Rational {
  private def gcd(a: BigDecimal, b: BigDecimal): BigDecimal = if (b == BigDecimal(0)) a else gcd(b, a % b)

  def apply(num: String): Rational = Rational(num, "1")

  def apply(x: String, y: String): Rational = {
    val numer = x.toDouble.abs
    val denom = y.toDouble.abs
    val commonDivisior = gcd(numer, denom)
    val a = if (x.toDouble * y.toDouble < 0) -(numer / commonDivisior).abs else (numer / commonDivisior).abs
    val b = (denom / commonDivisior).abs
    Rational(a, b)
  }
}

case class Rational(numerator: BigDecimal, denominator: BigDecimal) {
  private def less(that: Rational) = numerator * that.denominator < that.numerator * denominator

  def z3Expression = if (numerator >= 0) "(/ " + numerator.bigDecimal.toPlainString + " " + denominator.bigDecimal.toPlainString + ")" else "(- (/ " + numerator.abs.bigDecimal.toPlainString + " " + denominator.bigDecimal.toPlainString + "))"

  def value: BigDecimal = numerator / denominator

  def max(that: Rational) = if (this.less(that)) that else this

  def min(that: Rational) = if (this.less(that)) this else that

  def *(that: Rational) = Rational((numerator * that.numerator).toString(), (denominator * that.denominator).toString())

  def +(that: Rational) = Rational((numerator * that.denominator + that.numerator * denominator).toString(), (denominator * that.denominator).toString())
}
