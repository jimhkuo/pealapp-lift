package peal.synthesis

import peal.domain.PolicySet
import peal.domain.z3.PealAst

trait Condition {
  def getPol : Option[PolicySet]
  def getTh : BigDecimal //if it's not available, it should blow up
  def synthesis(consts: Map[String, PealAst]): PealAst
}
