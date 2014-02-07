package peal.synthesis

object ConditionTranslator {

  def translate(cond:Condition, conds : Map[String, Condition]) : String = cond match {
    case c: PredicateCondition => c.predicateName
    case c: NotCondition => "(not " + translate(conds(c.condName), conds) + ")"
    case _=> "not done"
  }

}
