package peal.domain.operator

trait Operators

object OperatorResolver extends Operators {
  def apply(operator: String) = operator match {
    case "+" => Plus
    case "*" => Mul
    case "min" => Min
    case "max" => Max
  }
}

object Plus extends Operators {
  override def toString = "+"
}

object Mul extends Operators {
  override def toString = "*"
}

object Min extends Operators {
  override def toString = "min"
}

object Max extends Operators {
  override def toString = "max"
}
