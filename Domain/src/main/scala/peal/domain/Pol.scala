package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operators
import peal.synthesis.PolicySet

case class Pol(rules: java.util.List[Rule], operator: Operators, defaultScore: Double, name: String = "") extends PolicySet {
  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + "%.2f".format(defaultScore)

  def getName = name
}