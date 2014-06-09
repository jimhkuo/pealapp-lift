package peal.domain

class Score (val underlyingScore: Either[BigDecimal, VariableFormula], val scoreRange: Option[ScoreRange]) {
  override def toString = underlyingScore.fold(d => d.toString(), f => f.toNaturalExpression) + " " + scoreRange.getOrElse("").toString
}