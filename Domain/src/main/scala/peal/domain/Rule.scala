package peal.domain

class Rule(val q: Predicate, val attr : Either[BigDecimal, String]) {
  def this(q: Predicate, doubleScore: Double) = this(q, Left(BigDecimal.valueOf(doubleScore)))
  override def toString = "(" + q.name + " " + attr.fold(score => score.toString(), variable => variable) + ")"
  def score = attr.left.get
  def variable = attr.right.get
}