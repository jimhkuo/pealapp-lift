package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ScoreSumTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleScoreExpression() {
      ScoreSum(Multiplier(0.5, "y")).toZ3Expression should be ("(* 0.5 y)")
      ScoreSum(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).toZ3Expression should be ("(+ (* 0.5 y) (* 0.4 x))")
  }

}
