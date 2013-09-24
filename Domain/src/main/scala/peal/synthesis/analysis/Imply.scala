package peal.synthesis.analysis

case class Imply(name: String, lhs: String, rhs: String) extends AnalysisGenerator {

   def z3SMTInput: String = "(push)\n" +
     "(declare-const imply_" + name + " Bool)\n" +
     "(assert (= imply_" + name + " (or (not " + lhs + ") " + rhs + ")))\n" +
     "(assert imply_" + name + ")\n" +
     "(check-sat)\n" +
     "(get-model)\n" +
     "(pop)\n"
  def analysisName: String = name + " = equivalent? " + lhs + " " + rhs

}
