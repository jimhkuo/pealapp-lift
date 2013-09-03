package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operators

case class Pol(rules: java.util.List[Rule], operator: Operators, defaultScore: BigDecimal, name: String = "") extends PolicySet {
  def this(rules: java.util.List[Rule], operator: Operators, doubleScore: Double) = this(rules, operator, BigDecimal.valueOf(doubleScore))
  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + defaultScore

  //needed by Java classes
  def getName = name
}