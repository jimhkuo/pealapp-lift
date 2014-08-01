package peal.domain

class ScoreRange(val minValue: BigDecimal, val maxValue: BigDecimal) {
  require(minValue <= 0, "min value of the score range [" + minValue + "," + maxValue + "] has to be <= 0")
  require(maxValue >= 0, "max value of the score range [" + minValue + "," + maxValue + "] has to be >= 0")
  override def toString = "[" + minValue + "," + maxValue + "]"
}