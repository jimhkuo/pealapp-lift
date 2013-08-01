package peal.synthesis

import peal.domain.Pol
import scala.collection.JavaConversions._
import _root_.z3.scala.{Z3AST, Z3Context}


class PolLessThanThCondition(pol: Pol, th: Double) extends Condition {

  def synthesis(z3 : Z3Context, consts: Map[String, Z3AST]) = pol.defaultScore match {
    case s if s <= th => z3.mkOr(new DefaultLessThanTh(pol, th).synthesis(z3,consts), new NonDefaultPolLessThanTh(pol, th).synthesis(z3,consts))
    case _ => z3.mkAnd(new ThLessThanDefault(pol, th).synthesis(z3,consts),new NonDefaultPolLessThanTh(pol, th).synthesis(z3,consts))
  }

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

  def getPol: PolicySet = pol
}
