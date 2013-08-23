package peal.synthesis
import peal.domain.z3.{PealAst, Not}


trait NonDefaultSet {

  def synthesis(consts: Map[String, PealAst]): PealAst

  def notPhi(consts: Map[String, PealAst]) =  Not(synthesis(consts))
}
