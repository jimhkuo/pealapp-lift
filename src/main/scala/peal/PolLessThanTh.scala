package peal

import peal.synthesis.DefaultSet

class PolLessThanTh(pol: Pol, th: Double) extends DefaultSet {
  def synthesis: String = pol.defaultScore match {
    case s if s > th => ""
    case _ =>
      pol.rules.map(_.q.name).foldLeft("")((rules, name) => "!" + name + " ")
  }
}
