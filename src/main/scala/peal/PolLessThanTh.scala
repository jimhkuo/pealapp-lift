package peal

import peal.domain.Pol
import peal.synthesis.TopSet

class PolLessThanTh(pol: Pol, th: Double) extends TopSet {

  def synthesis = "(or " + new DefaultPolLessThanTh(pol, th).synthesis + " " + new NonDefaultPolLessThanThOpMin(pol, th).synthesis + ")"
}
