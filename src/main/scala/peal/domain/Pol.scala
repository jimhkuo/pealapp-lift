package peal.domain


import scala.collection.JavaConversions._
import peal.domain.operator.Operators
import peal.synthesis.pSet

case class Pol(val rules: java.util.List[Rule], val operator: Operators, val defaultScore: Double) extends pSet {
  override def toString: String = rules.mkString(" ") + " default " + defaultScore + "\n"

  override def getPol = this

  def synthesis: String = null

  def z3SMTHeader: String = null
}