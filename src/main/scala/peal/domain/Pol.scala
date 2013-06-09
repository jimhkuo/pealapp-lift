package peal.domain


import scala.collection.JavaConversions._


class Pol(val rules: java.util.List[Rule], val defaultScore: Double) {
  override def toString: String = rules.mkString(" ") + "default " + defaultScore
}