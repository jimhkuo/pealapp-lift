package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class PolLessThanTh(pol: Pol, th: Double) extends pSet {

  def synthesis(z3 : Z3Context, consts: Map[String, Z3AST]) = pol.defaultScore match {
    case s if s <= th => "(or " + new DefaultLessThanTh(pol, th).synthesis(z3,consts) + " " + new NonDefaultPolLessThanTh(pol, th).synthesis(z3,consts) + ")"
    case _ => "(and " + new ThLessThanDefault(pol, th).synthesis(z3,consts) + " " + new NonDefaultPolLessThanTh(pol, th).synthesis(z3,consts) + ")"
  }

  def z3SMTHeader: String = pol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")

  class DefaultLessThanTh(pol: Pol, th: Double) {
    def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
      case _ => pol.rules.map("(not " + _.q.name + ")").mkString(" ")
    }
  }

  class ThLessThanDefault(pol: Pol, th: Double) {
    def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): String = pol.defaultScore match {
      case _ if pol.rules.size > 1 => pol.rules.map(_.q.name).mkString("(or ", " ", ")")
      case _ => pol.rules.map(_.q.name).mkString(" ")
    }
  }
}
