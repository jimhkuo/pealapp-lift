package peal.runner

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ReturnedModelAnalyserTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDealWithZ3Model() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:"
    val ReturnedModelAnalyser.spec(name) = input
    println(name)
    name should be("analysis1 = always_true? cond1")
  }

  @Test
  def testStatus() {
    val ReturnedModelAnalyser.status(s) = "sat"
    s should be ("sat")
    val ReturnedModelAnalyser.status(us) = "unsat"
    us should be ("unsat")
  }

  @Test
  def testResultAnalyser() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun cond1 () Bool\n\nResult of analysis [analysis2 = always_false? cond2]:\n\nunsat\n\n(error \"line 199 column 10: model is not available\")\n\nResult of analysis [analysis3 = different? cond1 cond2]:\n\nsat\n\n(model "
    val results = ReturnedModelAnalyser.execute(input)
    results should be(Map("analysis1 = always_true? cond1" -> "sat", "analysis2 = always_false? cond2" -> "unsat", "analysis3 = different? cond1 cond2" -> "sat"))
  }
}
