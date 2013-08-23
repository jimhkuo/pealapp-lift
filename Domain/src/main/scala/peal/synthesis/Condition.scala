package peal.synthesis

import peal.domain.PolicySet
import peal.domain.z3.wrapper.PealAst


trait Condition {
  def getPol : PolicySet
  def getTh : BigDecimal
  def synthesis(consts: Map[String, PealAst]): PealAst
}
