package peal

import peal.domain.Pol
import peal.synthesis.TopSet

class MaxLessThanTh(lhs: Pol, rhs: Pol, th: Double) extends TopSet {

  def synthesis = "(and " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs, th).synthesis + ")"
}
