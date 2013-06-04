package peal

import peal.synthesis.NonDefaultSet
import peal.domain.Pol

class NonDefaultPolLessThanThOpMin(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = pol.rules.filter(th > _.score).map(_.q.name).mkString("\n")
}
