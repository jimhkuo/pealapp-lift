package peal

import peal.domain.Pol
import peal.synthesis.TopSet

class MinLessThanTh(lhs: Pol, rhs: Pol, th: Double) extends TopSet {

  def synthesis = "(or " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs, th).synthesis + ")"
}
