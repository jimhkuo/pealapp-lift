package peal.domain

class Score (val score: Either[BigDecimal, VariableFormula], val range: Option[Range])