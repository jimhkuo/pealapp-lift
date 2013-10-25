package peal.synthesis

import scala.collection.JavaConversions._
import peal.domain.z3._
import peal.domain.Pol


class PolLessThanThSynthesiser(pol: Pol, th: BigDecimal) extends Condition {

  def synthesis(consts: Map[String, PealAst]) = pol.defaultNumericalScore match {
    case s if s <= th => Or(new DefaultLessThanTh(pol, th).synthesis(consts), new NonDefaultPolLessThanTh(pol, th).synthesis(consts))
    case _ => And(new ThLessThanDefault(pol, th).synthesis(consts), new NonDefaultPolLessThanTh(pol, th).synthesis(consts))
  }

  class DefaultLessThanTh(pol: Pol, th: BigDecimal) {
    def synthesis(consts: Map[String, PealAst]): PealAst = pol.rules.size match {
      case 0 => True()
      case 1 => Not(consts(pol.rules(0).q.name))
      case s if s > 1 => And(pol.rules.map(p => Not(consts(p.q.name))): _*)
    }
  }

  class ThLessThanDefault(pol: Pol, th: BigDecimal) {
    def synthesis(consts: Map[String, PealAst]): PealAst = pol.rules.size match {
      case 0 => False()
      case 1 => consts(pol.rules(0).q.name)
      case s if s > 1 => Or(pol.rules.map(p => consts(p.q.name)): _*)
    }
  }

  def getPol = None

  def getTh = th
}
