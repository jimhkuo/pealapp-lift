package peal

import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class MinLessThanTh(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis = rhs match {
    case _: Pol => "(or " + new PolLessThanTh(lhs, th).synthesis + " " + new PolLessThanTh(rhs.getPol, th).synthesis + ")"
    case _ => "(or " + new PolLessThanTh(lhs, th).synthesis + " " + rhs.synthesis + ")"
  }


  def z3SMTHeader: String = {

    val s = rhs match {
      case _: Pol => rhs.getPol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")
      case _ => rhs.z3SMTHeader
    }

    lhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n") + s
  }
}
