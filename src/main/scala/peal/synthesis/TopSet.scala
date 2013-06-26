package peal.synthesis

trait TopSet {

  def synthesis: String

  def z3SMTHeader: String

  def notPhiZ3SMTString = "(assert " + synthesis + ")\n(check-sat)"
}
