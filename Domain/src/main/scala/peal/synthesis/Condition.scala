package peal.synthesis

import z3.scala.{Z3Context, Z3AST}


trait Condition {
  def synthesis(z3: Z3Context, consts: Map[String, Z3AST]): Z3AST
}