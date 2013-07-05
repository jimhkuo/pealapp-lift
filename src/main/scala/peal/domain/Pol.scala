package peal.domain


import scala.collection.JavaConversions._
import peal.domain.operator.Operators
import peal.synthesis.pSet
import z3.scala.{Z3Context, Z3AST}

case class Pol(val rules: java.util.List[Rule], val operator: Operators, val defaultScore: Double) extends pSet {
  override def toString: String = rules.mkString(" ") + " default " + defaultScore + "\n"

  override def getPol = this

  def synthesis(z3:Z3Context): String = null

  def z3SMTHeader: String = null

  def synthesisByZ3(const: Map[String, Z3AST], z3: Z3Context): Z3AST = null
}