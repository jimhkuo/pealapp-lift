package peal.synthesis

import _root_.z3.scala.Z3Context
import peal.domain._
import peal.domain.Pol
import peal.domain.z3.wrapper.{Or, And, PealAst}

case class GreaterThanThCondition(phi: PolicySet, th: BigDecimal) extends Condition {
   def synthesis(z3: Z3Context, consts: Map[String, PealAst]): PealAst = phi match {
     case s: MinPolicySet => And(new GreaterThanThCondition(s.lhs, th).synthesis(z3, consts), new GreaterThanThCondition(s.rhs, th).synthesis(z3, consts))
     case s: MaxPolicySet => Or(new GreaterThanThCondition(s.lhs, th).synthesis(z3, consts), new GreaterThanThCondition(s.rhs, th).synthesis(z3, consts))
     case s: BasicPolicySet => new GreaterThanThCondition(s.pol, th).synthesis(z3, consts)
     case s: Pol => new ThLessThanPolCondition(s, th).synthesis(z3, consts)
   }

  def getPol = phi

  def getTh = th
}
