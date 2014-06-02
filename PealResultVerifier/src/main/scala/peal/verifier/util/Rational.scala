package peal.verifier.util

object Rational {
  private def gcd(a: BigDecimal, b: BigDecimal): BigDecimal = {
//    println(a + " " + b)
    if (b == 0) a else gcd(b, a % b)
  }

  def apply(num: String): Rational = Rational(num, "1")

  def apply(enum: String, denom: String) = new Rational(enum.toDouble / gcd(enum.toDouble, denom.toDouble), denom.toDouble / gcd(enum.toDouble, denom.toDouble))
}

case class Rational(numerator: BigDecimal, denominator: BigDecimal) {
  def value: BigDecimal = numerator / denominator
}
