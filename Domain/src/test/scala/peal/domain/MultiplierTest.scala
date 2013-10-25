package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class MultiplierTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleScoreExpression() {
      Multiplier(0.5).toZ3Expression should be ("0.5")
      Multiplier("z").toZ3Expression should be ("z")
      Multiplier(0.6, "z").toZ3Expression should be ("(* 0.6 z)")
  }

  @Test
  def testGetVariableNames() {
    Multiplier(0.5).name should be ("")
    Multiplier("z").name should be ("z")
    Multiplier(0.6, "z").name should be ("z")

  }
}
