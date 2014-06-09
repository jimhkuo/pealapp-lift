package peal.domain

class ScoreRange(val minValue: BigDecimal, val maxValue: BigDecimal) {
  override def toString = "[" + minValue + "," + maxValue + "]"
}