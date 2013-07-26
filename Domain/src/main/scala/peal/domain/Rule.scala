package peal.domain

class Rule(val q: Predicate, val score: Double) {
  override def toString = "(" + q.name + " " + "%.2f".format(score) + ")"
}