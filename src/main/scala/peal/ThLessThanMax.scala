package peal

import peal.domain.Pol
import peal.synthesis.TopSet
import scala.collection.JavaConversions._


class ThLessThanMax(lhs: Pol, rhs: Pol, th: Double) extends TopSet {

  def synthesis = "(and " + new ThLessThanPol(lhs, th).synthesis + " " + new ThLessThanPol(rhs, th).synthesis + ")"

  def z3SMTHeader: String = lhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n") +
    rhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")
}