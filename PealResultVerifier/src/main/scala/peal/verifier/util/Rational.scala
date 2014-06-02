package peal.verifier.util

object Rational {
  def apply(num: String) : Rational = Rational(num, "1")
  def apply(enum: String, denom: String) = new Rational(enum.toDouble, denom.toDouble)
}

case class Rational(numerator: BigDecimal, denominator: BigDecimal) {
  def value : BigDecimal = numerator / denominator
}
