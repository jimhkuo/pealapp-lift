package peal

import peal.domain.Pol
import peal.synthesis.pSet
import scala.collection.JavaConversions._


class ThLessThanMax(lhs: Pol, rhs: pSet, th: Double) extends pSet {

  def synthesis = rhs match {
    case _ : Pol => "(or " + new ThLessThanPol(lhs, th).synthesis + " " + new ThLessThanPol(rhs.getPol, th).synthesis + ")"
    case _ => "(or " + new ThLessThanPol(lhs, th).synthesis + " " + rhs.synthesis + ")"
  }


  //TODO omit the second part if it's not a pol
  def z3SMTHeader: String = lhs.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n") +
    rhs.getPol.rules.map(p => "(declare-const " + p.q.name + " Bool)").mkString("", "\n", "\n")
}