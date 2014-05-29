package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class VariableFormulaTest extends ShouldMatchersForJUnit {

  @Test
  def testSimpleScoreExpression() {
    VariableFormula(Multiplier(0.5, "y")).toZ3Expression should be("(* 0.5 y)")
    VariableFormula(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).toZ3Expression should be("(+ (* 0.5 y) (* 0.4 x))")
    VariableFormula(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).add(Multiplier(0.1, "z")).toZ3Expression should be("(+ (* 0.5 y) (* 0.4 x) (* 0.1 z))")
    VariableFormula(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).add(Multiplier(1)).toZ3Expression should be("(+ (* 0.5 y) (* 0.4 x) 1)")
  }

  @Test
  def testGetNames() {
    VariableFormula(Multiplier(0.5)).names should be(Set())
    VariableFormula(Multiplier(0.5, "y")).names should be(Set("y"))
    VariableFormula(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).names should be(Set("x", "y"))
    VariableFormula(Multiplier(0.5, "y")).add(Multiplier(0.4, "x")).add(Multiplier(0.1, "z")).names should be(Set("x", "y", "z"))
  }
}
