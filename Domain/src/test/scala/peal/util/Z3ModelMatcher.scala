package peal.util

trait Z3ModelMatcher {

  val beZ3Model = (expected: String) => new EqualsIgnoreWhiteSpaceWord(expected)

}
