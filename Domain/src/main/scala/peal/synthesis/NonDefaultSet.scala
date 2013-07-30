package peal.synthesis
import _root_.z3.scala.{Z3AST, Z3Context}


trait NonDefaultSet {

  def synthesis(z3:Z3Context, consts: Map[String, Z3AST]): Z3AST

  def notPhi(z3:Z3Context, consts: Map[String, Z3AST]) =  z3.mkNot(synthesis(z3, consts))
}
