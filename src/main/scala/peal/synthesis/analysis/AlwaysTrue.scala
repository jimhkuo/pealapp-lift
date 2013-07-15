package peal.synthesis.analysis


case class AlwaysTrue(name: String, cond: String) extends AnalysisGenerator {

  def z3SMTInput: String = "(push)\n" +
    "(declare-const always_true_" + name + " Bool)\n" +
    "(assert (= always_true_" + name + " " + cond + "))\n" +
    "(assert (not always_true_" + name + "))\n" +
    "(check-sat)\n" +
    "(get-model)\n" +
    "(pop)\n"
  def analysisName: String = name + " = always_true? " + cond

}
