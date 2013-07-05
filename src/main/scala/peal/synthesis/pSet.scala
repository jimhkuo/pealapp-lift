package peal.synthesis

import peal.domain.Pol
import z3.scala.{Z3AST, Z3Context}

trait pSet {

  def getPol : Pol = null

  def synthesis: String

  def phiZ3SMTString = "(assert " + synthesis + ")"

  def synthesisByZ3(const: Map[String, Z3AST], z3: Z3Context) : Z3AST
}
