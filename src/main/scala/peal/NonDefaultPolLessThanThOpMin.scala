package peal

import peal.synthesis.NonDefaultSet
import peal.domain.Pol

class NonDefaultPolLessThanThOpMin(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = pol.rules.size match {
    case s if s > 1 => pol.rules.filter(th > _.score).map(_.q.name).mkString("or ", " ", "")
    case _ => pol.rules.filter(th > _.score).map(_.q.name).mkString(" ")
  }
}
