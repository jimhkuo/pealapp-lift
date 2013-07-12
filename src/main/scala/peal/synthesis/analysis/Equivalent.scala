package peal.synthesis.analysis

class Equivalent(name: String, lhs: String, rhs: String) extends AnalysisGenerator {

   def z3SMTInput: String = "(push)\n" +
     "(declare-const equivalent_" + name + " Bool)\n" +
     "(assert (= equivalent_" + name + " (and (implies " + lhs + " " + rhs + ") (implies " + rhs + " " + lhs + "))))\n" +
     "(assert equivalent_" + name + ")\n" +
     "(check-sat)\n" +
     "(get-model)\n" +
     "(pop)\n"
  def analysisName: String = name + " = equivalent? " + lhs + " " + rhs

}
