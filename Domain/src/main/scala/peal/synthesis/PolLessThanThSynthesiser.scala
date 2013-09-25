package peal.synthesis

import peal.domain.Pol
import scala.collection.JavaConversions._
import peal.domain.z3.{PealAst, Or, Not, And}


class PolLessThanThSynthesiser(pol: Pol, th: BigDecimal) extends Condition {

  def synthesis(consts: Map[String, PealAst]) = pol.defaultScore match {
    case s if s <= th => Or(new DefaultLessThanTh(pol, th).synthesis(consts), new NonDefaultPolLessThanTh(pol, th).synthesis(consts))
    case _ => And(new ThLessThanDefault(pol, th).synthesis(consts),new NonDefaultPolLessThanTh(pol, th).synthesis(consts))
  }

  class DefaultLessThanTh(pol: Pol, th: BigDecimal) {
    def synthesis(consts: Map[String, PealAst]): PealAst = pol.rules.size match {
      case 1 => Not(consts(pol.rules(0).q.name)) //pol.rules.map("(not " + _.q.name + ")").mkString(" ")
      case s if s > 1 => And(pol.rules.map(p => Not(consts(p.q.name))):_*)//pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
    }
  }

  class ThLessThanDefault(pol: Pol, th: BigDecimal) {
    def synthesis(consts: Map[String, PealAst]): PealAst = pol.rules.size match {
      case 1 => consts(pol.rules(0).q.name) //pol.rules.map(_.q.name).mkString(" ")
      case s if s > 1 => Or(pol.rules.map(p => consts(p.q.name)):_*) //pol.rules.map(_.q.name).mkString("(or ", " ", ")")
    }
  }

  def getPol = pol
  def getTh = th
}
