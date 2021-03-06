package peal.verifier.util

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain._
import peal.util.ConsoleLogger
import peal.verifier.Z3ModelExtractor


class ScoreEvaluatorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanPullOutStraightScore() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 5.0 12.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula("y")) , None), null).get should be (Rational("5","12"))
  }

  @Test
  def testNonExistentScoreReturn0() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 5.0 12.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    intercept[RuntimeException] {
      ScoreEvaluator.trueScore(new Score(Right(VariableFormula("y1")) , None), null).get should be (Rational("0"))
    }
  }

  @Test
  def testCanMultiplyScores() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun z () Real\n    (/ 5.0 8.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula(Multiplier(8, "z"))) , None), null).get should be (Rational("5"))
  }

  @Test
  def testCanLoadPurger() {
    implicit def purgeUnderscore(x: Multiplier): String = {
      x.name.contains("_score") match {
        case true => x.name.dropRight("_score".length)
        case _ => x.name
      }
    }
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun z () Real\n    (/ 5.0 8.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula(Multiplier(8, "z_score"))) , None), null).get should be (Rational("5"))
  }

  @Test
  def testSumming() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 1.0 2.0))\n" +
      "(define-fun y1 () Real\n    (/ 1.0 2.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula(Multiplier(1, "y")).add(Multiplier(1, "y1"))) , None), null).get should be (Rational("1"))
  }

  @Test
  def testSummingOverWithDefaultScore() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 1.0 2.0))\n" +
      "(define-fun y1 () Real\n    (/ 1.0 2.0))\n" +
      "(define-fun y2 () Real\n    2.0)\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula(Multiplier(1, "y")).add(Multiplier(1, "y1"))) , Some(ScoreRange(0,1))), "y2") should be (Some(Rational("3")))
  }

  @Test
  def testSummingOverWithUndefined() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 1.0 2.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula(Multiplier(1, "y")).add(Multiplier(1, "y1"))) , None), null) should be (None)
  }

  @Test
  def testSummingOverWithUndefinedDefaultScore() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun y () Real\n    (/ 1.0 2.0))\n" +
      "(define-fun y1 () Real\n    (/ 1.0 2.0))\n" +
      ")"
    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(model)("name1")._2
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula(Multiplier(1, "y")).add(Multiplier(1, "y1"))) , Some(ScoreRange(0,1))), "y2") should be (None)
  }
}
