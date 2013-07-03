package peal

import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class ThLessThanMax(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis = rhs match {
    case _ : Pol => "(or " + new ThLessThanPol(lhs, th).synthesis + " " + new ThLessThanPol(rhs.getPol, th).synthesis + ")"
    case _ => "(or " + new ThLessThanPol(lhs, th).synthesis + " " + rhs.synthesis + ")"
  }


  def z3SMTHeader: String = {

    val s = rhs match {
      case _: Pol => rhs.getPol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")
      case _ => rhs.z3SMTHeader
    }

    lhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n") + s
  }
}