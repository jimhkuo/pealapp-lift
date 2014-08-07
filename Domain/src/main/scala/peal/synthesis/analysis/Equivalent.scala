package peal.synthesis.analysis

case class Equivalent(name: String, lhs: String, rhs: String) extends AnalysisGenerator {

  def z3SMTInput: String = "(declare-const equivalent_" + name + " Bool)\n" +
    "(assert (= equivalent_" + name + " (or (and " + lhs + " (not " + rhs + ")) (and (not " + lhs + ") " + rhs + "))))\n" +
    "(assert equivalent_" + name + ")\n" +
    "(check-sat)\n" +
    "(get-model)\n"

  def analysisName: String = name + " = equivalent? " + lhs + " " + rhs

}
