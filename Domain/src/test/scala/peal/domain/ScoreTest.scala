package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ScoreTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSetUpScoreWithNoRange() {
      new Score(Left[BigDecimal, VariableFormula](0.1), None)
  }

}
