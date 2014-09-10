package peal.verifier

import org.junit.{Ignore, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.{PealTrue, ThreeWayBoolean}
import peal.util.ConsoleLogger


class OutputVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testSelfConstructedScenarioToCatchInvalidUsagesOf_scoreValuesInIWithMultiplier() {
    //    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = + ((q0 0.1)) default 0.1\nb2 = + ((q1 1)) default b1_score * 0.5\nPOLICY_SETS\npSet1 = b2\nCONDITIONS\ncond1 = 0.5 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  " +
      "(define-fun cond1 () Bool\n    false)\n  " +
      "(define-fun b1_score () Real\n    (/ 1.0 10.0))\n  " +
      "(define-fun b2_score () Real\n    (/ 1.0 20.0))\n  " +
      "(define-fun q1 () Bool\n    false)\n  " +
      "(define-fun always_true_name1 () Bool\n    false)\n" +
      ")"

    println(input)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testSelfConstructedScenarioToCatchInvalidUsagesOf_scoreValuesInI() {
//    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = + ((q0 0.1)) default 0.1\nb2 = + ((q1 1)) default b1_score\nPOLICY_SETS\npSet1 = b2\nCONDITIONS\ncond1 = 0.5 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  " +
      "(define-fun cond1 () Bool\n    false)\n  " +
      "(define-fun b1_score () Real\n    (/ 1.0 10.0))\n " +
      "(define-fun b2_score () Real\n    (/ 1.0 10.0))\n  " +
      "(define-fun q1 () Bool\n    false)\n " +
      "(define-fun always_true_name1 () Bool\n    false)\n" +
      ")"

    println(input)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPredicateCondition() {
    ConsoleLogger.enable(1)
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
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPredicateAndAndCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = q2\ncond3 = cond1 && cond2\nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPredicateAndOrCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = q2\ncond3 = cond1 || cond2\nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPredicateAndNotCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = !cond1\nANALYSES\nname1 = always_true? cond2"
    val model = "Result of analysis [name1 = always_true? cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 1.0 5.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testMaxPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testMinPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPlusPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.6) (q3 0.2)) default 0.6\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = +(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testMulPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.6) (q3 0.2)) default 0.6\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = *(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
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
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMinWithRangedScore() {
    val input = "POLICIES\nb1 = min ((q1 x [-0.2, 0.8]) (q2 0.4) (q3 0.9)) default x\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_q1_U () Real\n    (/ 1.0 5.0))\n  (define-fun b1_score () Real\n    (/ 7.0 10.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun x () Real\n    (/ 1.0 2.0))\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testDefaultScoreWithRange() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default x [-0.5, 0.9]\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_default_U () Real\n    (/ 1.0 2.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    (/ 1.0 10.0))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMax() {
    val input = "POLICIES\nb1 = max ((q1 0.8) (q2 0.7) (q3 0.4)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.5 < pSet1 \nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 2.0 5.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMaxWithRange() {
    val input = "POLICIES\nb1 = max ((q1 0.8) (q2 x * 2 [-0.81, 4]) (q3 y [-99,0.51])) default x\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0 < pSet1 \nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_q3_U () Real\n    (- (/ 51.0 100.0)))\n  (define-fun b1_score () Real\n    0.0)\n  (define-fun y () Real\n    (/ 51.0 100.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun b1_q2_U () Real\n    (- (/ 81.0 100.0)))\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    (/ 81.0 200.0))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolPlus() {
    val input = "POLICIES\nb1 = + ((q1 0.2) (q2 0.3) (q3 0.1)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 3.0 5.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolPlusWithRange() {
    val input = "POLICIES\nb1 = + ((q1 0.2) (q2 x [-0.3, 0.9]) (q3 0.1)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 3.0 4.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    true)\n  (define-fun b1_q2_U () Real\n    (/ 3.0 10.0))\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun x () Real\n    (/ 1.0 4.0))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMul() {
    val input = "POLICIES\nb1 = * ((q1 0.5) (q2 0.5) (q3 1)) default 0.4\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testPolMulWithRange() {
    val input = "POLICIES\nb1 = * ((q1 0.5) (q2 0.5) (q3 x [-1,2])) default 0.4\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_q3_U () Real\n    1.0)\n  (define-fun b1_score () Real\n    (/ 3.0 4.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    (- (/ 1.0 4.0)))\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testEvaluateFormulaVariablePlusNumber() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + 1\n" +
      "POLICY_SETS\npSet1 = b1\n" +
      "CONDITIONS\ncond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 3.0 2.0))\n  (define-fun x () Real\n    (/ 1.0 4.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testEvaluateFormulaVariablePlusVariable() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + y\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 3.0 2.0))\n  (define-fun y () Real\n    (/ 3.0 2.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    0.0)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    new OutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testEvaluateFormulaSomePredicatesAreNotDefinedInI() {
    //Needs resetting bottom to false iteration logic
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + y\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.5 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 1.0 10.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    //    println(input)
    //    println(model)
    val model1= new OutputVerifier(input).verifyModel(model, "name1")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2"
    val model = "Result of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    (/ 5.0 12.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    0.0)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    1.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    0.0)\n)"
    println(input)
    println(model)
    val model1 = new OutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase1() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2"
    val model = "Result of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 20.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    (- (/ 1.0 6.0)))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 9.0 16.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    7.0)\n)"
    println(input)
    println(model)
    val model1 = new OutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase15() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2"
    val model = "Result of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    (/ 5.0 12.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    0.0)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    1.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    0.0)\n)"
    println(input)
    println(model)
    val model1 = new OutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCase2() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname6 = implies? cond1 cond2\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    0.0)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 17.0 40.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    (/ 17.0 4.0))\n)\nResult of analysis [name6 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    0.0)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 9.0 20.0))\n  (define-fun implies_name6 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x () Real\n    (/ 9.0 2.0))\n)"
    val model1 = new OutputVerifier(input).verifyModel(model, "name6")
    model1._1 should be(PealTrue)
    println(model1._2)

    val model2 = new OutputVerifier(input).verifyModel(model, "name1")
    model1._1 should be(PealTrue)
    println(model2._2)
  }

  @Test
  def testFailedCase3() {
    val input = "POLICIES\nb0 = max ((q1 0.2603 [-0.4133,0.4174])) default vc\nb1 = * ((q4 0.5325 [-0.0870,0.6493])) default 0.9825 [-0.6180,0.5811]\nb2 = + ((q1 0.8088*ve [-0.6325,0.6191])) default vy\nb3 = min ((q0 0.1884)) default 0.1066\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val model = "Result of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n  (define-fun b1_q4_U () Real\n    (/ 363.0 2500.0))\n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 6777.0 10000.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b0_score () Real\n    (/ 6777.0 10000.0))\n  (define-fun b2_q1_U () Real\n    0.0)\n  (define-fun b3_score () Real\n    (/ 471.0 2500.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun b1_default_U () Real\n    0.0)\n  (define-fun b0_q1_U () Real\n    (/ 2087.0 5000.0))\n  (define-fun always_false_analysis2 () Bool\n    true)\n  (define-fun b2_score () Real\n    (/ 471.0 2500.0))\n  (define-fun ve () Real\n    (/ 157.0 674.0))\n)"
    val model1 = new OutputVerifier(input).verifyModel(model, "analysis2")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCaseErrorInMaxWithNegScores() {
    val input = "POLICIES\nb0 = * ((q5 0.0950)) default 0.8718 [-0.4463,0.7353]\nb1 = * ((q1 0.0850*vo)) default 0.7574 [-0.1187,0.2284]\nb2 = min ((q2 0.7381 [-0.2884,0.7739]) (q4 0.4906) (q3 0.0999) (q1 0.3900*vc [-0.7165,0.0679])) default vi [-0.0617,0.7424]\nb3 = min ((q1 vf) (q4 0.2662 [-0.5723,0.4127]) (q0 0.9344*vh) (q2 0.6324*vc)) default 0.8180 [-0.7780,0.3191]\nb4 = + ((q4 vf) (q1 0.6339 [-0.8312,0.7318])) default vn [-0.0893,0.0162]\nb5 = + ((q4 0.5336*vy [-0.9878,0.7074]) (q0 0.4799*vz)) default vk\nb6 = max ((q3 0.8506*ve [-0.8144,0.9030]) (q1 0.9060*ve) (q4 0.9847 [-0.8179,0.0042])) default 0.4215\nb7 = max ((q1 v5) (q2 va [-0.3475,0.6834]) (q4 0.9048*vk)) default 0.6442*vx [-0.8859,0.9526]\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = +(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nANALYSES\nanalysis1 = always_true? cond1"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 6387.0 10000.0))\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b3_default_U () Real\n    0.0)\n  (define-fun b2_q2_U () Real\n    (- (/ 721.0 2500.0)))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b0_default_U () Real\n    (- (/ 2331.0 10000.0)))\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun va () Real\n    (- (/ 1387.0 10000.0)))\n  (define-fun q1 () Bool\n    false)\n  (define-fun b1_default_U () Real\n    (- (/ 1187.0 10000.0)))\n  (define-fun b7_q2_U () Real\n    0.0)\n  (define-fun vh () Real\n    0.0)\n  (define-fun b7_default_U () Real\n    0.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun b5_q4_U () Real\n    0.0)\n  (define-fun b2_default_U () Real\n    0.0)\n  (define-fun b2_score () Real\n    (/ 4497.0 10000.0))\n  (define-fun b6_q4_U () Real\n    0.0)\n  (define-fun b4_score () Real\n    0.0)\n  (define-fun b3_q4_U () Real\n    0.0)\n  (define-fun b6_score () Real\n    (/ 843.0 2000.0))\n  (define-fun b0_score () Real\n    (/ 6387.0 10000.0))\n  (define-fun vn () Real\n    0.0)\n  (define-fun vc () Real\n    0.0)\n  (define-fun b2_q1_U () Real\n    0.0)\n  (define-fun b4_q1_U () Real\n    0.0)\n  (define-fun b7_score () Real\n    (- (/ 1387.0 10000.0)))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b6_q3_U () Real\n    0.0)\n  (define-fun q2 () Bool\n    true)\n  (define-fun b5_score () Real\n    (- (/ 1387.0 10000.0)))\n  (define-fun vz () Real\n    (- (/ 1387.0 4799.0)))\n  (define-fun b4_default_U () Real\n    0.0)\n)"
    val model1 = new OutputVerifier(input).verifyModel(model, "analysis1")
    //    println(input)
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testFailedCaseAccumulationOfPolicySetNotInRational() {
    val input = "POLICIES\nb0 = min ((q0 0.9962*vg [-0.2896,0.5176])) default ve\nb1 = + ((q5 0.3714*vw [-0.3220,0.4756])) default 0.7753\nb2 = * ((q2 0.5471*v1 [-0.7420,0.7544])) default vg\nb3 = + ((q3 vu [-0.9477,0.8046])) default 0.1862\nb4 = max ((q5 0.0647 [-0.5157,0.4387])) default 0.4194*vf\nb5 = min ((q1 0.0196*vf [-0.3389,0.4423])) default vt\nb6 = * ((q1 vu)) default v1\nb7 = max ((q1 vw)) default vi [-0.0250,0.5933]\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = +(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nANALYSES\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 17461.0 34285.0))\n  (define-fun b2_q2_U () Real\n    0.0)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b4_q5_U () Real\n    (/ 6783521.0 68570000.0))\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun vg () Real\n    (/ 17461000.0 34154717.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun b7_default_U () Real\n    0.0)\n  (define-fun q3 () Bool\n    true)\n  (define-fun b2_score () Real\n    0.0)\n  (define-fun vf () Real\n    (/ 1555000.0 335993.0))\n  (define-fun b5_q1_U () Real\n    0.0)\n  (define-fun v1 () Real\n    0.0)\n  (define-fun b4_score () Real\n    (/ 1122.0 6857.0))\n  (define-fun b6_score () Real\n    (/ 622.0 6857.0))\n  (define-fun vw () Real\n    (/ 622.0 6857.0))\n  (define-fun b0_score () Real\n    (/ 17461.0 34285.0))\n  (define-fun b7_score () Real\n    (/ 622.0 6857.0))\n  (define-fun q5 () Bool\n    true)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun vu () Real\n    (/ 622.0 6857.0))\n  (define-fun b3_q3_U () Real\n    (- (/ 622.0 6857.0)))\n  (define-fun q2 () Bool\n    true)\n  (define-fun b1_q5_U () Real\n    (/ 1189.0 2500.0))\n  (define-fun b5_score () Real\n    (/ 622.0 6857.0))\n  (define-fun b0_q0_U () Real\n    0.0)\n)"
    val model1 = new OutputVerifier(input).verifyModel(model, "analysis3")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testInclusiveCase() {
    val input = "POLICIES\nb0 = * ((q0 0.7859) (q1 0.0043) (q4 0.7949*vh)) default v1\nb1 = + ((q0 vu [-0.8203,0.4180]) (q4 0.9958 [-0.2653,0.6868]) (q5 vt)) default vo\nb2 = max ((q0 0.9596*vy) (q5 0.7023) (q3 0.1111 [-0.0779,0.0834])) default vm [-0.5417,0.6000]\nb3 = min ((q5 0.2161*vi) (q1 vc) (q2 0.0097 [-0.8133,0.3069])) default vm\nb4 = + ((q1 vg [-0.0308,0.4651]) (q5 0.9173*vq [-0.4791,0.2551]) (q2 0.6015)) default vv\nb5 = min ((q5 0.7109 [-0.7006,0.4685]) (q2 0.2162) (q4 0.8998)) default vi\nb6 = * ((q4 v0) (q0 0.7165) (q5 0.2159*vn [-0.9342,0.9755])) default 0.9332\nb7 = max ((q0 0.7300 [-0.9210,0.0952]) (q3 0.6840*vp [-0.3423,0.3870]) (q4 0.6273*v4)) default vh\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = +(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun vq () Real\n    (/ 7002686261213.0 9173000000000.0))\n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (- (/ 2686261213.0 10000000000000.0)))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b4_q5_U () Real\n    0.0)\n  (define-fun b3_score () Real\n    (- (/ 2686261213.0 10000000000000.0)))\n  (define-fun vg () Real\n    0.0)\n  (define-fun q1 () Bool\n    true)\n  (define-fun b5_q5_U () Real\n    (- (/ 1106313738787.0 10000000000000.0)))\n  (define-fun v0 () Real\n    0.0)\n  (define-fun vh () Real\n    (- (/ 1.0 10.0)))\n  (define-fun b7_q3_U () Real\n    0.0)\n  (define-fun b2_default_U () Real\n    0.0)\n  (define-fun b2_score () Real\n    (/ 7023.0 10000.0))\n  (define-fun v4 () Real\n    0.0)\n  (define-fun b3_q2_U () Real\n    (- (/ 99686261213.0 10000000000000.0)))\n  (define-fun b7_q0_U () Real\n    (- (/ 73.0 100.0)))\n  (define-fun b4_score () Real\n    (/ 7002686261213.0 10000000000000.0))\n  (define-fun b1_q4_U () Real\n    (- (/ 2653.0 10000.0)))\n  (define-fun vy () Real\n    (/ 7023.0 9596.0))\n  (define-fun vi () Real\n    (- (/ 2686261213.0 2161000000000.0)))\n  (define-fun b6_score () Real\n    0.0)\n  (define-fun b0_score () Real\n    (- (/ 2686261213.0 10000000000000.0)))\n  (define-fun vn () Real\n    0.0)\n  (define-fun vc () Real\n    (- (/ 2686261213.0 10000000000000.0)))\n  (define-fun b7_score () Real\n    0.0)\n  (define-fun b4_q1_U () Real\n    0.0)\n  (define-fun b1_q0_U () Real\n    0.0)\n  (define-fun vp () Real\n    0.0)\n  (define-fun q5 () Bool\n    true)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun vu () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun b6_q5_U () Real\n    0.0)\n  (define-fun b5_score () Real\n    (/ 6002686261213.0 10000000000000.0))\n  (define-fun b2_q3_U () Real\n    0.0)\n  (define-fun vt () Real\n    (- (/ 7307686261213.0 10000000000000.0)))\n)"
    val model1 = new OutputVerifier(input).verifyModel(model, "analysis3")
    model1._1 should be(PealTrue)
    println(model1._2)
  }

  @Test
  def testInclusiveCase2() {
    val input = "POLICIES\nb0 = min ((q3 0*vn) (q6 0.1359)) default 2*vh\nb1 = + ((q13 0.3730) (q14 0.6929)) default vj\nb2 = + ((q14 0.6368) (q7 0.2781)) default 0.6354\nb3 = max ((q0 vb) (q11 va)) default 0.8859\nb4 = min ((q11 0.7874) (q3 0.6516)) default 0.8986\nb5 = max ((q13 2*vp) (q1 0.1372)) default 0.7511\nb6 = max ((q1 0.8283) (q3 2*vf)) default 0.4108\nb7 = * ((q13 0*ve) (q4 0.1400)) default 0.0941\nb8 = min ((q0 0.4863) (q14 0.8804)) default 0.3160\nb9 = * ((q7 0.1460) (q11 0.4322)) default 0.6763\nb10 = max ((q9 0.2923) (q14 0.7209)) default 0.6281\nb11 = + ((q8 0.8615) (q3 0.4483)) default 0.7302\nb12 = + ((q12 0.8650) (q11 0.4821)) default 0.3583\nb13 = * ((q0 0.7371) (q8 0.7559)) default 0.4107\nb14 = + ((q9 vk) (q2 0.4444)) default 0.0659\nb15 = min ((q5 0.0156) (q13 0.9952)) default 0.8097\nb16 = * ((q13 0.7887) (q14 0.9534)) default 2*vr\nb17 = max ((q3 0.3167) (q9 0.3092)) default 0.7999\nb18 = * ((q5 0.5374) (q1 v4)) default 0.1989\nb19 = min ((q9 0.2194) (q12 0.8734)) default 0.3319\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun q13 () Bool\n    true)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 373.0 1000.0))\n  (define-fun b14_score () Real\n    (/ 39.0 2500.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun vb () Real\n    (- (/ 8442596961.0 146162500000.0)))\n  (define-fun va () Real\n    0.0)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q9 () Bool\n    true)\n  (define-fun vk () Real\n    (- (/ 268.0 625.0)))\n  (define-fun q3 () Bool\n    true)\n  (define-fun b8_score () Real\n    (/ 4863.0 10000.0))\n  (define-fun b2_score () Real\n    (/ 3177.0 5000.0))\n  (define-fun vf () Real\n    (/ 8283.0 20000.0))\n  (define-fun v4 () Real\n    (/ 973.0 5374.0))\n  (define-fun q12 () Bool\n    true)\n  (define-fun b18_score () Real\n    (/ 973.0 10000.0))\n  (define-fun b4_score () Real\n    (/ 1629.0 2500.0))\n  (define-fun b11_score () Real\n    (/ 4483.0 10000.0))\n  (define-fun b12_score () Real\n    (/ 13471.0 10000.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 7887.0 10000.0))\n  (define-fun b6_score () Real\n    (/ 8283.0 10000.0))\n  (define-fun q8 () Bool\n    false)\n  (define-fun b19_score () Real\n    (/ 1097.0 5000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    0.0)\n  (define-fun b15_score () Real\n    (/ 39.0 2500.0))\n  (define-fun q14 () Bool\n    false)\n  (define-fun b7_score () Real\n    0.0)\n  (define-fun vp () Real\n    (/ 3167.0 23386.0))\n  (define-fun b17_score () Real\n    (/ 3167.0 10000.0))\n  (define-fun q5 () Bool\n    true)\n  (define-fun b10_score () Real\n    (/ 2923.0 10000.0))\n  (define-fun b13_score () Real\n    (/ 7371.0 10000.0))\n  (define-fun q2 () Bool\n    true)\n  (define-fun b9_score () Real\n    (/ 2161.0 5000.0))\n  (define-fun b5_score () Real\n    (/ 3167.0 11693.0))\n)"
    val result = new OutputVerifier(input).verifyModel(model, "analysis1")
    ConsoleLogger.log1(input)
    result._1 should be(PealTrue)
    println(result._2)
  }

  @Test
  def testCaseSomeVariablesAreNotSet() {
    //refinement is removed, now ScoreEvaluator will check 0 in multiplier
    ConsoleLogger.enable()
    val input = "POLICIES\nb0 = min ((q3 0*vn) (q6 0.1359)) default 2*vh\n" +
      "b1 = + ((q13 0.3730) (q14 0.6929)) default vj\n" +
      "b2 = + ((q14 0.6368) (q7 0.2781)) default 0.6354\n" +
      "b3 = max ((q0 vb) (q11 va)) default 0.8859\n" +
      "b4 = min ((q11 0.7874) (q3 0.6516)) default 0.8986\n" +
      "b5 = max ((q13 2*vp) (q1 0.1372)) default 0.7511\n" +
      "b6 = max ((q1 0.8283) (q3 2*vf)) default 0.4108\n" +
      "b7 = * ((q13 0*ve) (q4 0.1400)) default 0.0941\n" +
      "b8 = min ((q0 0.4863) (q14 0.8804)) default 0.3160\n" +
      "b9 = * ((q7 0.1460) (q11 0.4322)) default 0.6763\n" +
      "b10 = max ((q9 0.2923) (q14 0.7209)) default 0.6281\n" +
      "b11 = + ((q8 0.8615) (q3 0.4483)) default 0.7302\n" +
      "b12 = + ((q12 0.8650) (q11 0.4821)) default 0.3583\n" +
      "b13 = * ((q0 0.7371) (q8 0.7559)) default 0.4107\n" +
      "b14 = + ((q9 vk) (q2 0.4444)) default 0.0659\n" +
      "b15 = min ((q5 0.0156) (q13 0.9952)) default 0.8097\n" +
      "b16 = * ((q13 0.7887) (q14 0.9534)) default 2*vr\n" +
      "b17 = max ((q3 0.3167) (q9 0.3092)) default 0.7999\n" +
      "b18 = * ((q5 0.5374) (q1 v4)) default 0.1989\n" +
      "b19 = min ((q9 0.2194) (q12 0.8734)) default 0.3319\n" +
      "POLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
//    println(input)
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n" +
  "(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun q13 () Bool\n    true)\n" +
  "  (define-fun always_true_analysis1 () Bool\n    false)\n" +
  "  (define-fun b1_score () Real\n    (/ 373.0 1000.0))\n  " +
  "(define-fun b14_score () Real\n    (/ 39.0 2500.0))\n " +
  " (define-fun q4 () Bool\n    false)\n  " +
  "(define-fun cond2 () Bool\n    false)\n  " +
  "(define-fun cond1 () Bool\n    false)\n  " +
  "(define-fun b3_score () Real\n    0.0)\n" +
  "  (define-fun vb () Real\n    (- (/ 8442596961.0 146162500000.0)))\n " +
  " (define-fun va () Real\n    0.0)\n  " +
  "(define-fun q1 () Bool\n    true)\n " +
  " (define-fun q9 () Bool\n    true)\n " +
  " (define-fun vk () Real\n    (- (/ 268.0 625.0)))\n  " +
  "(define-fun q3 () Bool\n    true)\n  " +
  "(define-fun b8_score () Real\n    (/ 4863.0 10000.0))\n  " +
  "(define-fun b2_score () Real\n    (/ 3177.0 5000.0))\n  " +
  "(define-fun vf () Real\n    (/ 8283.0 20000.0))\n  " +
  "(define-fun v4 () Real\n    (/ 973.0 5374.0))\n  " +
  "(define-fun q12 () Bool\n    true)\n  " +
  "(define-fun b18_score () Real\n    (/ 973.0 10000.0))\n " +
  " (define-fun b4_score () Real\n    (/ 1629.0 2500.0))\n  " +
  "(define-fun b11_score () Real\n    (/ 4483.0 10000.0))\n" +
  "  (define-fun b12_score () Real\n    (/ 13471.0 10000.0))\n  " +
  "(define-fun q7 () Bool\n    false)\n  " +
  "(define-fun b16_score () Real\n    (/ 7887.0 10000.0))\n  " +
  "(define-fun b6_score () Real\n    (/ 8283.0 10000.0))\n  " +
  "(define-fun q8 () Bool\n    false)\n  " +
  "(define-fun b19_score () Real\n    (/ 1097.0 5000.0))\n " +
  " (define-fun q11 () Bool\n    true)\n " +
  " (define-fun b0_score () Real\n    0.0)\n " +
  " (define-fun b15_score () Real\n    (/ 39.0 2500.0))\n " +
  " (define-fun q14 () Bool\n    false)\n " +
  " (define-fun b7_score () Real\n    0.0)\n " +
  " (define-fun vp () Real\n    (/ 3167.0 23386.0))\n " +
  " (define-fun b17_score () Real\n    (/ 3167.0 10000.0))\n " +
  " (define-fun q5 () Bool\n    true)\n " +
  " (define-fun b10_score () Real\n    (/ 2923.0 10000.0))\n " +
  " (define-fun b13_score () Real\n    (/ 7371.0 10000.0))\n " +
  " (define-fun q2 () Bool\n    true)\n  " +
  "(define-fun b9_score () Real\n    (/ 2161.0 5000.0))\n  " +
  "(define-fun b5_score () Real\n    (/ 3167.0 11693.0))\n)"
    val result = new OutputVerifier(input).verifyModel(model, "analysis1")
    ConsoleLogger.log1(input)
    result._1 should be(PealTrue)
    println(result._2)
    println(result._3)
  }

  @Test
  def testInclusiveCase3() {
    val input = "POLICIES\nb0 = min ((q3 0*vn) (q6 0.1359)) default 2*vh\nb1 = + ((q13 0.3730) (q14 0.6929)) default vj\nb2 = + ((q14 0.6368) (q7 0.2781)) default 0.6354\nb3 = max ((q0 vb) (q11 va)) default 0.8859\nb4 = min ((q11 0.7874) (q3 0.6516)) default 0.8986\nb5 = max ((q13 2*vp) (q1 0.1372)) default 0.7511\nb6 = max ((q1 0.8283) (q3 2*vf)) default 0.4108\nb7 = * ((q13 0*ve) (q4 0.1400)) default 0.0941\nb8 = min ((q0 0.4863) (q14 0.8804)) default 0.3160\nb9 = * ((q7 0.1460) (q11 0.4322)) default 0.6763\nb10 = max ((q9 0.2923) (q14 0.7209)) default 0.6281\nb11 = + ((q8 0.8615) (q3 0.4483)) default 0.7302\nb12 = + ((q12 0.8650) (q11 0.4821)) default 0.3583\nb13 = * ((q0 0.7371) (q8 0.7559)) default 0.4107\nb14 = + ((q9 vk) (q2 0.4444)) default 0.0659\nb15 = min ((q5 0.0156) (q13 0.9952)) default 0.8097\nb16 = * ((q13 0.7887) (q14 0.9534)) default 2*vr\nb17 = max ((q3 0.3167) (q9 0.3092)) default 0.7999\nb18 = * ((q5 0.5374) (q1 v4)) default 0.1989\nb19 = min ((q9 0.2194) (q12 0.8734)) default 0.3319\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun q13 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 373.0 1000.0))\n  (define-fun vk () Real\n    (/ 3653.0 10000.0))\n  (define-fun b14_score () Real\n    (/ 8097.0 10000.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun vb () Real\n    (- (/ 3657783.0 100000000.0)))\n  (define-fun va () Real\n    0.0)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q9 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun b8_score () Real\n    (/ 4863.0 10000.0))\n  (define-fun b2_score () Real\n    (/ 3177.0 5000.0))\n  (define-fun vf () Real\n    (/ 8283.0 20000.0))\n  (define-fun v4 () Real\n    (/ 41717783.0 100000000.0))\n  (define-fun q12 () Bool\n    false)\n  (define-fun b18_score () Real\n    (/ 41717783.0 100000000.0))\n  (define-fun b4_score () Real\n    (/ 1629.0 2500.0))\n  (define-fun b11_score () Real\n    (/ 4483.0 10000.0))\n  (define-fun b12_score () Real\n    (/ 4821.0 10000.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 28012217.0 100000000.0))\n  (define-fun b6_score () Real\n    (/ 8283.0 10000.0))\n  (define-fun q8 () Bool\n    false)\n  (define-fun b19_score () Real\n    (/ 1097.0 5000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    0.0)\n  (define-fun b15_score () Real\n    (/ 8097.0 10000.0))\n  (define-fun q14 () Bool\n    false)\n  (define-fun b7_score () Real\n    (/ 941.0 10000.0))\n  (define-fun b17_score () Real\n    (/ 3167.0 10000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b10_score () Real\n    (/ 2923.0 10000.0))\n  (define-fun b13_score () Real\n    (/ 7371.0 10000.0))\n  (define-fun vj () Real\n    (/ 373.0 1000.0))\n  (define-fun vr () Real\n    (/ 28012217.0 200000000.0))\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_false_analysis2 () Bool\n    true)\n  (define-fun b5_score () Real\n    (/ 343.0 2500.0))\n  (define-fun b9_score () Real\n    (/ 2161.0 5000.0))\n)"
    val result = new OutputVerifier(input).verifyModel(model, "analysis2")
    result._1 should be(PealTrue)
    println(result._2)
  }

  @Test
  def testInclusiveCase4() {
    val input = "POLICIES\nb0 = min ((q3 0*vn) (q6 0.1359)) default 2*vh\nb1 = + ((q13 0.3730) (q14 0.6929)) default vj\nb2 = + ((q14 0.6368) (q7 0.2781)) default 0.6354\nb3 = max ((q0 vb) (q11 va)) default 0.8859\nb4 = min ((q11 0.7874) (q3 0.6516)) default 0.8986\nb5 = max ((q13 2*vp) (q1 0.1372)) default 0.7511\nb6 = max ((q1 0.8283) (q3 2*vf)) default 0.4108\nb7 = * ((q13 0*ve) (q4 0.1400)) default 0.0941\nb8 = min ((q0 0.4863) (q14 0.8804)) default 0.3160\nb9 = * ((q7 0.1460) (q11 0.4322)) default 0.6763\nb10 = max ((q9 0.2923) (q14 0.7209)) default 0.6281\nb11 = + ((q8 0.8615) (q3 0.4483)) default 0.7302\nb12 = + ((q12 0.8650) (q11 0.4821)) default 0.3583\nb13 = * ((q0 0.7371) (q8 0.7559)) default 0.4107\nb14 = + ((q9 vk) (q2 0.4444)) default 0.0659\nb15 = min ((q5 0.0156) (q13 0.9952)) default 0.8097\nb16 = * ((q13 0.7887) (q14 0.9534)) default 2*vr\nb17 = max ((q3 0.3167) (q9 0.3092)) default 0.7999\nb18 = * ((q5 0.5374) (q1 v4)) default 0.1989\nb19 = min ((q9 0.2194) (q12 0.8734)) default 0.3319\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun q13 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 373.0 1000.0))\n  (define-fun vk () Real\n    (/ 3653.0 10000.0))\n  (define-fun b14_score () Real\n    (/ 8097.0 10000.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun vb () Real\n    (- (/ 3657783.0 100000000.0)))\n  (define-fun va () Real\n    0.0)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q9 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun b8_score () Real\n    (/ 4863.0 10000.0))\n  (define-fun b2_score () Real\n    (/ 3177.0 5000.0))\n  (define-fun vf () Real\n    (/ 8283.0 20000.0))\n  (define-fun v4 () Real\n    (/ 1903.0 5000.0))\n  (define-fun q12 () Bool\n    false)\n  (define-fun b18_score () Real\n    (/ 1903.0 5000.0))\n  (define-fun b4_score () Real\n    (/ 1629.0 2500.0))\n  (define-fun b11_score () Real\n    (/ 4483.0 10000.0))\n  (define-fun b12_score () Real\n    (/ 4821.0 10000.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 28012217.0 100000000.0))\n  (define-fun b6_score () Real\n    (/ 8283.0 10000.0))\n  (define-fun q8 () Bool\n    false)\n  (define-fun b19_score () Real\n    (/ 1097.0 5000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    0.0)\n  (define-fun b15_score () Real\n    (/ 8097.0 10000.0))\n  (define-fun q14 () Bool\n    false)\n  (define-fun b7_score () Real\n    (/ 941.0 10000.0))\n  (define-fun b17_score () Real\n    (/ 3167.0 10000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun b10_score () Real\n    (/ 2923.0 10000.0))\n  (define-fun b13_score () Real\n    (/ 7371.0 10000.0))\n  (define-fun vj () Real\n    (/ 373.0 1000.0))\n  (define-fun vr () Real\n    (/ 28012217.0 200000000.0))\n  (define-fun q2 () Bool\n    true)\n  (define-fun b9_score () Real\n    (/ 2161.0 5000.0))\n  (define-fun b5_score () Real\n    (/ 343.0 2500.0))\n)"
    val result = new OutputVerifier(input).verifyModel(model, "analysis3")
    result._1 should be(PealTrue)
    println(result._2)
  }

  @Test
  def testFailedCase4() {
    val input = "POLICIES\nb0 = max ((q5 0.4029) (q14 0.1127 [-0.0027,0.6815])) default 0.7307\nb1 = + ((q9 0.9636) (q11 0.2012)) default 0.1073\nb2 = min ((q8 0.7054) (q6 0.3175 [-0.7931,0.1276])) default 0.7057\nb3 = * ((q6 0.8156) (q13 0.1693)) default 0.8282\nb4 = + ((q11 0.1068 [-0.3632,0.7380]) (q2 0.0645)) default 0.7872\nb5 = + ((q13 2*vt) (q5 0.1839)) default 2*vs\nb6 = * ((q0 0*vk) (q11 0.8569)) default 0.6636\nb7 = * ((q2 0.3446) (q3 0.3486)) default 0.9915\nb8 = * ((q6 0.8912) (q10 0.2351)) default 0.5547\nb9 = min ((q8 0.3896) (q10 0.6011)) default 0*vu\nb10 = max ((q3 0.0737) (q8 0.0136)) default 0.9945\nb11 = max ((q11 3*vf) (q2 0.0847)) default 0.6194\nb12 = + ((q3 0.4573) (q2 0.2543)) default 0.7492\nb13 = + ((q11 0.2539) (q9 vf)) default 0.8805 [-0.0340,0.1647]\nb14 = max ((q7 0.0194) (q6 0.1480)) default 0.1947\nb15 = min ((q8 0.6864) (q12 0.6203)) default 0.2134\nb16 = min ((q2 0.3168) (q4 2*v4)) default 0.4705\nb17 = min ((q7 0.0755) (q6 0.5221)) default 0.6965\nb18 = * ((q7 0.6841) (q3 3*vt [-0.7118,0.7851])) default 0*vq\nb19 = max ((q8 0.3898) (q1 0.4625)) default 0.8286\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n  (define-fun q13 () Bool\n    true)\n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 728.0 625.0))\n  (define-fun b14_score () Real\n    (/ 37.0 250.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b3_score () Real\n    (/ 3452027.0 25000000.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q9 () Bool\n    true)\n  (define-fun b18_q3_U () Real\n    (- (/ 3559.0 5000.0)))\n  (define-fun b0_q14_U () Real\n    (/ 1451.0 5000.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun b8_score () Real\n    (/ 1309507.0 6250000.0))\n  (define-fun b2_score () Real\n    0.0)\n  (define-fun b12_score () Real\n    (/ 2543.0 10000.0))\n  (define-fun vf () Real\n    (/ 847.0 30000.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q12 () Bool\n    true)\n  (define-fun b2_q6_U () Real\n    (- (/ 127.0 400.0)))\n  (define-fun b18_score () Real\n    0.0)\n  (define-fun b11_score () Real\n    (/ 847.0 10000.0))\n  (define-fun b4_score () Real\n    (/ 9093.0 10000.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 198.0 625.0))\n  (define-fun b6_score () Real\n    0.0)\n  (define-fun q8 () Bool\n    false)\n  (define-fun b19_score () Real\n    (/ 4143.0 5000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    (/ 4029.0 10000.0))\n  (define-fun q10 () Bool\n    true)\n  (define-fun b7_score () Real\n    (/ 1723.0 5000.0))\n  (define-fun b15_score () Real\n    (/ 6203.0 10000.0))\n  (define-fun v4 () Real\n    (/ 99.0 625.0))\n  (define-fun b17_score () Real\n    (/ 5221.0 10000.0))\n  (define-fun b13_default_U () Real\n    (- (/ 17.0 500.0)))\n  (define-fun q5 () Bool\n    true)\n  (define-fun b10_score () Real\n    (/ 1989.0 2000.0))\n  (define-fun b13_score () Real\n    (/ 529.0 1875.0))\n  (define-fun b4_q11_U () Real\n    (/ 369.0 500.0))\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_false_analysis2 () Bool\n    true)\n  (define-fun b9_score () Real\n    (/ 6011.0 10000.0))\n  (define-fun b5_score () Real\n    0.0)\n  (define-fun vt () Real\n    (- (/ 1839.0 20000.0)))\n)"

    val result = new OutputVerifier(input).verifyModel(model, "analysis2")
    result._1 should be(PealTrue)
  }

  @Ignore("z3 has got problems")
  @Test
  def testFailedCase_8667664277616483421() {
    val input = "POLICIES\nb0 = + ((q13 0.7301) (q12 0.7087)) default 0.1692 [-0.2513,0.9973]\nb1 = min ((q5 0.0642) (q12 0.0753 [-0.7925,0.9061])) default 0.4771\nb2 = * ((q12 0.1458) (q4 0.9540)) default 0.0872\nb3 = min ((q1 0.8880) (q2 2*vz [-0.6447,0.1609])) default 0.3114\nb4 = max ((q12 0.7703) (q0 0.5619)) default 0.3823\nb5 = min ((q0 0.7813) (q14 0*vv)) default 0.6410\nb6 = * ((q11 0.4952) (q9 0.3759)) default 0.2439\nb7 = + ((q6 0.1301) (q3 0.7222)) default 0.7327\nb8 = + ((q9 3*v7) (q11 0.4404)) default 0.3150\nb9 = min ((q2 0*v0) (q11 3*vv)) default 0*vy\nb10 = * ((q14 0.3935) (q2 0.7636)) default 0.2704\nb11 = max ((q0 0.2312) (q6 0.3329)) default 0.1680 [-0.8552,0.9747]\nb12 = + ((q4 0.5895) (q12 0.6743 [-0.1729,0.6820])) default 0.3923 [-0.5205,0.3971]\nb13 = max ((q12 0.1854) (q0 0.9702 [-0.3427,0.6474])) default 2*vx\nb14 = min ((q13 0.1679 [-0.7471,0.2529]) (q4 2*vr)) default 0.4245\nb15 = * ((q2 0.2418) (q7 0*vs)) default 0.6600\nb16 = max ((q1 0.8433) (q14 0.3115)) default 0.0403\nb17 = * ((q13 0.5530) (q2 0.0229)) default 0.9581\nb18 = + ((q4 0.3261) (q8 0.9810)) default 0.1242\nb19 = max ((q8 0.5477) (q6 0.8907)) default 0.0507\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    false)\n  (define-fun b1_q12_U () Real\n    (/ 3167.0 5000.0))\n  (define-fun b12_q12_U () Real\n    (- (/ 1729.0 10000.0)))\n  (define-fun b13_q0_U () Real\n    (- (/ 3427.0 10000.0)))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b0_default_U () Real\n    (- (/ 2513.0 10000.0)))\n  (define-fun b14_q13_U () Real\n    (/ 2529.0 10000.0))\n  (define-fun b1_score () Real\n    (/ 7087.0 10000.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun q9 () Bool\n    false)\n  (define-fun b3_score () Real\n    (/ 111.0 125.0))\n  (define-fun b14_score () Real\n    (/ 849.0 2000.0))\n  (define-fun b12_default_U () Real\n    (- (/ 1041.0 2000.0)))\n  (define-fun q3 () Bool\n    true)\n  (define-fun b8_score () Real\n    (/ 63.0 200.0))\n  (define-fun b2_score () Real\n    (/ 729.0 5000.0))\n  (define-fun b12_score () Real\n    (/ 2507.0 5000.0))\n  (define-fun q12 () Bool\n    true)\n  (define-fun q6 () Bool\n    false)\n  (define-fun b3_q2_U () Real\n    (- (/ 6447.0 10000.0)))\n  (define-fun b18_score () Real\n    (/ 621.0 5000.0))\n  (define-fun b11_score () Real\n    (/ 1170743.0 4065000.0))\n  (define-fun b4_score () Real\n    (/ 7703.0 10000.0))\n  (define-fun q7 () Bool\n    true)\n  (define-fun b16_score () Real\n    (/ 8433.0 10000.0))\n  (define-fun b6_score () Real\n    (/ 2439.0 10000.0))\n  (define-fun q8 () Bool\n    false)\n  (define-fun b19_score () Real\n    (/ 507.0 10000.0))\n  (define-fun q11 () Bool\n    false)\n  (define-fun b0_score () Real\n    (/ 7087.0 10000.0))\n  (define-fun b15_score () Real\n    0.0)\n  (define-fun q14 () Bool\n    true)\n  (define-fun b7_score () Real\n    (/ 3611.0 5000.0))\n  (define-fun b17_score () Real\n    (/ 9581.0 10000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun b11_default_U () Real\n    (/ 487823.0 4065000.0))\n  (define-fun b10_score () Real\n    (/ 787.0 2000.0))\n  (define-fun b13_score () Real\n    (/ 927.0 5000.0))\n  (define-fun q2 () Bool\n    false)\n  (define-fun b9_score () Real\n    0.0)\n  (define-fun b5_score () Real\n    0.0)\n)"
    Z3ModelExtractor.extractIUsingRational(model)("analysis3")._2.foreach(t => println("(assert (= " + t._1 + " " + t._2.fold(l => l.z3Expression, r => r.toString.toLowerCase()) + "))"))

    val result = new OutputVerifier(input).verifyModel(model, "analysis3")
    result._1 should be(PealTrue)
  }

  @Test
  def testFailedCase_8667664277616483421_a1() {
    val input = "POLICIES\nb0 = + ((q13 0.7301) (q12 0.7087)) default 0.1692 [-0.2513,0.9973]\nb1 = min ((q5 0.0642) (q12 0.0753 [-0.7925,0.9061])) default 0.4771\nb2 = * ((q12 0.1458) (q4 0.9540)) default 0.0872\nb3 = min ((q1 0.8880) (q2 2*vz [-0.6447,0.1609])) default 0.3114\nb4 = max ((q12 0.7703) (q0 0.5619)) default 0.3823\nb5 = min ((q0 0.7813) (q14 0*vv)) default 0.6410\nb6 = * ((q11 0.4952) (q9 0.3759)) default 0.2439\nb7 = + ((q6 0.1301) (q3 0.7222)) default 0.7327\nb8 = + ((q9 3*v7) (q11 0.4404)) default 0.3150\nb9 = min ((q2 0*v0) (q11 3*vv)) default 0*vy\nb10 = * ((q14 0.3935) (q2 0.7636)) default 0.2704\nb11 = max ((q0 0.2312) (q6 0.3329)) default 0.1680 [-0.8552,0.9747]\nb12 = + ((q4 0.5895) (q12 0.6743 [-0.1729,0.6820])) default 0.3923 [-0.5205,0.3971]\nb13 = max ((q12 0.1854) (q0 0.9702 [-0.3427,0.6474])) default 2*vx\nb14 = min ((q13 0.1679 [-0.7471,0.2529]) (q4 2*vr)) default 0.4245\nb15 = * ((q2 0.2418) (q7 0*vs)) default 0.6600\nb16 = max ((q1 0.8433) (q14 0.3115)) default 0.0403\nb17 = * ((q13 0.5530) (q2 0.0229)) default 0.9581\nb18 = + ((q4 0.3261) (q8 0.9810)) default 0.1242\nb19 = max ((q8 0.5477) (q6 0.8907)) default 0.0507\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    false)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b1_q12_U () Real\n    (/ 141.0 2000.0))\n  (define-fun b12_q12_U () Real\n    (- (/ 1729.0 10000.0)))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b0_default_U () Real\n    (- (/ 2513.0 10000.0)))\n  (define-fun b13_q0_U () Real\n    (- (/ 3427.0 10000.0)))\n  (define-fun b14_q13_U () Real\n    (/ 2529.0 10000.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun q9 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 729.0 5000.0))\n  (define-fun b3_score () Real\n    (/ 111.0 125.0))\n  (define-fun b14_score () Real\n    (/ 849.0 2000.0))\n  (define-fun b12_default_U () Real\n    (- (/ 1041.0 2000.0)))\n  (define-fun q3 () Bool\n    false)\n  (define-fun b8_score () Real\n    (/ 63.0 200.0))\n  (define-fun b2_score () Real\n    (/ 729.0 5000.0))\n  (define-fun b12_score () Real\n    (/ 2507.0 5000.0))\n  (define-fun q12 () Bool\n    true)\n  (define-fun q6 () Bool\n    false)\n  (define-fun b3_q2_U () Real\n    (- (/ 6447.0 10000.0)))\n  (define-fun b18_score () Real\n    (/ 621.0 5000.0))\n  (define-fun b11_score () Real\n    0.0)\n  (define-fun b4_score () Real\n    (/ 7703.0 10000.0))\n  (define-fun q7 () Bool\n    true)\n  (define-fun b16_score () Real\n    (/ 8433.0 10000.0))\n  (define-fun b6_score () Real\n    (/ 2439.0 10000.0))\n  (define-fun q8 () Bool\n    false)\n  (define-fun b19_score () Real\n    (/ 507.0 10000.0))\n  (define-fun q11 () Bool\n    false)\n  (define-fun b0_score () Real\n    (/ 7087.0 10000.0))\n  (define-fun b15_score () Real\n    0.0)\n  (define-fun q14 () Bool\n    true)\n  (define-fun b7_score () Real\n    (/ 7327.0 10000.0))\n  (define-fun b17_score () Real\n    (/ 9581.0 10000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b11_default_U () Real\n    (- (/ 21.0 125.0)))\n  (define-fun b10_score () Real\n    (/ 787.0 2000.0))\n  (define-fun b13_score () Real\n    (/ 927.0 5000.0))\n  (define-fun q2 () Bool\n    false)\n  (define-fun b9_score () Real\n    0.0)\n  (define-fun b5_score () Real\n    0.0)\n)"
    Z3ModelExtractor.extractIUsingRational(model)("analysis1")._2.foreach(t => println("(assert (= " + t._1 + " " + t._2.fold(l => l.z3Expression, r => r.toString.toLowerCase()) + "))"))

    val result = new OutputVerifier(input).verifyModel(model, "analysis1")
    result._1 should be(PealTrue)
  }


  //Real broken case, fixed by removal of Double usage in Rational
  @Test
  def testFailedCase_7912490692998144642() {
    val input = "POLICIES\nb0 = * ((q0 0.8478) (q11 0.4938)) default 0.0386\nb1 = max ((q9 2*vj) (q5 0.9938)) default v3\nb2 = min ((q8 0.9463) (q0 0.8250)) default 2*v9\nb3 = max ((q11 0.5790) (q1 0.6485)) default 0.5733\nb4 = max ((q11 0.5899) (q5 0.0572)) default 2*vr [-0.5034,0.9492]\nb5 = + ((q2 0.9392) (q5 0.3920)) default 0.7358\nb6 = + ((q11 0.9706 [-0.2458,0.7521]) (q8 0.2161)) default 0.6726\nb7 = + ((q13 0.1415) (q7 0.9998)) default 0.1452\nb8 = * ((q3 0.9740) (q2 3*v4)) default 2*vj\nb9 = min ((q5 0*vn) (q3 0.0561)) default 0.9398\nb10 = * ((q13 0.1365) (q10 0.4044 [-0.1752,0.1397])) default 3*vj\nb11 = + ((q4 0.7950) (q1 0.0861)) default 0.7933\nb12 = + ((q6 0.8121) (q10 3*vs)) default 0*vw\nb13 = min ((q13 0*vl) (q6 0.5168)) default 0.2923\nb14 = * ((q7 0.6010) (q2 0.6501)) default 0.8051\nb15 = * ((q7 v9) (q2 0.9119)) default 0*v7\nb16 = max ((q14 0*v7) (q7 0.9404)) default 0.9137\nb17 = min ((q2 0.7213) (q6 0.9502)) default 0.9407\nb18 = max ((q1 2*vn) (q3 0.2168)) default 0.6278\nb19 = min ((q10 0.9773) (q1 3*vb)) default 0.6286\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 4969.0 5000.0))\n  (define-fun b14_score () Real\n    (/ 8051.0 10000.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b3_score () Real\n    (/ 1297.0 2000.0))\n  (define-fun vb () Real\n    (/ 3438931417.0 10743750000.0))\n  (define-fun b10_q10_U () Real\n    (- (/ 219.0 1250.0)))\n  (define-fun q1 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun b8_score () Real\n    (/ 155674.0 5371875.0))\n  (define-fun b2_score () Real\n    (/ 9463.0 10000.0))\n  (define-fun b12_score () Real\n    (/ 8121.0 10000.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun b18_score () Real\n    (- (/ 1290181417.0 3581250000.0)))\n  (define-fun b4_score () Real\n    (/ 5899.0 10000.0))\n  (define-fun b11_score () Real\n    (/ 8811.0 10000.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 9137.0 10000.0))\n  (define-fun b6_score () Real\n    (/ 9409.0 10000.0))\n  (define-fun q8 () Bool\n    true)\n  (define-fun b19_score () Real\n    (/ 3438931417.0 3581250000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    (/ 2469.0 5000.0))\n  (define-fun q10 () Bool\n    false)\n  (define-fun q14 () Bool\n    false)\n  (define-fun b7_score () Real\n    (/ 363.0 2500.0))\n  (define-fun b15_score () Real\n    0.0)\n  (define-fun vn () Real\n    (- (/ 1290181417.0 7162500000.0)))\n  (define-fun b17_score () Real\n    (/ 4751.0 5000.0))\n  (define-fun q5 () Bool\n    true)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun b10_score () Real\n    (/ 77837.0 1790625.0))\n  (define-fun b13_score () Real\n    (/ 323.0 625.0))\n  (define-fun b6_q11_U () Real\n    (- (/ 1229.0 5000.0)))\n  (define-fun vj () Real\n    (/ 77837.0 5371875.0))\n  (define-fun q2 () Bool\n    false)\n  (define-fun b9_score () Real\n    0.0)\n  (define-fun b5_score () Real\n    (/ 49.0 125.0))\n  (define-fun b4_default_U () Real\n    (- (/ 2517.0 5000.0)))\n)"
    //    Z3ModelExtractor.extractIUsingRational(model)("analysis3").foreach(t => println("(assert (= " + t._1 + " " + t._2.fold(l => l.z3Expression, r => r.toString.toLowerCase()) + "))"))

    val result = new OutputVerifier(input).verifyModel(model, "analysis3")
    result._1 should be(PealTrue)
  }

  @Test
  def testReallyFailedCase4() {
    val input = "POLICIES\nb0 = min ((q13 0.0959 [-0.8117,0.3539]) (q14 0.0100)) default 0.8931\nb1 = max ((q14 0.6691) (q5 0.2627)) default vc\nb2 = max ((q1 0.1218) (q10 0.9912)) default 0.2943\nb3 = min ((q4 2*vu) (q7 0.8416)) default 0.0809\nb4 = min ((q8 v4) (q1 0.9504)) default 0.8191\nb5 = max ((q6 0.0596) (q2 0.5933)) default 0.2468\nb6 = * ((q7 0.6472 [-0.6549,0.2911]) (q6 0.1032)) default 3*vs\nb7 = max ((q11 0.1087) (q6 2*vk)) default 0.1397\nb8 = + ((q9 0.6409 [-0.6325,0.0760]) (q14 0.9161)) default 0*v5 [-0.4938,0.1298]\nb9 = min ((q14 3*vf) (q11 3*vg)) default 0.0043\nb10 = + ((q3 vr) (q5 0.0691)) default 0.3101\nb11 = * ((q8 0.6665) (q4 0.9335)) default 0.9777\nb12 = + ((q12 0.4935) (q1 0.2251 [-0.4880,0.9101])) default 0.0341\nb13 = + ((q13 0.6415) (q3 0.3009 [-0.6209,0.7105])) default 2*vv\nb14 = + ((q12 0*vj) (q2 0.2686)) default 3*vs\nb15 = * ((q5 0.7777) (q1 0.0720)) default 0.5421\nb16 = * ((q11 0.6983) (q1 0.8932)) default 0.2004\nb17 = min ((q14 0.9654) (q4 0.1168)) default 0.2088\nb18 = * ((q10 0.6907) (q4 0*ve)) default 0.0428\nb19 = max ((q13 0*vd) (q1 0.3216)) default 3*vp [-0.5222,0.0831]\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis3 = different? cond1 cond2"
    val model = "Result of analysis [analysis3 = different? cond1 cond2]:\nsat\n(model \n  (define-fun q13 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 6691.0 10000.0))\n  (define-fun b8_q9_U () Real\n    (/ 19.0 250.0))\n  (define-fun b14_score () Real\n    (/ 4106961.0 100000000.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b3_score () Real\n    (/ 809.0 10000.0))\n  (define-fun vg () Real\n    (/ 3546457.0 8192500.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun q9 () Bool\n    true)\n  (define-fun b0_q13_U () Real\n    (- (/ 11683039.0 100000000.0)))\n  (define-fun q3 () Bool\n    true)\n  (define-fun b8_score () Real\n    (/ 1633.0 1000.0))\n  (define-fun b2_score () Real\n    (/ 609.0 5000.0))\n  (define-fun b12_score () Real\n    (/ 643.0 2000.0))\n  (define-fun v4 () Real\n    (/ 594.0 625.0))\n  (define-fun q6 () Bool\n    false)\n  (define-fun q12 () Bool\n    false)\n  (define-fun b8_default_U () Real\n    (- (/ 2469.0 5000.0)))\n  (define-fun b18_score () Real\n    (/ 107.0 2500.0))\n  (define-fun b11_score () Real\n    (/ 1333.0 2000.0))\n  (define-fun b4_score () Real\n    (/ 594.0 625.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 15593039.0 25000000.0))\n  (define-fun b6_score () Real\n    (/ 4106961.0 100000000.0))\n  (define-fun q8 () Bool\n    true)\n  (define-fun b13_q3_U () Real\n    (- (/ 6209.0 10000.0)))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b19_score () Real\n    (/ 201.0 625.0))\n  (define-fun q10 () Bool\n    false)\n  (define-fun q14 () Bool\n    true)\n  (define-fun b12_q1_U () Real\n    (/ 241.0 2500.0))\n  (define-fun b0_score () Real\n    (- (/ 2093039.0 100000000.0)))\n  (define-fun b7_score () Real\n    (/ 1087.0 10000.0))\n  (define-fun b19_default_U () Real\n    (- (/ 2611.0 5000.0)))\n  (define-fun b15_score () Real\n    (/ 9.0 125.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun b6_q7_U () Real\n    (- (/ 6549.0 10000.0)))\n  (define-fun b10_score () Real\n    (/ 1333.0 2000.0))\n  (define-fun b13_score () Real\n    (/ 643.0 2000.0))\n  (define-fun b17_score () Real\n    (/ 4827.0 5000.0))\n  (define-fun q2 () Bool\n    false)\n  (define-fun b9_score () Real\n    (/ 10639371.0 8192500.0))\n  (define-fun b5_score () Real\n    (/ 617.0 2500.0))\n  (define-fun vs () Real\n    (/ 1368987.0 100000000.0))\n  (define-fun vf () Real\n    (/ 145236909601.0 327700000000.0))\n  (define-fun vr () Real\n    (/ 1333.0 2000.0))\n)"
    //    Z3ModelExtractor.extractIUsingRational(model)("analysis2").foreach(println(_))

    val result = new OutputVerifier(input).verifyModel(model, "analysis3")
    //    println(input)
    //    println(model)
    result._1 should be(PealTrue)
  }

  //Use this to generate assert statements
  @Ignore("still fails")
  @Test
  def testReallyFailedCase3871405547660506467() {
    //    ConsolePrinter.enable()
    val input = "POLICIES\nb0 = min ((q14 0*vl) (q0 0.7850)) default 0.8919\nb1 = * ((q6 0.6819) (q7 0.7271)) default 0.5390\nb2 = * ((q12 0.3504) (q0 0.4032)) default 3*vp\nb3 = + ((q3 0.3078) (q7 0.1332)) default 0.7163\nb4 = max ((q3 0.0948 [-0.1435,0.4347]) (q11 0.1327)) default 0.8418\nb5 = * ((q3 0.3235) (q9 2*v0 [-0.3561,0.7747])) default 0*vy\nb6 = max ((q14 0.5613) (q4 0.6564)) default 0.6351\nb7 = + ((q8 vx) (q2 0.9709)) default 0.5696 [-0.8854,0.0560]\nb8 = * ((q14 0.7031) (q5 0.6469)) default 0.3753\nb9 = min ((q11 0.3598) (q7 0.4311)) default 0.0868\nb10 = * ((q10 0.1041) (q12 0.6119)) default 0.8983\nb11 = + ((q1 0.4650) (q4 0.6019)) default 0.3478\nb12 = min ((q3 3*vu [-0.2797,0.6717]) (q9 2*vu)) default 0.0223\nb13 = max ((q5 0.9802) (q2 0.9236)) default 0.8039\nb14 = min ((q6 2*vk) (q13 0.4516)) default 2*vl\nb15 = max ((q13 0.3230) (q12 0.9485)) default 0.6186\nb16 = + ((q1 0.8562 [-0.9047,0.6472]) (q11 3*vb)) default 0.6468\nb17 = + ((q4 2*vl) (q11 0.8956)) default 0.5290\nb18 = min ((q6 0.0261) (q4 0.8287)) default 0.9865\nb19 = max ((q0 0.0663) (q10 0.5418)) default 0.7368\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis2 = always_false? cond2"
    println(input)
    val model = "Result of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun q13 () Bool\n    true)\n  (define-fun b12_q3_U () Real\n    (- (/ 2797.0 10000.0)))\n  (define-fun b1_score () Real\n    (/ 49580949.0 100000000.0))\n  (define-fun b14_score () Real\n    (/ 1129.0 2500.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b16_q1_U () Real\n    (- (/ 4281.0 5000.0)))\n  (define-fun b3_score () Real\n    (/ 441.0 1000.0))\n  (define-fun v0 () Real\n    0.0)\n  (define-fun q9 () Bool\n    true)\n  (define-fun q1 () Bool\n    true)\n  (define-fun vl () Real\n    0.0)\n  (define-fun vb () Real\n    (/ 867987391.0 3906250000.0))\n  (define-fun vk () Real\n    (/ 1129.0 5000.0))\n  (define-fun b7_default_U () Real\n    (- (/ 4427.0 5000.0)))\n  (define-fun q3 () Bool\n    true)\n  (define-fun b8_score () Real\n    (/ 7031.0 10000.0))\n  (define-fun b2_score () Real\n    (/ 55188.0 390625.0))\n  (define-fun b4_q3_U () Real\n    (/ 379.0 10000.0))\n  (define-fun b12_score () Real\n    (/ 2797.0 5000.0))\n  (define-fun q12 () Bool\n    true)\n  (define-fun q6 () Bool\n    true)\n  (define-fun b18_score () Real\n    (/ 261.0 10000.0))\n  (define-fun b11_score () Real\n    (/ 10669.0 10000.0))\n  (define-fun b4_score () Real\n    (/ 1327.0 10000.0))\n  (define-fun q7 () Bool\n    true)\n  (define-fun b16_score () Real\n    (/ 2603962173.0 3906250000.0))\n  (define-fun b6_score () Real\n    (/ 1641.0 2500.0))\n  (define-fun q8 () Bool\n    true)\n  (define-fun b19_score () Real\n    (/ 2709.0 5000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    0.0)\n  (define-fun q10 () Bool\n    true)\n  (define-fun q14 () Bool\n    true)\n  (define-fun b7_score () Real\n    (/ 1324675029.0 2810937500.0))\n  (define-fun b15_score () Real\n    (/ 1897.0 2000.0))\n  (define-fun b17_score () Real\n    (/ 2239.0 2500.0))\n  (define-fun vx () Real\n    (- (/ 5617856759.0 11243750000.0)))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b10_score () Real\n    (/ 6369879.0 100000000.0))\n  (define-fun b13_score () Real\n    (/ 2309.0 2500.0))\n  (define-fun vu () Real\n    (/ 2797.0 10000.0))\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_false_analysis2 () Bool\n    true)\n  (define-fun b9_score () Real\n    (/ 1799.0 5000.0))\n  (define-fun b5_q9_U () Real\n    (- (/ 3561.0 10000.0)))\n  (define-fun b5_score () Real\n    (- (/ 2303967.0 20000000.0)))\n)"
    Z3ModelExtractor.extractIUsingRational(model)("analysis2")._2.foreach(t => println("(assert (= " + t._1 + " " + t._2.fold(l => l.z3Expression, r => r.toString.toLowerCase()) + "))"))

    val result = new OutputVerifier(input).verifyModel(model, "analysis2")
    result._1 should be(PealTrue)
  }
}
