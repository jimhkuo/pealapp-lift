package peal.synthesis

import z3.scala.{Z3AST, Z3Context}

trait NonDefaultSet {

  def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): String

  def notPhi(z3:Z3Context, consts: Map[String, Z3AST]) = "(not " + synthesis(z3, consts) + ")"
}
