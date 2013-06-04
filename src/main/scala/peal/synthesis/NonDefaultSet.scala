package peal.synthesis

trait NonDefaultSet {

  def synthesis: String

  def not = synthesis.replaceAll(" ", "\n") //this is not done yet
}
