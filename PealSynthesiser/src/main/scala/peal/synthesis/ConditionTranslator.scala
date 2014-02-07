package peal.synthesis

import peal.domain._

object ConditionTranslator {

  def translate(cond: Condition, conds: Map[String, Condition]): String = cond match {
    case c: PredicateCondition => c.predicateName
    case c: NotCondition => "(not " + translate(conds(c.condName), conds) + ")"
    case c: OrCondition => "(or " + translate(conds(c.lhs), conds) + " " + translate(conds(c.rhs), conds) + ")"
    case c: AndCondition => "(and " + translate(conds(c.lhs), conds) + " " + translate(conds(c.rhs), conds) + ")"
    case c: LessThanThCondition => "(<= " + translatePolicySet(c.lhs) + " " + c.rhs.fold(lhs => lhs.toString(), rhs => translatePolicySet(rhs)) + ")"
    case c: GreaterThanThCondition => "(< " + c.rhs.fold(lhs => lhs.toString(), rhs => translatePolicySet(rhs)) + " " + translatePolicySet(c.lhs) + ")"
    case _ => "Not supported condition " + cond
  }

  private def translatePolicySet(pSet: PolicySet): String = pSet match {
    case s: BasicPolicySet => s.underlyingPolicyName + "_score"
    case s: MaxPolicySet => "(ite (< " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ") " + translatePolicySet(s.rhs) + " " + translatePolicySet(s.lhs) + ")"
    case s: MinPolicySet => "(ite (< " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ") " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ")"
    case s: PlusPolicySet => "(+ " + translatePolicySet(s.lhs) + " " + translatePolicySet(s.rhs) + ")"
    case _ => "Unsupported Policy Set " + pSet
  }
}
