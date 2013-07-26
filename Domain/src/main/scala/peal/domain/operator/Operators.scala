package peal.domain.operator

trait Operators

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
