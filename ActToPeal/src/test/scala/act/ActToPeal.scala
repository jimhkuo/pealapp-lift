package act

object ActToPeal {

  def execute(act: Act): String = act match {
    case OrLeaf(name, lhs, rhs@_*) => "attack_success_probability = +((True prob_or_" + name + "_score)) default 0.0\n" + executeNext(lhs) + rhs.map(executeNext).mkString
  }

  private def executeNext(act: Act): String = act match {
    case AndLeaf(name, lhs, rhs@_*) =>
      "prob_and_" + name + " = *((True " + policyName(lhs) + "_score)" + rhs.map("(True " + policyName(_) + ")").mkString(" ") + ") default 0.0\n" +
        executeNext(lhs) +
        rhs.map(executeNext).mkString
    case OrLeaf(name, lhs, rhs@_*) =>
      "prob_or_" + name + "= +((True 1.0) (True -1.0*prob_or_" + name + "_aux_score)) default 0.0\n" +
        "prob_or_" + name + "_aux = *((True prob_or_" + name + "_aux1_score)" + orAuxRules(name, rhs: _*).mkString + ") default 0.0\n" +
        "prob_or_" + name + "_aux1 = +((True 1.0) (True -1.0*" + policyName(lhs) + ")) default 0.0\n" +
        orAux(name, rhs: _*).mkString
    case AttLeaf(name, pred, prob, cost, impact) => "prob_att_leaf_" + name + " = +((" + pred + " " + prob + ")) default 0.0"
    case NotLeaf(name, dm) => "prob_not_" + name + " = +((True -1.0* " + policyName(dm) + ")) default 0.0"
  }

  private def orAux(name: String, acts: Act*) = for ((act, i) <- acts.zipWithIndex) yield ("prob_or_" + name + "_aux" + (i + 2) + " = +((True 1.0) (True -1.0*" + policyName(act) + ")) default 0.0\n")


  private def orAuxRules(name: String, acts: Act*) = for ((act, i) <- acts.zipWithIndex) yield ("(True prob_or_" + name + "_aux" + (i + 2) + "_score)")

  private def policyName(act: Act): String = act match {
    case AndLeaf(name, _, rhs@_*) => "prob_and_" + name + "_score"
    case OrLeaf(name, _, rhs@_*) => "prob_or_" + name + "_score"
    case NotLeaf(name, _) => "prob_not_" + name + "_score"
    case AttLeaf(name, _, _, _, _) => "prob_att_leaf_" + name + "_score"
  }

  private def policyName(dmAct: DmAct): String = dmAct match {
    case AndDmAct(name, lhs, rhs@_*) => "prob_and_" + name + "_score"
  }
}
