package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operators

case class Pol(rules: java.util.List[Rule], operator: Operators, val score: Either[BigDecimal, VariableFormula], policyName: String = "") extends PolicySet {
  def this(rules: java.util.List[Rule], operator: Operators, doubleScore: Double) = this(rules, operator, Left(BigDecimal.valueOf(doubleScore)))
  def this(rules: java.util.List[Rule], operator: Operators, scoreString: String) = this(rules, operator, Left(BigDecimal.valueOf(scoreString.toDouble)))

  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + score.fold(score => score.toString(), variable => variable.toZ3Expression)

  //multiplier will be used if default score is non constant
  def defaultNumericalScore = score.left.get

  def scoreString = score.fold(lhs => lhs.toString(), rhs => rhs.toZ3Expression)

  //needed by Java classes
  def getPolicyName = policyName

  def getPolicySetName = policyName
}