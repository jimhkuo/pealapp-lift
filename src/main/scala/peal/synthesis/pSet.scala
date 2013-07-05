package peal.synthesis

import peal.domain.Pol
import z3.scala.{Z3AST, Z3Context}

trait pSet {

  def getPol : Pol = null

  def synthesis(z3: Z3Context): String

  def phiZ3SMTString(z3: Z3Context) = "(assert " + synthesis(z3) + ")"
}
