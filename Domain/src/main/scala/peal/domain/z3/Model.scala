package peal.domain.z3
import scala.collection.JavaConversions._

//TODO assignments is no good as a list
class Model(val satResult: SatResult, val assignments: java.util.List[Assignment]) {
  override def toString = satResult.toString + "\n" + assignments.mkString("\n")
}

