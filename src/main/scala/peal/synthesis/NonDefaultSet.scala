package peal.synthesis

import z3.scala.Z3Context

trait NonDefaultSet {

  def synthesis(z3:Z3Context): String

  def notPhi(z3:Z3Context) = "(not " + synthesis(z3) + ")"
}
