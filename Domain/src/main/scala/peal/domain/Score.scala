package peal.domain

class Score (val underlyingScore: Either[BigDecimal, VariableFormula], val scoreRange: Option[ScoreRange])