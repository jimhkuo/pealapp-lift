package peal.domain.z3
import scala.collection.JavaConversions._

class Model(val satResult: SatResult, val assignments: java.util.List[Assignment]) {
  override def toString = satResult.toString + "\n" + assignments.mkString("\n")
  def isSat = satResult == Sat
  def isUnSat = !isSat
}

