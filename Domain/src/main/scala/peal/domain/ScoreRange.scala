package peal.domain

class ScoreRange(val minValue: BigDecimal, val maxValue: BigDecimal) {
  require(minValue <= maxValue)
  override def toString = "[" + minValue + "," + maxValue + "]"
}