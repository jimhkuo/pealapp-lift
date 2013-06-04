package peal

import peal.synthesis.NonDefaultSet

class NonDefaultPolLessThanThOpMonotone(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = new NonDefaultThLessThanPolOpMonotone(pol, th).not
}
