package peal.domain

object Variable {
  def apply(n: String) = new Variable(-999, n)
}

class Variable(val multiplier: BigDecimal, val name: String)
