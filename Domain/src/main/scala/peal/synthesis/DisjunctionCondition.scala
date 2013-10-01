package peal.synthesis

import peal.domain.z3.{Or, Term, PealAst}

class DisjunctionCondition(lhs: String, rhs: String) extends Condition {
   def getPol = None

   def getTh = null

   def synthesis(consts: Map[String, PealAst]) = Or(Term(lhs), Term(rhs))
 }
