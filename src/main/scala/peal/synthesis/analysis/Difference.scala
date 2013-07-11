package peal.synthesis.analysis

class Difference(name: String, lhs: String, rhs: String) extends AnalysisGenerator {

  def z3SMTInput: String = "(push)\n" +
    "(declare-const difference_" + name + " Bool)\n" +
    "(assert (= difference_" + name + " (or (and " + lhs + " (not " + rhs + ")) (and (not " + lhs + ") " + rhs + "))))\n" +
    "(assert difference_" + name + ")\n" +
    "(check-sat)\n" +
    "(get-model)\n" +
    "(pop)\n"

  def analysisType: String = "difference?"

}
