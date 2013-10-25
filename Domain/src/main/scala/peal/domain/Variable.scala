package peal.domain

//TODO need to expand this to support 0.5*x + y
object Variable {
  def apply(n: String) = new Variable(1, n)
  def apply(multiplier: BigDecimal, n: String) = new Variable(multiplier, n)
}

class Variable(val multiplier: BigDecimal, val name: String) {
  def toZ3Expression = if (multiplier != 1) "(* " + multiplier + " " + name + ")" else name
}
