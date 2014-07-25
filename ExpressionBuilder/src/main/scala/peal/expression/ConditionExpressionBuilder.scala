package peal.expression

import peal.synthesis._

object ConditionExpressionBuilder {
  def build(cond: String)(implicit conditions: Map[String, Condition] = Map()): String = conditions(cond) match {
    case GreaterThanThCondition(lhs, rhs) => lhs.getPolicySetName + " > " + rhs.fold(b => b, pSet => pSet.getPolicySetName)
    case LessThanThCondition(lhs, rhs) => lhs.getPolicySetName + " <= " + rhs.fold(b => b, pSet => pSet.getPolicySetName)
    case AndCondition(lhs, rhs) => "(" + build(lhs) + ") && (" + build(rhs) + ")"
    case OrCondition(lhs, rhs) => "(" + build(lhs) + ") || (" + build(rhs) + ")"
    case NotCondition(embeddedCondition) => "!(" + build(embeddedCondition) + ")"
    case PredicateCondition(pred) => pred
  }
}
