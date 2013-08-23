package peal.synthesis
import _root_.z3.scala.{Z3AST, Z3Context}
import peal.domain.z3.wrapper.{Not, PealAst}


trait NonDefaultSet {

  def synthesis(z3:Z3Context, consts: Map[String, PealAst]): PealAst

  def notPhi(z3:Z3Context, consts: Map[String, PealAst]) =  Not(synthesis(z3, consts))
}
