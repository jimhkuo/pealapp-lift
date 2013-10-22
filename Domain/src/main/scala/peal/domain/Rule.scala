package peal.domain

class Rule(val q: Predicate, val attribute : Either[BigDecimal, Variable]) {
  def this(q: Predicate, doubleScore: Double) = this(q, Left(BigDecimal.valueOf(doubleScore)))
  def score = attribute.fold(score => score, variable => BigDecimal.valueOf(-999))
  def scoreString = attribute.fold(score => score.toString(), variable => "(* " + variable.multiplier + " " + variable.name + ")")
  override def toString = "(" + q.name + " " + attribute.fold(score => score.toString(), variable => variable.multiplier + "*" + variable.name) + ")"
}