package peal.domain

class NewRule(val q: Predicate, val attribute : Either[BigDecimal, String]) {
  def this(q: Predicate, doubleScore: Double) = this(q, Left(BigDecimal.valueOf(doubleScore)))
  override def toString = "(" + q.name + " " + attribute.left + ")"
}