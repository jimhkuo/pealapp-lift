package peal.synthesis

import peal.domain._
import peal.domain.Pol
import peal.domain.z3.{PealAst, Or, And}

//this is > th
case class GreaterThanThCondition(lhs: PolicySet, rhs: Either[BigDecimal,PolicySet]) extends Condition {

  if (lhs == null) throw new RuntimeException("Referred to undeclared policy set in GreaterThanThCondition")

  //TODO if implementing equal condition, the first two cases change to comparing the maximum or minimum to th
  //may need to involve ite
  def synthesis(consts: Map[String, PealAst]): PealAst = lhs match {
    case l: MinPolicySet => And(new GreaterThanThCondition(l.lhs, rhs).synthesis(consts), new GreaterThanThCondition(l.rhs, rhs).synthesis(consts))
    case l: MaxPolicySet => Or(new GreaterThanThCondition(l.lhs, rhs).synthesis(consts), new GreaterThanThCondition(l.rhs, rhs).synthesis(consts))
    case l: BasicPolicySet => new GreaterThanThCondition(l.pol, rhs).synthesis(consts)
    case l: Pol => new ThLessThanPolSynthesiser(l, rhs.left.get).synthesis(consts)
  }

  def getPol = Some(lhs)

  def getTh = rhs.left.get
}
