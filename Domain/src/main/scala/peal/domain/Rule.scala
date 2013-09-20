package peal.domain

class Rule(val q: Predicate, val attribute : Either[BigDecimal, String]) {
  def this(q: Predicate, doubleScore: Double) = this(q, Left(BigDecimal.valueOf(doubleScore)))
  override def toString = "(" + q.name + " " + attribute.fold(score => score.toString(), variable => variable) + ")"
  def score = attribute.fold(score => score, variable => BigDecimal.valueOf(-999))
  def variable = attribute.right.get
}