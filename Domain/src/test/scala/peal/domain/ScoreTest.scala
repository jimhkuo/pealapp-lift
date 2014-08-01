package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ScoreTest extends ShouldMatchersForJUnit {

  @Test
  def testCanSetUpScoreWithNoRange() {
      new Score(Left[BigDecimal, VariableFormula](0.1), None) //this ensures the construction does not throw exception
  }

  @Test
  def testRangeMaxMustBeGreaterThan0() {
    intercept[RuntimeException] {
      new Score(Left[BigDecimal, VariableFormula](0.1), Some(new ScoreRange(-10, -1)))
    }

    intercept[RuntimeException] {
      new Score(Left[BigDecimal, VariableFormula](0.1), Some(new ScoreRange(-1, -1)))
    }

    new Score(Left[BigDecimal, VariableFormula](0.1), Some(new ScoreRange(-1, 0)))
  }
}
