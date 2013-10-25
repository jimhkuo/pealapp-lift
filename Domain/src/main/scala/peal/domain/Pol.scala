package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operators

case class Pol(rules: java.util.List[Rule], operator: Operators, val score: Either[BigDecimal, Multiplier], policyName: String = "") extends PolicySet {
  def this(rules: java.util.List[Rule], operator: Operators, doubleScore: Double) = this(rules, operator, Left(BigDecimal.valueOf(doubleScore)))

  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + score.fold(score => score.toString(), variable => variable.multiplier + "*" + variable.name)

  //multiplier will be used if default score is non constant
  def defaultNumericalScore = score.fold(score => score, variable => variable.multiplier)

  def scoreString = score.fold(lhs => lhs.toString(), rhs => if (rhs.multiplier != 1) "(* " + rhs.multiplier + " " + rhs.name + ")" else rhs.name)

  //needed by Java classes
  def getPolicyName = policyName

  def getPolicySetName = policyName
}