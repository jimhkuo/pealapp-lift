package peal

import _root_.z3.scala.{Z3AST, Z3Context}
import peal.synthesis.{PolicySet, Condition}
import peal.domain.{MaxPolicySet, Pol, PolPolicySet, MinPolicySet}

class LessThanThCondition(phi: PolicySet, th: Double) extends Condition {
  def synthesis(z3: Z3Context, consts: Map[String, Z3AST]): Z3AST = phi match {
    case s: MinPolicySet => z3.mkOr(new LessThanThCondition(s.lhs, th).synthesis(z3, consts), new LessThanThCondition(s.rhs, th).synthesis(z3, consts))
    case s: MaxPolicySet => z3.mkAnd(new LessThanThCondition(s.lhs, th).synthesis(z3, consts), new LessThanThCondition(s.rhs, th).synthesis(z3, consts))
    case s: PolPolicySet => new LessThanThCondition(s.pol, th).synthesis(z3, consts)
    case s: Pol => new PolLessThanTh(s, th).synthesis(z3, consts)
  }
}