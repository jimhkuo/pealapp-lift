package peal

import _root_.z3.scala.{Z3Context, Z3AST}
import peal.domain.Pol
import peal.synthesis.Condition
import scala.collection.JavaConversions._


class ThLessThanMax(lhs: Pol, rhs: Condition, th: Double) extends Condition {

  def synthesis(z3 : Z3Context, consts: Map[String, Z3AST]) = rhs match {
    case _ : Pol => z3.mkOr(new ThLessThanPol(lhs, th).synthesis(z3,consts), new ThLessThanPol(rhs.getPol, th).synthesis(z3,consts))//"(or " + new ThLessThanPol(lhs, th).synthesis(z3,consts) + " " + new ThLessThanPol(rhs.getPol, th).synthesis(z3,consts) + ")"
    case _ => z3.mkOr(new ThLessThanPol(lhs, th).synthesis(z3,consts), rhs.synthesis(z3,consts))
  }

}