package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class MinLessThanTh(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis = rhs match {
    case _: Pol => "(or " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs.getPol, th).synthesis + ")"
    case _ => "(or " + new PolLessThanTh(lhs, th).synthesis + " " + rhs.synthesis + ")"
  }

  def synthesisByZ3(const: Map[String, Z3AST], z3: Z3Context): Z3AST = z3.mkBoolConst("a")

}
