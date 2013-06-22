package peal

import peal.synthesis.NonDefaultSet
import peal.domain.Pol

class NonDefaultPolLessThanThOpPlus(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = new NonDefaultThLessThanPolOpPlus(pol, th).notPhi
}
