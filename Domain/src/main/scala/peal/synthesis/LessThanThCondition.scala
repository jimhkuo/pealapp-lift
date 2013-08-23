package peal.synthesis

import peal.domain._
import _root_.z3.scala.Z3Context

import peal.domain.Pol
import peal.domain.z3.wrapper.{And, Or, PealAst}

case class LessThanThCondition(phi: PolicySet, th: BigDecimal) extends Condition {
  def synthesis(z3: Z3Context, consts: Map[String, PealAst]): PealAst = phi match {
    case s: MinPolicySet => Or(new LessThanThCondition(s.lhs, th).synthesis(z3, consts), new LessThanThCondition(s.rhs, th).synthesis(z3, consts))
    case s: MaxPolicySet => And(new LessThanThCondition(s.lhs, th).synthesis(z3, consts), new LessThanThCondition(s.rhs, th).synthesis(z3, consts))
    case s: BasicPolicySet => new LessThanThCondition(s.pol, th).synthesis(z3, consts)
    case s: Pol => new PolLessThanThCondition(s, th).synthesis(z3, consts)
  }

  def getPol = phi
  def getTh = th
}

