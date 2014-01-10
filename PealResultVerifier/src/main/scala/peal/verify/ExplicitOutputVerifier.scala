package peal.verify

object ExplicitOutputVerifier {
  //TODO
  //extract predicate truth assignments from Z3 model to create I
  //take v from the truth assignment for the condition in the raw Z3 result, but need to perform external check first, see below (temporarily skipped)
  //need to pass in cond map as some Conditions only hold cond names

  //always_true? c1 -> c1 has to be false
  //always_false? c1 -> c1 has to be true
  //different? c1 c2 -> c1, c2 have to be different
  //equivalent? c1 c2 -> c1, c2 have to be same
  //implies? c1 c2 -> c1 has to be true, c2 has to be false

  //looking for q_i not available in I returns bottom

  //need to sets up 3 way truth value, true, false, and bottom

}
