package peal.synthesis

import peal.domain.z3.{And, Term, PealAst}

case class AndCondition(lhs: String, rhs: String) extends Condition {
   def getPol = None

   def getTh = null

   def synthesis(consts: Map[String, PealAst]) = And(Term(lhs), Term(rhs))
 }
