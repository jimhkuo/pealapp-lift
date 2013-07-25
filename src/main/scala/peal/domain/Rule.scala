package peal.domain


class Rule(val q: Predicate, val score: Double) {
  override def toString = "(" + q.name + " " + score + ")"
}