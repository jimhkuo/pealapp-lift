package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class ThLessThanMin(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis(z3:Z3Context) = rhs match {
    case _ : Pol => "(and " + new ThLessThanPol(lhs, th).synthesis(z3) + " " + new ThLessThanPol(rhs.getPol, th).synthesis(z3) + ")"
    case _ => "(and " + new ThLessThanPol(lhs, th).synthesis(z3) + " " + rhs.synthesis(z3) + ")"
  }

}
