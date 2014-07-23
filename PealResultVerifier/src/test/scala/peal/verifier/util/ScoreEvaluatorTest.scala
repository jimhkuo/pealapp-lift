package peal.verifier.util

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rational, VariableFormula, Score}
import peal.verifier.Z3ModelExtractor


class ScoreEvaluatorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanPullOutStraightScore() {
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  " +
      "(define-fun b1_score () Real\n    (/ 1.0 2.0))\n  " +
      "(define-fun cond3 () Bool\n    false)\n  " +
      "(define-fun y () Real\n    (/ 5.0 12.0))\n" +
      "(define-fun q4 () Bool\n    true)\n  " +
      "(define-fun cond2 () Bool\n    false)\n  " +
      "(define-fun cond1 () Bool\n    true)\n " +
      "(define-fun q1 () Bool\n    false)\n  " +
      "(define-fun q5 () Bool\n    true)\n  " +
      "(define-fun b () Real\n    0.0)\n  " +
      "(define-fun cond4 () Bool\n    true)\n  " +
      "(define-fun q3 () Bool\n    false)\n  " +
      "(define-fun a () Real\n    1.0)\n  " +
      "(define-fun q2 () Bool\n    false)\n  " +
      "(define-fun z () Real\n    (/ 5.0 8.0))\n" +
      "(define-fun b2_score () Real\n    (/ 1.0 2.0))\n  " +
      "(define-fun implies_name6 () Bool\n    true)\n  " +
      "(define-fun q6 () Bool\n    true)\n  " +
      "(define-fun x () Real\n    0.0)\n)"
    implicit val I = Z3ModelExtractor.extractIUsingRational(model)("name1")
    ScoreEvaluator.trueScore(new Score(Right(VariableFormula("y")) , None), null) should be (Rational("5","12"))
  }
}
