package peal.input

object ExplicitSynthesisChecker {

  //TODO notes
  //  1. ci contains some * policy pi and pi itself contains some score that is not in [0,1].
  //  2. ci contains some + policy pi and pi itself contains some score that is negative or non-constant.
  //  3. ci contains some min or max policy pi and pi itself contains some score that is non-constant.
  //  4. ci contains some "pS1 cop pS2" expression such that neither pS1 nor pS2 are <score> expressions (or of form as in (1) above).
  //
  //  If none of these three conditions are true then we know the following:
  //  1'. All * policies in ci have constant scores within [0,1].
  //  2'. All + policies within ci have constant, positive scores.
  //  3'. All min and max policies within ci have constant scores (positive or negative).
  //  4'. All comparison expressions within ci are such that at least one of its arguments is a score expression, which we view as a policy within ci according to (1).

}
