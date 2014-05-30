package peal.extended

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}
import peal.domain.{ThreeWayBoolean, PealTrue}


class ExtendedOutputVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testPredicateCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond2 = q2\n" +
      "ANALYSES\nname1 = always_true? cond2"
    val model = "Result of analysis [name1 = always_true? cond2]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPredicateAndAndCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = q2\ncond3 = cond1 && cond2\nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPredicateAndOrCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = q2\ncond3 = cond1 || cond2\nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPredicateAndNotCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = !cond1\nANALYSES\nname1 = always_true? cond2"
    val model = "Result of analysis [name1 = always_true? cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 1.0 5.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testMaxPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testMinPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPlusPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.6) (q3 0.2)) default 0.6\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = +(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testMulPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.6) (q3 0.2)) default 0.6\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = *(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMin() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMax() {
    val input = "POLICIES\nb1 = max ((q1 0.8) (q2 0.7) (q3 0.4)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.5 < pSet1 \nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 2.0 5.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolPlus() {
    val input = "POLICIES\nb1 = + ((q1 0.2) (q2 0.3) (q3 0.1)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMul() {
    val input = "POLICIES\nb1 = * ((q1 0.5) (q2 0.5) (q3 1)) default 0.4\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testEvaluateFormulaVariablePlusNumber() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + 1\n" +
      "POLICY_SETS\npSet1 = b1\n" +
      "CONDITIONS\ncond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 3.0 2.0))\n  (define-fun x () Real\n    (/ 1.0 4.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testEvaluateFormulaVariablePlusVariable() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + y\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 3.0 2.0))\n  (define-fun y () Real\n    (/ 3.0 2.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    0.0)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testEvaluateFormulaSomePredicatesAreNotDefinedInI() {
    //Needs resetting bottom to false iteration logic
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + y\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.5 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 1.0 10.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    //    println(model)
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "name1")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2"
    val model = "Result of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    (/ 5.0 12.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    0.0)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    1.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    0.0)\n)"
    println(input)
    //    println(model)
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase1() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2"
    val model = "Result of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 20.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    (- (/ 1.0 6.0)))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 9.0 16.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    7.0)\n)"
    println(input)
    println(model)
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase15() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2"
    val model = "Result of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    (/ 5.0 12.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    0.0)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    1.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    0.0)\n)"
    println(input)
    println(model)
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase2() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    0.0)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 17.0 40.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    (/ 17.0 4.0))\n)\nResult of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    0.0)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 9.0 20.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    (/ 9.0 2.0))\n)"
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)

    val model2: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "name1")
    model1._1 should be(PealTrue)
    println(model2._2)

  }
}
