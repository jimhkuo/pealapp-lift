package peal.synthesis

import peal.domain.{PolicySet, Pol}
import scala.collection.JavaConversions._
import _root_.z3.scala.{Z3AST, Z3Context}


case class ThLessThanPolCondition(pol: Pol, th: Double) extends Condition {

  def synthesis(z3:Z3Context, consts: Map[String, Z3AST]) = pol.defaultScore match {
    case s if th < s => z3.mkOr(new ThLessThanDefault(pol, th).synthesis(z3,consts), new NonDefaultThLessThanPol(pol, th).synthesis(z3,consts))
    case _ => z3.mkAnd(new DefaultLessThanTh(pol, th).synthesis(z3,consts), new NonDefaultThLessThanPol(pol, th).synthesis(z3,consts))
  }

  class ThLessThanDefault(pol: Pol, th: Double)  {
    def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): Z3AST = pol.rules.size match {
      case 1 => z3.mkNot(consts(pol.rules(0).q.name)) //pol.rules.map("(not " + _.q.name + ")").mkString(" ")
      case s if s > 1 => z3.mkAnd(pol.rules.map(p => z3.mkNot(consts(p.q.name))):_*) //pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
    }
  }

  class DefaultLessThanTh(pol: Pol, th: Double)  {
    def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): Z3AST = pol.rules.size match {
      case 1 => consts(pol.rules(0).q.name)//pol.rules.map(_.q.name).mkString(" ")
      case s if s > 1 => z3.mkOr(pol.rules.map(p => consts(p.q.name)):_*) //pol.rules.map(_.q.name).mkString("(or ", " ", ")")
    }
  }

  def getPol: PolicySet = pol
}
