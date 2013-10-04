package peal.domain

case class BasicPolicySet(pol: PolicySet) extends PolicySet {
  val policyName = pol match {
    case p : Pol => p.name
  }
}
