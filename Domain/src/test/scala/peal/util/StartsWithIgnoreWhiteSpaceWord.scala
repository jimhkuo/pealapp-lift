package peal.util

import org.scalatest.matchers.{MatchResult, Matcher}

class StartsWithIgnoreWhiteSpaceWord(expectedInput: String) extends Matcher[Any] {
  def apply(actualInput: Any): MatchResult = {
    val actual: Array[String] = actualInput.toString.split("\n")
    val expected = expectedInput.split("\n")

    MatchResult(compareModel(actual, expected),
      actual + " was not equal to "+ expected + "\nactual\n" + actual.filterNot(expected.contains).mkString("\n") + " are not in expected\n\nexpected\n" +
        expected.filterNot(actual.contains).mkString("\n") + " are not in actual" ,
      actual + " was equal to "+ expected)
  }

  private def compareModel(actual: Array[String], expected: Array[String]) : Boolean = expected.forall(actual.contains)
}
