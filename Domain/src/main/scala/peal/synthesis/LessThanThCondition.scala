package peal.synthesis

import peal.domain._

import peal.domain.Pol
import peal.domain.z3.{PealAst, Or, And}

//TODO consider using Either for th
case class LessThanThCondition(lhs: PolicySet, rhs: BigDecimal) extends Condition {

  if (lhs == null) throw new RuntimeException("Referred to undeclared policy set in LessThanThCondition")

  def synthesis(consts: Map[String, PealAst]): PealAst = lhs match {
    case l: MinPolicySet => Or(new LessThanThCondition(l.lhs, rhs).synthesis(consts), new LessThanThCondition(l.rhs, rhs).synthesis(consts))
    case l: MaxPolicySet => And(new LessThanThCondition(l.lhs, rhs).synthesis(consts), new LessThanThCondition(l.rhs, rhs).synthesis(consts))
    case l: BasicPolicySet => new LessThanThCondition(l.pol, rhs).synthesis(consts)
    case l: Pol => new PolLessThanThSynthesiser(l, rhs).synthesis(consts)
  }

  def getPol = Some(lhs)
  def getTh = rhs
}

