package peal.util

trait StringMatcher {

  val beIgnoreWhiteSpaces = (expected: String) => new EqualsIgnoreWhiteSpaceWord(expected)

}
