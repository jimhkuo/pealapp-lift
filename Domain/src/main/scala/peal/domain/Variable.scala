package peal.domain

object Variable {
  def apply(n: String) = new Variable(-999, n)
  def apply(multiplier: BigDecimal, n: String) = new Variable(multiplier, n)
}

class Variable(val multiplier: BigDecimal, val name: String)
