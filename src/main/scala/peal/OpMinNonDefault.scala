package peal

import peal.synthesis.NonDefaultSet

class OpMinNonDefault(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = pol.rules.filter(th > _.score).map(_.q.name).mkString(" ")
}
