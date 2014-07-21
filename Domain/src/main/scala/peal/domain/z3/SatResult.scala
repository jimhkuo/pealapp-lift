package peal.domain.z3

trait SatResult {
  def toString : String
}

object Unsat extends SatResult {
  override def toString = "Unsat"
}

object Sat extends SatResult  {
  override def toString = "Sat"
}

object Unknown extends SatResult  {
  override def toString = "Unknown"
}
