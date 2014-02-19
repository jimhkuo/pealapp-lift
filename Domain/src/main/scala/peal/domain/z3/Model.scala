package peal.domain.z3

class Model(val satResult: SatResult, val defines: java.util.List[Define]) {
  override def toString = satResult.toString + " " + defines.toString
}

