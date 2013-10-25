package peal.domain

import scala.collection.mutable.ListBuffer

object ScoreSum {
  def apply(m: Multiplier) = new ScoreSum().add(m)
}

class ScoreSum() {
  val operations = ListBuffer[Multiplier]()

  def add(m: Multiplier) = {
    operations.append(m)
    this
  }

  def names = operations.size match {
    case 0 => Set()
    case _ => operations.map(_.name).toSet.filterNot(_ == "")
  }

  def toZ3Expression = operations.size match {
    case 0 => ""
    case 1 => operations(0).toZ3Expression
    case _ => "(+ " + operations.map(_.toZ3Expression).mkString(" ") + ")"
  }
}
