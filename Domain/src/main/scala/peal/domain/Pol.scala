package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operators

case class Pol(rules: java.util.List[Rule], operator: Operators, val score: Either[BigDecimal, Variable], name: String = "") extends PolicySet {
  def this(rules: java.util.List[Rule], operator: Operators, doubleScore: Double) = this(rules, operator, Left(BigDecimal.valueOf(doubleScore)))

  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + score.fold(score => score.toString(), variable => variable.multiplier + "*" + variable.name)

  def defaultScore = score.fold(score => score, variable => BigDecimal.valueOf(-999))

  //needed by Java classes
  def getName = name
}