package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.{Multiplier, VariableFormula, Score}

class Z3ScoreGeneratorTest extends ShouldMatchersForJUnit {

  @Test
  def testDealWithReal() {
    Z3ScoreGenerator.generate(new Score(Left(0.3), None)) should be ("0.3")
  }

  @Test
  def testDealWithVariable() {
    Z3ScoreGenerator.generate(new Score(Right(VariableFormula("x")), None)) should be ("x")
  }

  @Test
  def testDealWithVariableWithMultiplier() {
    Z3ScoreGenerator.generate(new Score(Right(VariableFormula(Multiplier(0.5, "y"))), None)) should be ("(* 0.5 y)")
  }
}
