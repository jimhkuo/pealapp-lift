package peal.synthesis

trait TopSet {

  def synthesis: String

  def header: String

  def notPhi = "(assert (not " + synthesis + "))\n" +
    "(check-sat)"
}
