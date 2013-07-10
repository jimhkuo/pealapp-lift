package peal

import _root_.z3.scala.{Z3AST, Z3Context}
import peal.synthesis.{PolicySet, Condition}
import peal.domain.{MaxPolicySet, Pol, PolPolicySet, MinPolicySet}

class GreaterThanThCondition(phi: PolicySet, th: Double) extends Condition {
   def synthesis(z3: Z3Context, consts: Map[String, Z3AST]): Z3AST = phi match {
     case s: MinPolicySet => z3.mkAnd(new GreaterThanThCondition(s.lhs, th).synthesis(z3, consts), new GreaterThanThCondition(s.rhs, th).synthesis(z3, consts))
//     case s: MaxPolicySet => z3.mkOr(new GreaterThanThCondition(s.lhs, th).synthesis(z3, consts), new GreaterThanThCondition(s.rhs, th).synthesis(z3, consts))
     case s: PolPolicySet => new GreaterThanThCondition(s.pol, th).synthesis(z3, consts)
     case s: Pol => new ThLessThanPol(s, th).synthesis(z3, consts)
   }
 }