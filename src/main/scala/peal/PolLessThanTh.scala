package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class PolLessThanTh(pol: Pol, th: Double) extends pSet {

  def synthesis(z3 : Z3Context, consts: Map[String, Z3AST]) = pol.defaultScore match {
    case s if s <= th => z3.mkOr(new DefaultLessThanTh(pol, th).synthesis(z3,consts), new NonDefaultPolLessThanTh(pol, th).synthesis(z3,consts))
    case _ => z3.mkAnd(new ThLessThanDefault(pol, th).synthesis(z3,consts),new NonDefaultPolLessThanTh(pol, th).synthesis(z3,consts))
  }

  def z3SMTHeader: String = pol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")

  class DefaultLessThanTh(pol: Pol, th: Double) {
    def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): Z3AST = pol.rules.size match {
      case 1 => z3.mkNot(consts(pol.rules(0).q.name)) //pol.rules.map("(not " + _.q.name + ")").mkString(" ")
      case s if s > 1 => z3.mkAnd(pol.rules.map(p => z3.mkNot(consts(p.q.name))):_*)//pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
    }
  }

  class ThLessThanDefault(pol: Pol, th: Double) {
    def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): Z3AST = pol.rules.size match {
      case 1 => consts(pol.rules(0).q.name) //pol.rules.map(_.q.name).mkString(" ")
      case s if s > 1 => z3.mkOr(pol.rules.map(p => consts(p.q.name)):_*) //pol.rules.map(_.q.name).mkString("(or ", " ", ")")
    }
  }
}
