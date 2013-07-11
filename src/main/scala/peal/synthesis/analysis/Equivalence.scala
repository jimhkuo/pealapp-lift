package peal.synthesis.analysis

class Equivalence(name: String, lhs: String, rhs: String) extends AnalysisGenerator {

   def z3SMTInput: String = "(push)\n" +
     "(declare-const equivalence_" + name + " Bool)\n" +
     "(assert (= equivalence_" + name + " (and (implies " + lhs + " " + rhs + ") (implies " + rhs + " " + lhs + "))))\n" +
     "(assert equivalence_" + name + ")\n" +
     "(check-sat)\n" +
     "(get-model)\n" +
     "(pop)\n"
  def analysisType: String = "equivalence?"

}
