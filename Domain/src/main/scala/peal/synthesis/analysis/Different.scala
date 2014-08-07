package peal.synthesis.analysis

case class Different(name: String, lhs: String, rhs: String) extends AnalysisGenerator {

  def z3SMTInput: String = "(declare-const different_" + name + " Bool)\n" +
    "(assert (= different_" + name + " (or (and " + lhs + " (not " + rhs + ")) (and (not " + lhs + ") " + rhs + "))))\n" +
    "(assert different_" + name + ")\n" +
    "(check-sat)\n" +
    "(get-model)\n"

  def analysisName: String = name + " = different? " + lhs + " " + rhs

}
