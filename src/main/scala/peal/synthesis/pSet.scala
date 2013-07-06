package peal.synthesis

import peal.domain.Pol
import z3.scala.{Z3AST, Z3Context}

trait pSet {

  def getPol : Pol = null

  def synthesis(z3: Z3Context, consts: Map[String, Z3AST]): Z3AST

  def phiZ3SMTString(z3: Z3Context, consts: Map[String, Z3AST]) = "(assert " + synthesis(z3, consts) + ")"
}
