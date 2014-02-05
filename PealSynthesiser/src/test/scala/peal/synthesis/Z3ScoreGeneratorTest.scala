package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.{ScoreRange, Multiplier, VariableFormula, Score}

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
  def testUncertaintyNameIsIgnoredIfNoRange() {
    Z3ScoreGenerator.generate(new Score(Right(VariableFormula("x")), None), "X") should be ("x")
  }

  @Test
  def testDealWithVariableAndRange() {
    Z3ScoreGenerator.generate(new Score(Right(VariableFormula("x")), Some(new ScoreRange(-0.3, 0.7))), "U") should be ("(+ x U)")
  }

  @Test
  def testDealWithVariableWithMultiplier() {
    Z3ScoreGenerator.generate(new Score(Right(VariableFormula(Multiplier(0.5, "y"))), None)) should be ("(* 0.5 y)")
  }
}
