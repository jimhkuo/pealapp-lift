package peal.domain

class Rule(val q: Predicate, val attribute : Either[BigDecimal, VariableFormula]) {
  def this(q: Predicate, doubleScore: Double) = this(q, Left(BigDecimal.valueOf(doubleScore)))
  def score = attribute.left.get //purposely left this like this so when non constant scores are used in comparison, it will blow up
  def scoreString = attribute.fold(score => score.toString(), variable => variable.toZ3Expression)
  override def toString = "(" + q.name + " " + scoreString + ")"
}