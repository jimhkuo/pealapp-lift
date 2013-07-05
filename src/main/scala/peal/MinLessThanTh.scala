package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class MinLessThanTh(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis(z3 : Z3Context) = rhs match {
    case _: Pol => "(or " + new PolLessThanTh(lhs, th).synthesis(z3) + " " + new PolLessThanTh(rhs.getPol, th).synthesis(z3) + ")"
    case _ => "(or " + new PolLessThanTh(lhs, th).synthesis(z3) + " " + rhs.synthesis(z3) + ")"
  }


}
