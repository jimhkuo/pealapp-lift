package peal.synthesis

object ConditionTranslator {

  def translate(cond:Condition) : String = cond match {
    case c: PredicateCondition => c.predicateName
    case _=> "not done"
  }

}
