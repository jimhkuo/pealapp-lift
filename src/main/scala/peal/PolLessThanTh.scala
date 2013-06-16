package peal

import peal.domain.Pol
import peal.synthesis.TopSet
import scala.collection.JavaConversions._


class PolLessThanTh(pol: Pol, th: Double) extends TopSet {

  def synthesis = "(or " + new DefaultPolLessThanTh(pol, th).synthesis + " " + new NonDefaultPolLessThanThOpMin(pol, th).synthesis + ")"

  def header: String = pol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")

}
