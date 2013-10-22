package peal.domain

case class MaxPolicySet(lhs: PolicySet, rhs: PolicySet, name: String = "") extends PolicySet{
  def getPolicySetName = name
}