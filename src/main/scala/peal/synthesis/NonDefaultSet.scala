package peal.synthesis

trait NonDefaultSet {

  def synthesis: String

  def notPhi = "(not " + synthesis + ")"

  //   .replaceAll("""(\S)+""", "!$1") //polarity change
}
