package peal.synthesis

object ConditionTranslator {

  def translate(cond:Condition, conds : Map[String, Condition]) : String = cond match {
    case c: PredicateCondition => c.predicateName
    case c: NotCondition => "(not " + translate(conds(c.condName), conds) + ")"
    case c: OrCondition => "(or " + translate(conds(c.lhs), conds) + " " + translate(conds(c.rhs), conds) + ")"
    case _=> "not done"
  }

}
