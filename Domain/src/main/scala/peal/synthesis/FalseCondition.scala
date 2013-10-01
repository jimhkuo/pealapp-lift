package peal.synthesis

import peal.domain.z3.{False, PealAst}

class FalseCondition() extends Condition {
   def getPol = None

   def getTh = null

   def synthesis(consts: Map[String, PealAst]) = False()
 }
