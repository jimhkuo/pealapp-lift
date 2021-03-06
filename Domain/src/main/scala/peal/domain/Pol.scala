package peal.domain

import scala.collection.JavaConversions._
import peal.domain.operator.Operator

case class Pol(rules: java.util.List[Rule], operator: Operator, score: Score, policyName: String = "") extends PolicySet {
  def this(rules: java.util.List[Rule], operator: Operator, scoreString: String) = this(rules, operator, new Score(Left(BigDecimal(scoreString)), None))

  private def range = score.scoreRange match {
    case None => ""
    case Some(r) => " [" + r.minValue + "," + r.maxValue + "]"
  }

  override def toString: String = operator + " (" + rules.mkString(" ") + ") default " + score.underlyingScore.fold(score => score.toString(), variable => variable.toZ3Expression)

  def toNaturalExpression: String = operator + " (" + rules.mkString(" ") + ") default " + score.underlyingScore.fold(score => score.toString(), variable => variable.toNaturalExpression) + range

  //multiplier will be used if default score is non constant
  def defaultNumericalScore = score.underlyingScore.left.get

  def scoreString = score.underlyingScore.fold(lhs => lhs.toString(), rhs => rhs.toZ3Expression)

  //needed by Java classes
  def getPolicyName = policyName

  def getPolicySetName = policyName
}