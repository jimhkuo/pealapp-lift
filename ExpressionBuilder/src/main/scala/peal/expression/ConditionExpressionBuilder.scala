package peal.expression

import peal.synthesis.{AndCondition, LessThanThCondition, GreaterThanThCondition, Condition}

object ConditionExpressionBuilder {
  def build(pealObject: Condition, conditions: Map[String, Condition] = Map()): String = pealObject match {
    case GreaterThanThCondition(lhs, rhs) => lhs.getPolicySetName + " > " + rhs.fold(b => b, pSet => pSet.getPolicySetName)
    case LessThanThCondition(lhs, rhs) => lhs.getPolicySetName + " <= " + rhs.fold(b => b, pSet => pSet.getPolicySetName)
    case AndCondition(lhs, rhs) => "(" + build(conditions(lhs)) + ") && (" + build(conditions(rhs)) + ")"
  }
}
