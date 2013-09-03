package peal.domain

class Rule(val q: Predicate, val score: BigDecimal) {
  def this(q: Predicate, score: Double) = this(q, BigDecimal.valueOf(score))
  override def toString = "(" + q.name + " " + score + ")"
}