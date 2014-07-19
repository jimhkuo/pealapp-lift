package peal.domain

object Rational {
  private def gcd(a: BigDecimal, b: BigDecimal): BigDecimal = if (b == BigDecimal(0)) a else gcd(b, a % b)

  def apply(num: String): Rational = Rational(num, "1")

  def apply(x: String, y: String): Rational = {
    def isWholeNumber(bigDecimal: BigDecimal): Boolean = bigDecimal == BigDecimal(bigDecimal.longValue())

    val numer = BigDecimal(x).abs
    val denom = BigDecimal(y).abs
    val commonDivisior = gcd(numer, denom)
    val tempA: BigDecimal = if (x.toDouble * y.toDouble < 0) -(numer / commonDivisior).abs else (numer / commonDivisior).abs
    val a: BigDecimal = if (isWholeNumber(tempA)) tempA.setScale(0, BigDecimal.RoundingMode.DOWN) else tempA
    val b: BigDecimal = (denom / commonDivisior).abs
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
