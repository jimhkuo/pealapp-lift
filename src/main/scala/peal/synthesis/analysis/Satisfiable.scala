package peal.synthesis.analysis

class Satisfiable(name: String, cond: String) extends AnalysisGenerator {

   def z3SMTInput: String = "(push)\n" +
     "(declare-const satisfiable_" + name + " Bool)\n" +
     "(assert (= satisfiable_" + name + " " + cond + "))\n" +
     "(assert satisfiable_" + name + ")\n" +
     "(check-sat)\n" +
     "(get-model)\n" +
     "(pop)\n"
  def analysisType: String = "satisfiable?"
}
