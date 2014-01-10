package peal.verify

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test


class ExplicitVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testCanVerifySimpleModel() {
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ExplicitVerifier.assignmentExtractor(model)("name1").defines.toString() should be ("[cond1 (Bool) = false, q1 (Bool) = false, q3 (Bool) = false, q2 (Bool) = false, always_true_name1 (Bool) = false]")
  }
}
