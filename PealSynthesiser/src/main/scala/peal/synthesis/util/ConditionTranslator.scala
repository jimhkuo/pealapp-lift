package peal.synthesis.util

import peal.domain._
import peal.synthesis._

object ConditionTranslator {

  def translate(condName: String, conds: Map[String, Condition]): String = conds(condName) match {
    case c: PredicateCondition => c.predicateName
    case c: NotCondition => "(not " + translate(c.condName, conds) + ")"
    case c: OrCondition => "(or " + translate(c.lhs, conds) + " " + translate(c.rhs, conds) + ")"
    case c: AndCondition => "(and " + translate(c.lhs, conds) + " " + translate(c.rhs, conds) + ")"
    case c: LessThanThCondition => "(<= " + translatePolicySet(c.lhs) + " " + c.rhs.fold(lhs => lhs.toString(), rhs => translatePolicySet(rhs)) + ")"
    case c: GreaterThanThCondition => "(< " + c.rhs.fold(lhs => lhs.toString(), rhs => translatePolicySet(rhs)) + " " + translatePolicySet(c.lhs) + ")"
    case _ => "Not supported condition " + condName
  }

  private def translatePolicySet(pSet: PolicySet): String = pSet match {
    case s: BasicPolicySet => s.underlyingPolicyName + "_score"
    case s: MaxPolicySet => "(ite (< " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ") " + translatePolicySet(s.rhs) + " " + translatePolicySet(s.lhs) + ")"
    case s: MinPolicySet => "(ite (< " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ") " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ")"
    case s: PlusPolicySet => "(+ " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ")"
    case s: MulPolicySet => "(* " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ")"
    case _ => "Unsupported Policy Set " + pSet
  }
}
