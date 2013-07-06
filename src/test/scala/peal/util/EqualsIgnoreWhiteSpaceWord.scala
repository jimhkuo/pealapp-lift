package peal.util

import org.scalatest.matchers.{MatchResult, Matcher}

class EqualsIgnoreWhiteSpaceWord(expected: String) extends Matcher[String] {
  def apply(actual: String): MatchResult = MatchResult(actual.replaceAll("\n", " ").replaceAll("\\s+", " ").equals(expected),
    actual + " was not equal to "+ expected,
    actual + " was equal to "+ expected)
}
