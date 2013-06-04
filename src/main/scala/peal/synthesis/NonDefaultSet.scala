package peal.synthesis

trait NonDefaultSet {

  def synthesis: String

  def not = synthesis.replaceAll("q", "!q").replaceAll(" ", "X").replaceAll("\n", " ").replaceAll("X", "\n")

  //   .replaceAll("""(\S)+""", "!$1") //polarity change
}
