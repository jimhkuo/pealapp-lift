package peal.verifier.util

object Rational {
  def apply(num: String) : Rational = Rational(num, "1")
  def apply(enum: String, denom: String) = new Rational(enum.toDouble.toInt, denom.toDouble.toInt)
}

case class Rational(numerator: Int, denominator: Int) {
  def value : BigDecimal = numerator.toDouble / denominator
}
