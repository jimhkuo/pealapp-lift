package peal.synthesis

import peal.domain._
import peal.domain.Pol
import peal.domain.z3.wrapper.{Or, And, PealAst}

case class GreaterThanThCondition(phi: PolicySet, th: BigDecimal) extends Condition {
  def synthesis(consts: Map[String, PealAst]): PealAst = phi match {
    case s: MinPolicySet => And(new GreaterThanThCondition(s.lhs, th).synthesis(consts), new GreaterThanThCondition(s.rhs, th).synthesis(consts))
    case s: MaxPolicySet => Or(new GreaterThanThCondition(s.lhs, th).synthesis(consts), new GreaterThanThCondition(s.rhs, th).synthesis(consts))
    case s: BasicPolicySet => new GreaterThanThCondition(s.pol, th).synthesis(consts)
    case s: Pol => new ThLessThanPolCondition(s, th).synthesis(consts)
  }

  def getPol = phi

  def getTh = th
}
