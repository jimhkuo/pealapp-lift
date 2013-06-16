package peal.synthesis

trait TopSet {

  def synthesis: String

  def not = "(not " + synthesis + ")"
}
