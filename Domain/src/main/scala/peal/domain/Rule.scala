package peal.domain

//not sure the point of Predicate
class Rule(val q: Predicate, val score: Score) {
  def this(q: Predicate, doubleScore: Double) = this(q, new Score(Left(BigDecimal.valueOf(doubleScore)), None))

  def this(q: Predicate, scoreString: String) = this(q, new Score(Left(BigDecimal.valueOf(scoreString.toDouble)), None))

  def numberScore = score.underlyingScore.left.get

  //purposely left this like this so when non constant scores are used in comparison, it will blow up
  def scoreZ3String = score.underlyingScore.fold(score => score.toString(), variable => variable.toZ3Expression)

  private def range = score.scoreRange match {
    case None => ""
    case Some(r) => " [" + r.minValue + "," + r.maxValue + "]"
  }

  private def scoreString = score.underlyingScore.fold(score => score.toString(), variable => variable.toNaturalExpression)

  override def toString = "(" + q.name + " " + scoreString + range + ")"
}