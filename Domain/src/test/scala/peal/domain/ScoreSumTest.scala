package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ScoreSumTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleScoreExpression() {
    ScoreSum(Multiplier(0.5, "y")).toZ3Expression should be("(* 0.5 y)")
    ScoreSum(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).toZ3Expression should be("(+ (* 0.5 y) (* 0.4 x))")
    ScoreSum(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).add(Multiplier(0.1, "z")).toZ3Expression should be("(+ (* 0.5 y) (* 0.4 x) (* 0.1 z))")
  }

  @Test
  def testGetNames() {
    ScoreSum(Multiplier(0.5)).names should be(Set())
    ScoreSum(Multiplier(0.5, "y")).names should be(Set("y"))
    ScoreSum(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).names should be(Set("x", "y"))
    ScoreSum(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).add(Multiplier(0.1, "z")).names should be(Set("x", "y", "z"))
  }
}
