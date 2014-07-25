package peal.expression

import peal.synthesis._

object ConditionExpressionBuilder {
  def build(pealObject: Condition)(implicit conditions: Map[String, Condition] = Map()): String = pealObject match {
    case GreaterThanThCondition(lhs, rhs) => lhs.getPolicySetName + " > " + rhs.fold(b => b, pSet => pSet.getPolicySetName)
    case LessThanThCondition(lhs, rhs) => lhs.getPolicySetName + " <= " + rhs.fold(b => b, pSet => pSet.getPolicySetName)
    case AndCondition(lhs, rhs) => "(" + build(conditions(lhs)) + ") && (" + build(conditions(rhs)) + ")"
    case OrCondition(lhs, rhs) => "(" + build(conditions(lhs)) + ") || (" + build(conditions(rhs)) + ")"
    case NotCondition(cond) => "!(" + build(conditions(cond)) + ")"
    case PredicateCondition(pred) => pred
  }
}
