package peal.synthesis

trait TopSet {

  def synthesis: String

  def notPhi = "(assert (not " + synthesis + "))\n" +
    "(check-sat)"
}
