package peal.synthesis

import peal.domain._

import peal.domain.Pol
import peal.domain.z3.{PealAst, Or, And}

case class LessThanThCondition(phi: PolicySet, th: BigDecimal) extends Condition {
  def synthesis(consts: Map[String, PealAst]): PealAst = phi match {
    case s: MinPolicySet => Or(new LessThanThCondition(s.lhs, th).synthesis(consts), new LessThanThCondition(s.rhs, th).synthesis(consts))
    case s: MaxPolicySet => And(new LessThanThCondition(s.lhs, th).synthesis(consts), new LessThanThCondition(s.rhs, th).synthesis(consts))
    case s: BasicPolicySet => new LessThanThCondition(s.pol, th).synthesis(consts)
    case s: Pol => new PolLessThanThSynthesiser(s, th).synthesis(consts)
  }

  def getPol = phi
  def getTh = th
}

