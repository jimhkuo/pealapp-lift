package peal.extended

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}


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
    println(input)
    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Test
  def testPredicateAndAndCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = q2\ncond3 = cond1 && cond2\nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Test
  def testPredicateAndOrCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = q2\ncond3 = cond1 || cond2\nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Test
  def testPredicateAndNotCondition() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = q1\ncond2 = !cond1\nANALYSES\nname1 = always_true? cond2"
    val model = "Result of analysis [name1 = always_true? cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 1.0 5.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n)"
    println(input)
    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Ignore("wip")
  @Test
  def testMaxAndMinPSet() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = q2\n" +
      "ANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 9.0 10.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Ignore("wip")
  @Test
  def testOpX() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 9.0 10.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
//    println(input)
//    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Ignore("wip")
  @Test
  def testEvaluateFormulaVariablePlusNumber() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + 1\n" +
      "POLICY_SETS\npSet1 = b1\n" +
      "CONDITIONS\ncond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 3.0 2.0))\n  (define-fun x () Real\n    (/ 1.0 4.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
//    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Ignore("wip")
  @Test
  def testEvaluateFormulaVariablePlusVariable() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + y\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 3.0 2.0))\n  (define-fun y () Real\n    (/ 3.0 2.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun x () Real\n    0.0)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
//    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }

  @Ignore("wip")
  @Test
  def testEvaluateFormulaSomePredicatesAreBottom() {
    //Needs resetting bottom to false iteration logic
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.1)) default 2*x + y\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.5 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 1.0 10.0))\n  (define-fun q3 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    println(input)
//    println(model)
    println(new ExtendedOutputVerifier(input).verifyModel(model, "name1"))
  }
}
