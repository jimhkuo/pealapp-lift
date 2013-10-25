package peal.domain

object Score {
  def apply(n : BigDecimal) = new Score(n)
  def apply(s : String) = new Score(Variable(s))
}

class Score (value : Either[BigDecimal, Variable]) {
  def this(v : Variable) = this(Right(v))
  def this(n : BigDecimal) = this(Left(n))
  def toZ3Expression = value.fold(num => num.toString(), v => v.toZ3Expression)
}
