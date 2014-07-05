package peal.verifier

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{Rational, PealFalse}
import peal.util.ConsoleLogger


class Z3ModelExtractorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanExtractSimpleModel() {
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    Z3ModelExtractor.extractI(model)("name1") should be(Map("q1" -> Right(PealFalse), "q2" -> Right(PealFalse), "q3" -> Right(PealFalse), "cond1" -> Right(PealFalse), "always_true_name1" -> Right(PealFalse)))
  }

  @Test
  def testCanExtractBrokenModel() {
    val model = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun cond1 () Bool\n\n    false)\n\n  (define-fun always_true_analysis1 () Bool\n\n    false)\n\n  (define-fun cond2 () Bool\n\n    false)\n\n)\n\nResult of analysis [analysis2 = always_false? cond2]:\n\nunsat\n\n(error \"line 25 column 10: model is not available\")\n\nResult of analysis [analysis3 = different? cond1 cond2]:\n\nunsat\n\n(error \"line 33 column 10: model is not available\")"
    Z3ModelExtractor.extractI(model)("analysis1") should be(Map("cond2" -> Right(PealFalse), "always_true_analysis1" -> Right(PealFalse), "cond1" -> Right(PealFalse)))
  }

  @Test
  def testCanExtractRational() {
    val model = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n)"
    Z3ModelExtractor.extractIUsingRational(model)("analysis1") should be(Map("b1_score" -> Left(Rational(1, 2))))
  }

  @Test
  def testCanExtractIUsingRationalBigDecimal() {
    val model = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun b () Real\n    (- 1.5))\n )"
    Z3ModelExtractor.extractIUsingRational(model)("analysis1") should be(Map("b" -> Left(Rational(-3, 2))))
  }

  @Test
  def testCanExtractIForNewInput() {
    ConsoleLogger.enable()
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  (define-fun q0 () Bool\n    false)\n  (define-fun n0 () MethodName\n    MethodName!val!1)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun q8 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun x2 () Real\n    (- (/ 2286.0 1645.0)))\n  (define-fun x1 () Real\n    (- (/ 4572.0 4493.0)))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun n1 () MethodName\n    MethodName!val!0)\n  (define-fun q3 () Bool\n    true)\n  (define-fun a1 () Int\n    0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun a0 () Int\n    0)\n  (define-fun q6 () Bool\n    true)\n  (define-fun calledBy ((x!1 MethodName)) Bool\n    false)\n)"
    ConsoleLogger.log(Z3ModelExtractor.extractIUsingRational(model)("analysis1")) //should be(Map("b" -> Left(Rational(-3, 2))))
  }

  @Test
  def testCanExtractIForNewerInput() {
    ConsoleLogger.enable()
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  ;; universe for MethodName:\n  ;;   MethodName!val!2 MethodName!val!3 MethodName!val!0 MethodName!val!1 \n  ;; -----------\n  ;; definitions for universe elements:\n  (declare-fun MethodName!val!2 () MethodName)\n  (declare-fun MethodName!val!3 () MethodName)\n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  ;; cardinality constraint:\n  (forall ((x MethodName))\n          (or (= x MethodName!val!2)\n              (= x MethodName!val!3)\n              (= x MethodName!val!0)\n              (= x MethodName!val!1)))\n  ;; -----------\n  (define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    true)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun x2 () Real\n    0.0)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q9 () Bool\n    false)\n  (define-fun n3 () MethodName\n    MethodName!val!2)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a1 () Int\n    1)\n  (define-fun n4 () MethodName\n    MethodName!val!1)\n  (define-fun q6 () Bool\n    true)\n  (define-fun q12 () Bool\n    true)\n  (define-fun a3 () Int\n    (- 1))\n  (define-fun a4 () Int\n    0)\n  (define-fun q7 () Bool\n    true)\n  (define-fun n0 () MethodName\n    MethodName!val!0)\n  (define-fun q8 () Bool\n    false)\n  (define-fun q11 () Bool\n    true)\n  (define-fun q10 () Bool\n    true)\n  (define-fun q14 () Bool\n    true)\n  (define-fun x1 () Real\n    (- (/ 565.0 4467.0)))\n  (define-fun q5 () Bool\n    true)\n  (define-fun n1 () MethodName\n    MethodName!val!3)\n  (define-fun q2 () Bool\n    false)\n  (define-fun x3 () Real\n    (/ 226.0 269.0))\n  (define-fun a0 () Int\n    0)\n  (define-fun x4 () Real\n    (/ 1281985.0 639144.0))\n  (define-fun x0 () Real\n    (- (/ 113.0 1000.0)))\n  (define-fun calledBy ((x!1 MethodName)) Bool\n    false)\n)"
    ConsoleLogger.log(Z3ModelExtractor.extractI(model)("analysis1")) //should be(Map("b" -> Left(Rational(-3, 2))))
  }

  @Test
  def testCanExtractModelFromExtendedSynthesis() {
    val model = "Result of analysis [name1 = satisfiable? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    0.0)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun satisfiable_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun b1_default_U () Real\n    0.0)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun b1_q1_U () Real\n    0.0)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun b2_q6_U () Real\n    0.0)\n  (define-fun x () Real\n    5.0)\n)"
    val assignments = Z3ModelExtractor.extractI(model)("name1")
    println(assignments)
  }

  @Test
  def testCanExtractModelFromComplicatedModel() {
    ConsoleLogger.enable()
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  ;; universe for MethodName:\n  ;;   MethodName!val!2 MethodName!val!3 MethodName!val!0 MethodName!val!1 \n  ;; -----------\n  ;; definitions for universe elements:\n  (declare-fun MethodName!val!2 () MethodName)\n  (declare-fun MethodName!val!3 () MethodName)\n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  ;; cardinality constraint:\n  (forall ((x MethodName))\n          (or (= x MethodName!val!2)\n              (= x MethodName!val!3)\n              (= x MethodName!val!0)\n              (= x MethodName!val!1)))\n  ;; -----------\n  (define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    true)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun x2 () Real\n    0.0)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q9 () Bool\n    false)\n  (define-fun n3 () MethodName\n    MethodName!val!2)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a1 () Int\n    1)\n  (define-fun n4 () MethodName\n    MethodName!val!1)\n  (define-fun q6 () Bool\n    true)\n  (define-fun q12 () Bool\n    true)\n  (define-fun a3 () Int\n    (- 1))\n  (define-fun a4 () Int\n    0)\n  (define-fun q7 () Bool\n    true)\n  (define-fun n0 () MethodName\n    MethodName!val!0)\n  (define-fun q8 () Bool\n    false)\n  (define-fun q11 () Bool\n    true)\n  (define-fun q10 () Bool\n    true)\n  (define-fun q14 () Bool\n    true)\n  (define-fun x1 () Real\n    (- (/ 565.0 4467.0)))\n  (define-fun q5 () Bool\n    true)\n  (define-fun n1 () MethodName\n    MethodName!val!3)\n  (define-fun q2 () Bool\n    false)\n  (define-fun x3 () Real\n    (/ 226.0 269.0))\n  (define-fun a0 () Int\n    0)\n  (define-fun x4 () Real\n    (/ 1281985.0 639144.0))\n  (define-fun x0 () Real\n    (- (/ 113.0 1000.0)))\n  (define-fun calledBy ((x!1 MethodName)) Bool\n    false)\n)"
    val assignments = Z3ModelExtractor.extractI(model)("analysis1")
    println(assignments)
  }
}
