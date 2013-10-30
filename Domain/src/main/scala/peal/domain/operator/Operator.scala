package peal.domain.operator

trait Operator
trait OperatorWithUnit extends Operator {
  def unit : String
}

object OperatorResolver extends Operator {
  def apply(operator: String) = operator match {
    case "+" => Plus
    case "*" => Mul
    case "min" => Min
    case "max" => Max
  }
}

object Plus extends OperatorWithUnit {
  override def toString = "+"
  def unit = "0.0"
}

object Mul extends OperatorWithUnit {
  override def toString = "*"
  def unit = "1.0"
}

object Min extends Operator {
  override def toString = "min"
}

object Max extends Operator {
  override def toString = "max"
}
