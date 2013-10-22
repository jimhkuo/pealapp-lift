package peal.domain

case class MinPolicySet(lhs: PolicySet, rhs: PolicySet, name: String = "") extends PolicySet{
  def getPolicySetName = name
}