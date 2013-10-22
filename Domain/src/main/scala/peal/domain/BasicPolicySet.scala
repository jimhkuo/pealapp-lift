package peal.domain

case class BasicPolicySet(pol: PolicySet, name: String = "") extends PolicySet {
  val underlyingPolicyName = pol match {
    case p : Pol => p.policyName
  }

  def getPolicySetName = name
}
