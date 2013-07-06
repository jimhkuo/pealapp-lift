package peal.util

trait WhitespaceMatcher {

  val beEqualIgnoreWhiteSpace = (expected: String) => new EqualsIgnoreWhiteSpaceWord(expected)

}
