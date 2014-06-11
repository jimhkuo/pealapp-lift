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
  def testPolMinWithRangedScore() {
    val input = "POLICIES\nb1 = min ((q1 x [0.2, 0.8]) (q2 0.4) (q3 0.9)) default x\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_q1_U () Real\n    (/ 1.0 5.0))\n  (define-fun b1_score () Real\n    (/ 7.0 10.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun x () Real\n    (/ 1.0 2.0))\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testDefaultScoreWithRange() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default x [0.5, 0.9]\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_default_U () Real\n    (/ 1.0 2.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    (/ 1.0 10.0))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
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
  def testPolMaxWithRange() {
    val input = "POLICIES\nb1 = max ((q1 0.8) (q2 x * 2 [-0.81, 4]) (q3 y [-99,-0.51])) default x\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0 < pSet1 \nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_q3_U () Real\n    (- (/ 51.0 100.0)))\n  (define-fun b1_score () Real\n    0.0)\n  (define-fun y () Real\n    (/ 51.0 100.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun b1_q2_U () Real\n    (- (/ 81.0 100.0)))\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    (/ 81.0 200.0))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
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
  def testPolPlusWithRange() {
    val input = "POLICIES\nb1 = + ((q1 0.2) (q2 x [0.3, 0.9]) (q3 0.1)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 3.0 4.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    true)\n  (define-fun b1_q2_U () Real\n    (/ 3.0 10.0))\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun x () Real\n    (/ 1.0 4.0))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
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
  def testPolMulWithRange() {
    val input = "POLICIES\nb1 = * ((q1 0.5) (q2 0.5) (q3 x [1,2])) default 0.4\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_q3_U () Real\n    1.0)\n  (define-fun b1_score () Real\n    (/ 3.0 4.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    (- (/ 1.0 4.0)))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
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

  @Test
  def testFailedCase3() {
    val input = "POLICIES\nb0 = max ((q1 0.2603 [-0.4133,0.4174])) default vc\nb1 = * ((q4 0.5325 [-0.0870,0.6493])) default 0.9825 [-0.6180,0.5811]\nb2 = + ((q1 0.8088*ve [-0.6325,0.6191])) default vy\nb3 = min ((q0 0.1884)) default 0.1066\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val model = "Result of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n  (define-fun b1_q4_U () Real\n    (/ 363.0 2500.0))\n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 6777.0 10000.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b0_score () Real\n    (/ 6777.0 10000.0))\n  (define-fun b2_q1_U () Real\n    0.0)\n  (define-fun b3_score () Real\n    (/ 471.0 2500.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun b1_default_U () Real\n    0.0)\n  (define-fun b0_q1_U () Real\n    (/ 2087.0 5000.0))\n  (define-fun always_false_analysis2 () Bool\n    true)\n  (define-fun b2_score () Real\n    (/ 471.0 2500.0))\n  (define-fun ve () Real\n    (/ 157.0 674.0))\n)"
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "analysis2")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase4() {
    val input = "POLICIES\nb0 = * ((q5 0.0950)) default 0.8718 [-0.4463,0.7353]\nb1 = * ((q1 0.0850*vo)) default 0.7574 [-0.1187,0.2284]\nb2 = min ((q2 0.7381 [-0.2884,0.7739]) (q4 0.4906) (q3 0.0999) (q1 0.3900*vc [-0.7165,0.0679])) default vi [-0.0617,0.7424]\nb3 = min ((q1 vf) (q4 0.2662 [-0.5723,0.4127]) (q0 0.9344*vh) (q2 0.6324*vc)) default 0.8180 [-0.7780,0.3191]\nb4 = + ((q4 vf) (q1 0.6339 [-0.8312,0.7318])) default vn [-0.0893,0.0162]\nb5 = + ((q4 0.5336*vy [-0.9878,0.7074]) (q0 0.4799*vz)) default vk\nb6 = max ((q3 0.8506*ve [-0.8144,0.9030]) (q1 0.9060*ve) (q4 0.9847 [-0.8179,0.0042])) default 0.4215\nb7 = max ((q1 v5) (q2 va [-0.3475,0.6834]) (q4 0.9048*vk)) default 0.6442*vx [-0.8859,0.9526]\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = +(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nANALYSES\nanalysis1 = always_true? cond1"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 6387.0 10000.0))\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b3_default_U () Real\n    0.0)\n  (define-fun b2_q2_U () Real\n    (- (/ 721.0 2500.0)))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b0_default_U () Real\n    (- (/ 2331.0 10000.0)))\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun va () Real\n    (- (/ 1387.0 10000.0)))\n  (define-fun q1 () Bool\n    false)\n  (define-fun b1_default_U () Real\n    (- (/ 1187.0 10000.0)))\n  (define-fun b7_q2_U () Real\n    0.0)\n  (define-fun vh () Real\n    0.0)\n  (define-fun b7_default_U () Real\n    0.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun b5_q4_U () Real\n    0.0)\n  (define-fun b2_default_U () Real\n    0.0)\n  (define-fun b2_score () Real\n    (/ 4497.0 10000.0))\n  (define-fun b6_q4_U () Real\n    0.0)\n  (define-fun b4_score () Real\n    0.0)\n  (define-fun b3_q4_U () Real\n    0.0)\n  (define-fun b6_score () Real\n    (/ 843.0 2000.0))\n  (define-fun b0_score () Real\n    (/ 6387.0 10000.0))\n  (define-fun vn () Real\n    0.0)\n  (define-fun vc () Real\n    0.0)\n  (define-fun b2_q1_U () Real\n    0.0)\n  (define-fun b4_q1_U () Real\n    0.0)\n  (define-fun b7_score () Real\n    (- (/ 1387.0 10000.0)))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b6_q3_U () Real\n    0.0)\n  (define-fun q2 () Bool\n    true)\n  (define-fun b5_score () Real\n    (- (/ 1387.0 10000.0)))\n  (define-fun vz () Real\n    (- (/ 1387.0 4799.0)))\n  (define-fun b4_default_U () Real\n    0.0)\n)"
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "analysis1")
//    println(input)
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase5() {
    val input = "POLICIES\nb0 = min ((q0 0.9962*vg [-0.2896,0.5176])) default ve\nb1 = + ((q5 0.3714*vw [-0.3220,0.4756])) default 0.7753\nb2 = * ((q2 0.5471*v1 [-0.7420,0.7544])) default vg\nb3 = + ((q3 vu [-0.9477,0.8046])) default 0.1862\nb4 = max ((q5 0.0647 [-0.5157,0.4387])) default 0.4194*vf\nb5 = min ((q1 0.0196*vf [-0.3389,0.4423])) default vt\nb6 = * ((q1 vu)) default v1\nb7 = max ((q1 vw)) default vi [-0.0250,0.5933]\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = +(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nANALYSES\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 17461.0 34285.0))\n  (define-fun b2_q2_U () Real\n    0.0)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b4_q5_U () Real\n    (/ 6783521.0 68570000.0))\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun vg () Real\n    (/ 17461000.0 34154717.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun b7_default_U () Real\n    0.0)\n  (define-fun q3 () Bool\n    true)\n  (define-fun b2_score () Real\n    0.0)\n  (define-fun vf () Real\n    (/ 1555000.0 335993.0))\n  (define-fun b5_q1_U () Real\n    0.0)\n  (define-fun v1 () Real\n    0.0)\n  (define-fun b4_score () Real\n    (/ 1122.0 6857.0))\n  (define-fun b6_score () Real\n    (/ 622.0 6857.0))\n  (define-fun vw () Real\n    (/ 622.0 6857.0))\n  (define-fun b0_score () Real\n    (/ 17461.0 34285.0))\n  (define-fun b7_score () Real\n    (/ 622.0 6857.0))\n  (define-fun q5 () Bool\n    true)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun vu () Real\n    (/ 622.0 6857.0))\n  (define-fun b3_q3_U () Real\n    (- (/ 622.0 6857.0)))\n  (define-fun q2 () Bool\n    true)\n  (define-fun b1_q5_U () Real\n    (/ 1189.0 2500.0))\n  (define-fun b5_score () Real\n    (/ 622.0 6857.0))\n  (define-fun b0_q0_U () Real\n    0.0)\n)"
    val model1: (ThreeWayBoolean, Set[String]) = new ExtendedOutputVerifier(input).verifyModel(model, "analysis3")
    model1._1 should be(PealTrue)
    println(model1._2)
  }
}
