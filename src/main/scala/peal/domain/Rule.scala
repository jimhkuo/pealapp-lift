package peal.domain


class Rule(val q: Predicate, val score: Double) {
  override def toString = "Rule(" + q.name + ", " + score + ")"
}