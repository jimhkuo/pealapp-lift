package peal

import peal.synthesis.DefaultSet
import peal.domain.Pol

class DefaultPolLessThanTh(pol: Pol, th: Double) extends DefaultSet {
  def synthesis: String = pol.defaultScore match {
    case s if s > th => ""
    case _ =>
      pol.rules.map("!" + _.q.name).mkString(" ")
  }
}
