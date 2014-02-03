package peal.domain

case class PlusPolicySet(lhs: PolicySet, rhs: PolicySet, name: String = "") extends PolicySet{
  def getPolicySetName = name
}