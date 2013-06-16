package peal

import peal.domain.Pol
import peal.synthesis.TopSet
import scala.collection.JavaConversions._


class MinLessThanTh(lhs: Pol, rhs: Pol, th: Double) extends TopSet {

  def synthesis = "(or " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs, th).synthesis + ")"

  def header: String = lhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("\n") +
    rhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("\n")
}
