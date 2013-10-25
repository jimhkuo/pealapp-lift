package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ScoreTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleScoreExpression() {
      Score(0.5).toZ3Expression should be ("0.5")
      Score("x").toZ3Expression should be ("x")
      Score(Multiplier(0.5, "y")).toZ3Expression should be ("(* 0.5 y)")
  }

//  @Test
//  def testNestedScoreExpression() {
//      Score(Multiplier("x")).toZ3Expression should be ("(+ x 1)")
//  }
}
