package peal

import peal.domain.Pol
import peal.synthesis.{DefaultSet, pSet}
import scala.collection.JavaConversions._


class ThLessThanPol(pol: Pol, th: Double) extends pSet {

  def synthesis = pol.defaultScore match {
    case s if th < s =>
      "(or " + new ThLessThanDefault(pol, th).synthesis + " " + new NonDefaultThLessThanPol(pol, th).synthesis + ")"
    case _ =>
      "(and " + new DefaultLessThanTh(pol, th).synthesis + " " + new NonDefaultThLessThanPol(pol, th).synthesis + ")"
  }

  def z3SMTHeader: String = pol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")

  class ThLessThanDefault(pol: Pol, th: Double)  {
    def synthesis: String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
      case _ => pol.rules.map("(not " + _.q.name + ")").mkString(" ")
    }
  }

  class DefaultLessThanTh(pol: Pol, th: Double)  {
    def synthesis: String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map(_.q.name).mkString("(or ", " ", ")")
      case _ => pol.rules.map(_.q.name).mkString(" ")
    }
  }
}
