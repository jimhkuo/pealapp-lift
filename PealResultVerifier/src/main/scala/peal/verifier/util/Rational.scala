package peal.verifier.util

object Rational {
  def apply(num: String) = new Rational(num, "1")
//  def apply(enum: String, denom: String) = new Rational(enum, denom)
}

case class Rational(numerator: String, denominator: String) {
  def value = BigDecimal.valueOf(numerator.toDouble / denominator.toDouble)
}
