package peal.synthesis.analysis

case class Satisfiable(name: String, cond: String) extends AnalysisGenerator {

   def z3SMTInput: String = "(push)\n" +
     "(declare-const satisfiable_" + name + " Bool)\n" +
     "(assert (= satisfiable_" + name + " " + cond + "))\n" +
     "(assert satisfiable_" + name + ")\n" +
     "(check-sat)\n" +
     "(get-model)\n" +
     "(pop)\n"
  def analysisName: String = name + " = satisfiable? " + cond
}
