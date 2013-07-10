package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.Condition
import scala.collection.JavaConversions._


class MaxLessThanTh(lhs: Pol, rhs: Condition, th: Double) extends Condition {

  def synthesis(z3 : Z3Context,consts:Map[String,Z3AST]) = rhs match {
    case _: Pol => z3.mkAnd(new PolLessThanTh(lhs, th).synthesis(z3,consts),new PolLessThanTh(rhs.getPol, th).synthesis(z3,consts))
    case _ => z3.mkAnd(new PolLessThanTh(lhs, th).synthesis(z3,consts),rhs.synthesis(z3,consts))
  }

}