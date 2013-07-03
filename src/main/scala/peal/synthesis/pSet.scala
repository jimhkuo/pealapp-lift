package peal.synthesis

import peal.domain.Pol

trait pSet {

  def getPol : Pol = null

  def synthesis: String

  def z3SMTHeader: String

  def phiZ3SMTString = "(assert " + synthesis + ")\n(check-sat)"
}
