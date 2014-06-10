package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class RuleTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutputRules() {
    new Rule(new Predicate("q1"), 0.5).toString should be ("(q1 0.5)")
  }

  @Test
  def testCanOutputZ3VariableString() {
    new Rule(new Predicate("q1"), new Score(Right(VariableFormula(Multiplier(7, "x"))), None)).toString should be ("(q1 7*x)")
  }

  @Test
  def testCanReturnCorrectScoreString() {
    new Rule(new Predicate("q1"), new Score(Right(VariableFormula(Multiplier(7, "x"))), None)).scoreZ3String should be ("(* 7 x)")
    new Rule(new Predicate("q1"), 0.4).scoreZ3String should be ("0.4")
  }
}
