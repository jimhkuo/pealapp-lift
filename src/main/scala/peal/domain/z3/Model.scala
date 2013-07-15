package peal.domain.z3


class Model(val satResult: SatResult, val defines: java.util.List[Define])

trait SatResult

object Unsat extends SatResult

object Sat extends SatResult