package peal.synthesis.analysis

case class RobustGrant(name: String, cond: String) extends AnalysisGenerator {

  //TODO need to do
//  (and (and (sub(phi[cond])) (and (implies q1' q1) ... (implies qk' qk)))
//  (not phi[cond]))

  //need to do synthesis, then create sub(.)
  //(not really good)
  def z3SMTInput: String = ""

  def analysisName: String = name + " = robust_grant? " + cond

}
