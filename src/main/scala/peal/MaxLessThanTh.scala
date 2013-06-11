package peal

import peal.domain.Pol
import peal.synthesis.TopSet

//TODO this is wrong , should take in more than two arguments
class MaxLessThanTh(lhs: Pol, rhs: Pol, th: Double) extends TopSet {

  def synthesis = "(and " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs, th).synthesis + ")"
}
