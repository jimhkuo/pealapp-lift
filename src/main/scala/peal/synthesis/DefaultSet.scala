package peal.synthesis

import z3.scala.Z3Context

trait DefaultSet {

  def synthesis(z3:Z3Context): String

}
