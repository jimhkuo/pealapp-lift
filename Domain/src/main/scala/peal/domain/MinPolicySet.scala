package peal.domain

case class MinPolicySet(lhs: PolicySet, rhs: PolicySet) extends PolicySet{
  var policySetName = ""
}