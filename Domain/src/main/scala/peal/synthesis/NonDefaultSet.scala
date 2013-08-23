package peal.synthesis
import _root_.z3.scala.Z3Context
import peal.domain.z3.wrapper.{Not, PealAst}


trait NonDefaultSet {

  def synthesis(consts: Map[String, PealAst]): PealAst

  def notPhi(consts: Map[String, PealAst]) =  Not(synthesis(consts))
}
