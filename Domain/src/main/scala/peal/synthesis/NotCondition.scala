package peal.synthesis

import peal.domain.z3.{Term, Not, PealAst}

case class NotCondition(condName: String) extends Condition {
  def getPol = None

  def getTh = null

  def synthesis(consts: Map[String, PealAst]) = Not(Term(condName))
}
