package peal

import peal.domain.Pol

class MaxLessThanTh(lhs: Pol, rhs: Pol, th: Double) {

  def synthesis = "(and " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs, th).synthesis + ")"
}
