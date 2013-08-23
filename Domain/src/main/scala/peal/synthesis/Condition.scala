package peal.synthesis

import z3.scala.Z3Context
import peal.domain.PolicySet
import peal.domain.z3.wrapper.PealAst


trait Condition {
  def getPol : PolicySet
  def getTh : BigDecimal
  def synthesis(z3: Z3Context, consts: Map[String, PealAst]): PealAst
}
