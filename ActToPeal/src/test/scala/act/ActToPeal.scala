package act

object ActToPeal {

  def execute(act: Act): String = act match {
    case OrLeaf(name, lhs, rhs, others@_*) => "attack_success_probability = +((True prob_or_" + name + "_score)) default 0.0\n" + executeNext(act)
  }

  private def executeNext(act: Act): String = act match {
    case AndLeaf(name, lhs, rhs, others@_*) =>
      "prob_and_" + name + " = *((True " + policyName[Act](lhs) + ") (True " + policyName[Act](rhs) + ")" + others.map("(True " + policyName[Act](_) + ")").mkString(" ") + ") default 0.0\n" +
        executeNext(lhs) +
        executeNext(rhs) +
        others.map(executeNext).mkString
    case OrLeaf(name, lhs, rhs, others@_*) =>
      "prob_or_" + name + " = +((True 1.0) (True -1.0*prob_or_" + name + "_aux_score)) default 0.0\n" +
        "prob_or_" + name + "_aux = *(" + orAuxRules[Act](name, Seq(lhs, rhs) ++ others: _*).mkString(" ") + ") default 0.0\n" +
        orAux[Act](name, Seq(lhs, rhs) ++ others: _*).mkString +
        executeNext(lhs) +
        executeNext(rhs) +
        others.map(executeNext).mkString
    case AttLeaf(name, pred, prob, cost, impact) => "prob_att_leaf_" + name + " = +((" + pred + " " + prob + ")) default 0.0\n"
    case NotLeaf(name, dm) => "prob_not_" + name + " = +((True 1.0) (True -1.0*" + policyName[DmAct](dm) + ")) default 0.0\n" + executeNext(dm)
  }

  private def executeNext(dmAct: DmAct): String = dmAct match {
    case DetLeaf(name, pred, prob, cost) => "prob_det_leaf_" + name + " = +((" + pred + " " + prob + ")) default 0.0\n"
    case MitLeaf(name, pred, prob, cost) => "prob_mit_leaf_" + name + " = +((" + pred + " " + prob + ")) default 0.0\n"
    case AndDmAct(name, lhs, rhs, others@_*) =>
      "prob_and_" + name + " = *((True " + policyName[DmAct](lhs) + ") (True " + policyName[DmAct](rhs) + ")" + others.map("(True " + policyName[DmAct](_) + ")").mkString(" ") + ") default 0.0\n" +
      executeNext(lhs) +
      executeNext(rhs) +
      others.map(executeNext).mkString
    case OrDmAct(name, lhs, rhs, others@_*) =>
      "prob_or_" + name + " = +((True 1.0) (True -1.0*prob_or_" + name + "_aux_score)) default 0.0\n" +
        "prob_or_" + name + "_aux = *(" + orAuxRules[DmAct](name, Seq(lhs, rhs) ++ others: _*).mkString(" ") + ") default 0.0\n" +
        orAux[DmAct](name, Seq(lhs, rhs) ++ others: _*).mkString +
        executeNext(lhs) +
        executeNext(rhs) +
        others.map(executeNext).mkString
  }

  private def orAux[T](name: String, acts: T*) = for ((act, i) <- acts.zipWithIndex) yield ("prob_or_" + name + "_aux" + (i + 1) + " = +((True 1.0) (True -1.0*" + policyName[T](act) + ")) default 0.0\n")

  private def orAuxRules[T](name: String, acts: T*) = for ((act, i) <- acts.zipWithIndex) yield ("(True prob_or_" + name + "_aux" + (i + 1) + "_score)")

  private def policyName[T](act: T): String = act match {
    case AndLeaf(name, _, _, others@_*) => "prob_and_" + name + "_score"
    case OrLeaf(name, _, _, others@_*) => "prob_or_" + name + "_score"
    case NotLeaf(name, _) => "prob_not_" + name + "_score"
    case AttLeaf(name, _, _, _, _) => "prob_att_leaf_" + name + "_score"
    case AndDmAct(name, lhs, rhs, others@_*) => "prob_and_" + name + "_score"
    case DetLeaf(name, _, _, _) => "prob_det_leaf_" + name + "_score"
    case MitLeaf(name, _, _, _) => "prob_mit_leaf_" + name + "_score"
  }
}
