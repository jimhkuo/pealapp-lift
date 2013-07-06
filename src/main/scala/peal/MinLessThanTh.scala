package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class MinLessThanTh(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis(z3 : Z3Context,consts:Map[String,Z3AST]) = rhs match {
    case _: Pol => "(or " + new PolLessThanTh(lhs, th).synthesis(z3,consts) + " " + new PolLessThanTh(rhs.getPol, th).synthesis(z3,consts) + ")"
    case _ => "(or " + new PolLessThanTh(lhs, th).synthesis(z3,consts) + " " + rhs.synthesis(z3,consts) + ")"
  }


}
