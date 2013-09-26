package peal.synthesis

import peal.domain.z3.{And, Term, Not, PealAst}

class ConjunctionCondition(lhs: String, rhs: String) extends Condition {
   def getPol = null

   def getTh = null

   def synthesis(consts: Map[String, PealAst]) = And(Term(lhs), Term(rhs))
 }
