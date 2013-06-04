package peal

import peal.synthesis.NonDefaultSet
import peal.domain.Pol

class NonDefaultPolLessThanThOpMonotone(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = new NonDefaultThLessThanPolOpMonotone(pol, th).not
}
