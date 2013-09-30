package peal.synthesis

import peal.domain.PolicySet
import peal.domain.z3.PealAst

trait Condition {
  //TODO may need to support substitution of predicates
  //so robust analyses can work in both eager/lazy synthesis
  def getPol : PolicySet
  def getTh : BigDecimal
  def synthesis(consts: Map[String, PealAst]): PealAst
}
