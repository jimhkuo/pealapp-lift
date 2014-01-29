package peal.domain

class Score (val underlyingScore: Either[BigDecimal, VariableFormula], val range: Option[Range])