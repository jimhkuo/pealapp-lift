package peal.domain

class Rule(val q: Predicate, val score: BigDecimal) {
  override def toString = "(" + q.name + " " + score + ")"
}