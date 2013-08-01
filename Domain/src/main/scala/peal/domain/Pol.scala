package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operators
import peal.synthesis.PolicySet

case class Pol(val rules: java.util.List[Rule], val operator: Operators, val defaultScore: Double, name: String = "") extends PolicySet {
  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + "%.2f".format(defaultScore)
}