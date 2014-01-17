package peal.verify

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.synthesis.EagerSynthesiser
import peal.z3.Z3Caller
import peal.domain.PealTrue


class ExplicitOutputVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testCanVerifyPlusAndAlwaysTrueGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1") should be (PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueMaxGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1,b2)\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1"
//    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1") should be (PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueMinGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = min(b1,b2)\nCONDITIONS\ncond1 = 0.7 < pSet1\nANALYSES\nname1 = always_true? cond1"
//    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1") should be (PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueLessThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.7\nANALYSES\nname1 = always_true? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1") should be (PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueAndConditions() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.5)) default 0.1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.7\ncond2 = 0 < pSet2\ncond3 = cond1 && cond2\nANALYSES\nname3 = always_true? cond3"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name3") should be (PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueOrConditions() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.5)) default 0.1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.7\ncond2 = 0.1 < pSet2\ncond3 = cond1 || cond2\nANALYSES\nname3 = always_true? cond3"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name3") should be (PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysFalse() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1") should be (PealTrue)
  }

  @Test
  def testCanDealWithTwoAnalyses() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_false? cond1\nname2 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1") should be (PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2") should be (PealTrue)
  }

  @Test
  def testCanVerifyMin() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1") should be (PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2") should be (PealTrue)
  }
}
