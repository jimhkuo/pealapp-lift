package peal.domain

object Multiplier {
  def apply(name: String) = new Multiplier(1, name)

  def apply(num: BigDecimal) = new Multiplier(num)

  def apply(multiplier: BigDecimal, name: String) = new Multiplier(multiplier, name)
}

class Multiplier(val multiplier: BigDecimal, val name: String = "") {
  def toZ3Expression = name match {
    case "" => multiplier.toString()
    case _ => if (multiplier != 1) "(* " + multiplier + " " + name + ")" else name
  }
  def toNaturalExpression = name match {
    case "" => multiplier.toString()
    case _ => if (multiplier != 1) multiplier + "*" + name else name
  }
}
