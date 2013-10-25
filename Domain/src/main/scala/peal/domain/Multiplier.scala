package peal.domain

//TODO need to expand this to support 0.5*x + y
object Multiplier {
  def apply(n: String) = new Multiplier(1, n)
  def apply(multiplier: BigDecimal, n: String) = new Multiplier(multiplier, n)
}

class Multiplier(val multiplier: BigDecimal, val name: String) {
  def toZ3Expression = if (multiplier != 1) "(* " + multiplier + " " + name + ")" else name
}
