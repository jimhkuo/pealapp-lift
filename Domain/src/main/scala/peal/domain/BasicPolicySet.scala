package peal.domain

case class BasicPolicySet(pol: PolicySet) extends PolicySet {
  val underlyingPolicyName = pol match {
    case p : Pol => p.policyName
  }
}
