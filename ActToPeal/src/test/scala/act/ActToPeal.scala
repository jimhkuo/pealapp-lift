package act

object ActToPeal {

  def execute(act: Act): String = act match {
    case OrLeaf(name, lhs, rhs@_*) => "attack_success_probability = +((True prob_or_" + name + "_score)) default 0.0\n" + executeNext(lhs) + rhs.map(executeNext).mkString
  }

  private def executeNext(act: Act): String = act match {
    case AndLeaf(name, lhs, rhs@_*) => "prob_and_" + name + " = *((True " + policyName(lhs) + "_score)" + rhs.map("(True " + policyName(_) + "_score)").mkString(" ") + ") default 0.0\n"

  }

  private def policyName(act: Act): String = act match {
    case AndLeaf(name, _, _) => "prob_and_" + name
    case OrLeaf(name, _, _) => "prob_or_" + name
    case NotLeaf(name, _) => "prob_not_" + name
    case AttLeaf(name, _, _, _, _) => "prob_att_leaf_" + name
  }
}
