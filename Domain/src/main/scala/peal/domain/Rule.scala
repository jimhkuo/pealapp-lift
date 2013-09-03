package peal.domain

class Rule(val q: Predicate, val score: BigDecimal) {
  def this(q: Predicate, doubleScore: Double) = this(q, BigDecimal.valueOf(doubleScore))
  override def toString = "(" + q.name + " " + score + ")"
}