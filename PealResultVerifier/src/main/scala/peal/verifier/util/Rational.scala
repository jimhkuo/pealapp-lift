package peal.verifier.util

class Rational(numerator: String, denominator: String) {

  def value = BigDecimal.valueOf(numerator.toDouble / denominator.toDouble)

}
