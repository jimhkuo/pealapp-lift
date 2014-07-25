package peal.expression

import peal.synthesis.{GreaterThanThCondition, Condition}

object ConditionExpressionBuilder {
  def build(pealObject: Condition) = pealObject match {
    case GreaterThanThCondition(lhs, rhs) => lhs.getPolicySetName + " > " + rhs.fold(b => b, pSet => pSet.getPolicySetName)
  }
}
