package peal.synthesis

trait NonDefaultSet {

  def synthesis: String

  def not = "(not " + synthesis + ")"

  //   .replaceAll("""(\S)+""", "!$1") //polarity change
}
