package peal.domain


import scala.collection.JavaConversions._
import peal.domain.operator.Operators

class Pol(val rules: java.util.List[Rule], val operator: Operators, val defaultScore: Double) {
  override def toString: String = rules.mkString(" ") + " default " + defaultScore + "\n"
}