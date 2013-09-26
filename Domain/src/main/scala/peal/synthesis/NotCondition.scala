package peal.synthesis

import peal.domain.z3.{Term, Not, PealAst}

class NotCondition(cond: String) extends Condition {
  def getPol = null

  def getTh = null

  def synthesis(consts: Map[String, PealAst]) = Not(Term(cond))
}
