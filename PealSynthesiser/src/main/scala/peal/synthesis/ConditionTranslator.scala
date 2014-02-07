package peal.synthesis

import peal.domain.{BasicPolicySet, Pol, PolicySet}

object ConditionTranslator {

  def translate(cond: Condition, conds: Map[String, Condition]): String = cond match {
    case c: PredicateCondition => c.predicateName
    case c: NotCondition => "(not " + translate(conds(c.condName), conds) + ")"
    case c: OrCondition => "(or " + translate(conds(c.lhs), conds) + " " + translate(conds(c.rhs), conds) + ")"
    case c: AndCondition => "(and " + translate(conds(c.lhs), conds) + " " + translate(conds(c.rhs), conds) + ")"
    case c: LessThanThCondition => "(<= " + translatePolicySet(c.lhs) + " " + c.rhs.fold(lhs => lhs.toString(), rhs => translatePolicySet(rhs)) + ")"
    case _ => "not done"
  }


  private def translatePolicySet(pSet: PolicySet): String = pSet match {
    case s: BasicPolicySet => s.underlyingPolicyName + "_score"
  }

}
