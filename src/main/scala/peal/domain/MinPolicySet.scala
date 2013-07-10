package peal.domain

import peal.synthesis.PolicySet

case class MinPolicySet(lhs: PolicySet, rhs: PolicySet) extends PolicySet