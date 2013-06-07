package peal

import peal.domain.Pol

class MinLessThanTh(lhs: Pol, rhs: Pol, th: Double) {

  def synthesis = "(or " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs, th).synthesis + ")"
}
