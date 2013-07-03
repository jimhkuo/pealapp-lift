package peal

import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class MinLessThanTh(lhs: Pol, rhs: Pol, th: Double) extends pSet {

  def synthesis = "(or " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs, th).synthesis + ")"

  def z3SMTHeader: String = lhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n") +
    rhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")
}
