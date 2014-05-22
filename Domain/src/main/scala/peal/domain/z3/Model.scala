package peal.domain.z3

class Model(val satResult: SatResult, val assignment: java.util.List[Assignment]) {
  override def toString = satResult.toString + " " + assignment.toString
}

