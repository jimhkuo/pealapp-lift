package peal.synthesis.analysis

case class Implies(name: String, lhs: String, rhs: String) extends AnalysisGenerator {

  def z3SMTInput: String = "(declare-const implies_" + name + " Bool)\n" +
    "(assert (= implies_" + name + " (and " + lhs + " (not " + rhs + "))))\n" +
    "(assert implies_" + name + ")\n" +
    "(check-sat)\n" +
    "(get-model)\n"

  def analysisName: String = name + " = implies? " + lhs + " " + rhs

}
