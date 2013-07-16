package peal.util

import org.scalatest.matchers.{MatchResult, Matcher}

class EqualsIgnoreWhiteSpaceWord(expected: String) extends Matcher[Any] {
  def apply(actual: Any): MatchResult = MatchResult(actual.toString.replaceAll("\n", " ").replaceAll("\\s+", " ").equals(expected.trim),
    actual + " was not equal to "+ expected,
    actual + " was equal to "+ expected)
}
