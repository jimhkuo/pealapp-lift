package peal.domain


import scala.collection.JavaConversions._
import peal.domain.operator.Operators
import peal.synthesis.pSet

class Pol(val rules: java.util.List[Rule], val operator: Operators, val defaultScore: Double) extends pSet {
  override def toString: String = rules.mkString(" ") + " default " + defaultScore + "\n"

  def synthesis: String = ""

  def z3SMTHeader: String = ""
}