package z3

import z3.scala.{Z3Model, Z3Solver}


object ModelGetter {

  def get(solver : Z3Solver) : (Option[Boolean], Z3Model) = {
    solver.checkAndGetModel
  }

}
