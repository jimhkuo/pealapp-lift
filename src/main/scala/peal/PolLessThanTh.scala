package peal

import peal.domain.Pol
import peal.synthesis.TopSet
import scala.collection.JavaConversions._


class PolLessThanTh(pol: Pol, th: Double) extends TopSet {

  def synthesis = pol.defaultScore match {
    case s if s <= th => "(or " + new DefaultLessThanTh(pol, th).synthesis + " " + new NonDefaultPolLessThanTh(pol, th).synthesis + ")"
    case _ => "(and " + new ThLessThanDefault(pol, th).synthesis + " " + new NonDefaultPolLessThanTh(pol, th).synthesis + ")"
  }

  def z3SMTHeader: String = pol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")

  class DefaultLessThanTh(pol: Pol, th: Double) {
    def synthesis: String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
      case _ => pol.rules.map("(not " + _.q.name + ")").mkString(" ")
    }
  }

  class ThLessThanDefault(pol: Pol, th: Double) {
    def synthesis: String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map(_.q.name).mkString("(or ", " ", ")")
      case _ => pol.rules.map(_.q.name).mkString(" ")
    }
  }
}
