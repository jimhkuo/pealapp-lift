package peal.domain

import peal.synthesis.PolicySet

case class MaxPolicySet(lhs: PolicySet, rhs: PolicySet) extends PolicySet