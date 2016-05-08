package peal.verifier

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rational, PealFalse}
import peal.util.ConsoleLogger


class Z3ModelExtractorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanExtractRational() {
    val model = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n)"
    Z3ModelExtractor.extractIAndStatusUsingRational(model)("analysis1")._2 should be(Map("b1_score" -> Left(Rational(1, 2))))
}

  @Test
  def testCanExtractIUsingRationalBigDecimal() {
    val model = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun b () Real\n    (- 1.5))\n )"
    Z3ModelExtractor.extractIAndStatusUsingRational(model)("analysis1")._2 should be(Map("b" -> Left(Rational(-3, 2))))
  }

  @Test
  def testCanExtractIForNewInput() {
    ConsoleLogger.enable()
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  (define-fun q0 () Bool\n    false)\n  (define-fun n0 () MethodName\n    MethodName!val!1)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun q8 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun x2 () Real\n    (- (/ 2286.0 1645.0)))\n  (define-fun x1 () Real\n    (- (/ 4572.0 4493.0)))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun n1 () MethodName\n    MethodName!val!0)\n  (define-fun q3 () Bool\n    true)\n  (define-fun a1 () Int\n    0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun a0 () Int\n    0)\n  (define-fun q6 () Bool\n    true)\n  (define-fun calledBy ((x!1 MethodName)) Bool\n    false)\n)"
    ConsoleLogger.log(Z3ModelExtractor.extractIAndStatusUsingRational(model)("analysis1")) //should be(Map("b" -> Left(Rational(-3, 2))))
  }
}
