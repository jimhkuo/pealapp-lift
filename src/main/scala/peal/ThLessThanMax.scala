package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class ThLessThanMax(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis = rhs match {
    case _ : Pol => "(or " + new ThLessThanPol(lhs, th).synthesis + " " + new ThLessThanPol(rhs.getPol, th).synthesis + ")"
    case _ => "(or " + new ThLessThanPol(lhs, th).synthesis + " " + rhs.synthesis + ")"
  }

  def synthesisByZ3(const: Map[String, Z3AST], z3: Z3Context): Z3AST = null
}