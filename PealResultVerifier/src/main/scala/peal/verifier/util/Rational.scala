package peal.verifier.util

object Rational {
  private def gcd(a: BigDecimal, b: BigDecimal): BigDecimal = {
    println("in gcd: a " + a + " b " + b)
    if (b == 0) a else gcd(b, a % b)
  }

  def apply(num: String): Rational = Rational(num, "1")

  def apply(x: String, y: String): Rational = {
    println("apply: " + x + " " + y)
    val numer = x.toDouble
    val denom = y.toDouble
    val commonDivisior = gcd(x.toDouble.abs, y.toDouble.abs)
    val a = if (numer * denom < 0) -(numer / commonDivisior).abs else (numer / commonDivisior).abs
    val b = (denom / commonDivisior).abs
    Rational(a, b)
  }
}

case class Rational(numerator: BigDecimal, denominator: BigDecimal) {
  private def less(that: Rational) = numerator * that.denominator < that.numerator * denominator

  def value: BigDecimal = numerator / denominator

  def max(that: Rational) = if (this.less(that)) that else this

  def min(that: Rational) = if (this.less(that)) this else that

  def *(that: Rational) = Rational((numerator * that.numerator).toString(), (denominator * that.denominator).toString())

  def +(that: Rational) = Rational((numerator * that.denominator + that.numerator * denominator).toString(), (denominator * that.denominator).toString())
}
