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
  def testAnalyserWithNoise() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\nxx\n\n(model \n\n  (define-fun cond1 () Bool\n\nResult of analysis [analysis2 = always_false? cond2]:\n\nunsat\n\n(error \"line 199 column 10: model is not available\")\n\nResult of analysis [analysis3 = different? cond1 cond2]:\n\nsat\n\n(model "
    val results = ReturnedModelAnalyser.execute(input)
    results should be(Map("analysis1 = always_true? cond1" -> "sat", "analysis2 = always_false? cond2" -> "unsat", "analysis3 = different? cond1 cond2" -> "sat"))
  }

  @Test
  def testAnalyser() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun cond1 () Bool\n\nResult of analysis [analysis2 = always_false? cond2]:\n\nunsat\n\n(error \"line 199 column 10: model is not available\")\n\nResult of analysis [analysis3 = different? cond1 cond2]:\n\nsat\n\n(model "
    val results = ReturnedModelAnalyser.execute(input)
    results should be(Map("analysis1 = always_true? cond1" -> "sat", "analysis2 = always_false? cond2" -> "unsat", "analysis3 = different? cond1 cond2" -> "sat"))
  }

  @Test
  def testBroken() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 3297695122422727.0 10000000000000000.0))\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b1_score_q4 () Real\n    (/ 3297695122422727.0 10000000000000000.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b3_score () Real\n    (/ 9608100433152293.0 10000000000000000.0))\n  (define-fun b0_score () Real\n    (/ 9826658878892659.0 10000000000000000.0))\n  (define-fun b3_score_q0 () Real\n    (/ 9608100433152293.0 10000000000000000.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun b2_score_q1 () Real\n    0.0)\n  (define-fun p2_3_score () Real\n    (/ 428601946740631.0 10000000000000000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun p0_3_score () Real\n    (/ 3297695122422727.0 10000000000000000.0))\n  (define-fun b0_score_q3 () Real\n    (/ 9826658878892659.0 10000000000000000.0))\n  (define-fun b2_score () Real\n    (/ 428601946740631.0 10000000000000000.0))\n  (define-fun p0_1_score () Real\n    (/ 3297695122422727.0 10000000000000000.0))\n)\nResult of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 3762831919918751.0 5000000000000000.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b3_score () Real\n    (/ 9608100433152293.0 10000000000000000.0))\n  (define-fun b0_score () Real\n    (/ 9826658878892659.0 10000000000000000.0))\n  (define-fun b3_score_q0 () Real\n    (/ 9608100433152293.0 10000000000000000.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun b2_score_q1 () Real\n    0.0)\n  (define-fun p2_3_score () Real\n    (/ 428601946740631.0 10000000000000000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun p0_3_score () Real\n    (/ 3762831919918751.0 5000000000000000.0))\n  (define-fun b0_score_q3 () Real\n    (/ 9826658878892659.0 10000000000000000.0))\n  (define-fun always_false_analysis2 () Bool\n    true)\n  (define-fun b2_score () Real\n    (/ 428601946740631.0 10000000000000000.0))\n  (define-fun p0_1_score () Real\n    (/ 3762831919918751.0 5000000000000000.0))\n)\nResult of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 3762831919918751.0 5000000000000000.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b3_score () Real\n    (/ 9608100433152293.0 10000000000000000.0))\n  (define-fun b0_score () Real\n    (/ 5847164633738419.0 10000000000000000.0))\n  (define-fun b3_score_q0 () Real\n    (/ 9608100433152293.0 10000000000000000.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun b2_score_q1 () Real\n    0.0)\n  (define-fun p2_3_score () Real\n    (/ 428601946740631.0 10000000000000000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun p0_3_score () Real\n    (/ 5847164633738419.0 10000000000000000.0))\n  (define-fun b0_score_q3 () Real\n    1.0)\n  (define-fun b2_score () Real\n    (/ 428601946740631.0 10000000000000000.0))\n  (define-fun p0_1_score () Real\n    (/ 5847164633738419.0 10000000000000000.0))\n)"
    val results = ReturnedModelAnalyser.execute(input)
    results should be(Map("analysis1 = always_true? cond1" -> "sat", "analysis2 = always_false? cond2" -> "sat", "analysis3 = different? cond1 cond2" -> "sat"))
  }
}
