package peal.domain

case class MulPolicySet(lhs: PolicySet, rhs: PolicySet, name: String = "") extends PolicySet{
  def getPolicySetName = name
}