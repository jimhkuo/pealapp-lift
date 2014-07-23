package peal.verifier.util

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Multiplier, Rational, VariableFormula, Score}
import peal.verifier.Z3ModelExtractor


class ScoreEvaluatorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanPullOutStraightScore() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 5.0 12.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIUsingRational(model)("name1")
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula("y")) , None), null) should be (Rational("5","12"))
  }

  @Test
  def testNonExistantScoreReturn0() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 5.0 12.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIUsingRational(model)("name1")
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula("y1")) , None), null) should be (Rational("0"))
  }

  @Test
  def testCanMultiplyScores() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun z () Real\n    (/ 5.0 8.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIUsingRational(model)("name1")
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula(Multiplier(8, "z"))) , None), null) should be (Rational("5"))
  }
}
