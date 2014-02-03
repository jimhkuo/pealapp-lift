package peal.synthesis

import peal.domain.z3.{And, Term, PealAst}

case class PredicateCondition(predicateName: String) extends Condition {
   def getPol = None

   def getTh = null

   def synthesis(consts: Map[String, PealAst]) = Term(predicateName)
 }
