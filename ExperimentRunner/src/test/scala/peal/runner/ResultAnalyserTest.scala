package peal.runner

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ResultAnalyserTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDealWithZ3Model() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:"
    val ResultAnalyser.spec(name) = input
    println(name)
    name should be("analysis1 = always_true? cond1")
  }

  @Test
  def testStatus() {
    val ResultAnalyser.status(s) = "  sat  "
    s should be ("sat")
    val ResultAnalyser.status(us) = "  unsat  "
    us should be ("unsat")
  }

  @Test
  def testResultAnalyser() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun cond1 () Bool\n\n    false)\n\n  (define-fun q5 () Bool\n\n    false)\n\n  (define-fun q0 () Bool\n\n    false)\n\n  (define-fun always_true_analysis1 () Bool\n\n    false)"
    val results = ResultAnalyser.execute(input)
    results should be(Map("analysis1 = always_true? cond1" -> "sat"))
  }
}
