package peal.synthesis

import peal.domain.z3.{True, PealAst}

class TrueCondition() extends Condition {
   def getPol = None

   def getTh = null

   def synthesis(consts: Map[String, PealAst]) = True()
 }
