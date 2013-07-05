package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.{DefaultSet, pSet}
import scala.collection.JavaConversions._


class ThLessThanPol(pol: Pol, th: Double) extends pSet {

  def synthesis(z3:Z3Context) = pol.defaultScore match {
    case s if th < s =>
      "(or " + new ThLessThanDefault(pol, th).synthesis(z3) + " " + new NonDefaultThLessThanPol(pol, th).synthesis(z3) + ")"
    case _ =>
      "(and " + new DefaultLessThanTh(pol, th).synthesis(z3) + " " + new NonDefaultThLessThanPol(pol, th).synthesis(z3) + ")"
  }

  def z3SMTHeader: String = pol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")

  class ThLessThanDefault(pol: Pol, th: Double)  {
    def synthesis(z3:Z3Context): String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
      case _ => pol.rules.map("(not " + _.q.name + ")").mkString(" ")
    }
  }

  class DefaultLessThanTh(pol: Pol, th: Double)  {
    def synthesis(z3:Z3Context): String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map(_.q.name).mkString("(or ", " ", ")")
      case _ => pol.rules.map(_.q.name).mkString(" ")
    }
  }
}
