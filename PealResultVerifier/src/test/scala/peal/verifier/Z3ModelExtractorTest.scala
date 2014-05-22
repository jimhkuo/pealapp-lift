package peal.verifier

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test


class Z3ModelExtractorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanVerifySimpleModel() {
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    Z3ModelExtractor.extractAssignments(model)("name1").defines.toString() should be ("[cond1 (Bool) = false, q1 (Bool) = false, q3 (Bool) = false, q2 (Bool) = false, always_true_name1 (Bool) = false]")
  }

  @Test
  def testCanVerifyBrokenModel() {
    val model = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun cond1 () Bool\n\n    false)\n\n  (define-fun always_true_analysis1 () Bool\n\n    false)\n\n  (define-fun cond2 () Bool\n\n    false)\n\n)\n\nResult of analysis [analysis2 = always_false? cond2]:\n\nunsat\n\n(error \"line 25 column 10: model is not available\")\n\nResult of analysis [analysis3 = different? cond1 cond2]:\n\nunsat\n\n(error \"line 33 column 10: model is not available\")"
    Z3ModelExtractor.extractAssignments(model)("analysis1").defines.toString() should be ("[cond1 (Bool) = false, always_true_analysis1 (Bool) = false, cond2 (Bool) = false]")
  }
}
