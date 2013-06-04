package peal

import peal.synthesis.NonDefaultSet


class NonDefaultThLessThanPolOpMonotone(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = ""

  def enumOne(x: Set, sum: Double, index: Integer): Set = {
    Set()
  }
}
