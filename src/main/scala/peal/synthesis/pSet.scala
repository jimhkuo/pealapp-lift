package peal.synthesis

import peal.domain.Pol
import z3.scala.{Z3AST, Z3Context}

//TODO this should probably be renamed to Condition
trait pSet {
  //TODO synthesis should go with cond and only triggered in analysis

  def getPol : Pol = null

  def synthesis(z3: Z3Context, consts: Map[String, Z3AST]): Z3AST

  def phiZ3SMTString(z3: Z3Context, consts: Map[String, Z3AST]) = "(assert " + synthesis(z3, consts) + ")"
}
