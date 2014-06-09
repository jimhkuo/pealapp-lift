package peal.domain

class Rule(val q: Predicate, val score : Score) {
  def this(q: Predicate, doubleScore: Double) = this(q, new Score(Left(BigDecimal.valueOf(doubleScore)), None))
  def this(q: Predicate, scoreString: String) = this(q, new Score(Left(BigDecimal.valueOf(scoreString.toDouble)), None))
  def numberScore = score.underlyingScore.left.get //purposely left this like this so when non constant scores are used in comparison, it will blow up
  def scoreString = score.underlyingScore.fold(score => score.toString(), variable => variable.toZ3Expression)
  override def toString = "(" + q.name + " " + scoreString + ")"
}