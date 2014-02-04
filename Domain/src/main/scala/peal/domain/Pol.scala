package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operator

case class Pol(rules: java.util.List[Rule], operator: Operator, val score: Score, policyName: String = "") extends PolicySet {
  def this(rules: java.util.List[Rule], operator: Operator, doubleScore: Double) = this(rules, operator, new Score(Left(BigDecimal.valueOf(doubleScore)), None))
  def this(rules: java.util.List[Rule], operator: Operator, scoreString: String) = this(rules, operator, new Score(Left(BigDecimal.valueOf(scoreString.toDouble)), None))

  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + score.underlyingScore.fold(score => score.toString(), variable => variable.toZ3Expression)

  //multiplier will be used if default score is non constant
  def defaultNumericalScore = score.underlyingScore.left.get

  def scoreString = score.underlyingScore.fold(lhs => lhs.toString(), rhs => rhs.toZ3Expression)

  //needed by Java classes
  def getPolicyName = policyName

  def getPolicySetName = policyName
}