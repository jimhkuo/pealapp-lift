package peal

import peal.domain.Pol

class PolLessThanTh(pol: Pol, th: Double) {

  def synthesis = "(or " + new DefaultPolLessThanTh(pol, th).synthesis + " " + new NonDefaultPolLessThanThOpMin(pol, th).synthesis + ")"
}
