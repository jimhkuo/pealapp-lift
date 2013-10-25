package peal.domain

object Score {
  def apply(n : BigDecimal) = new Score(n)
  def apply(s : String) = new Score(Multiplier(s))
  def apply(v : Multiplier) = new Score(v)
}

class Score (value : Either[BigDecimal, Multiplier]) {
  def this(v : Multiplier) = this(Right(v))
  def this(n : BigDecimal) = this(Left(n))
  def toZ3Expression = value.fold(num => num.toString(), v => v.toZ3Expression)
}
