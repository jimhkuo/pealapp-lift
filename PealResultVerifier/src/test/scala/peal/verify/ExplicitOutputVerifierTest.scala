package peal.verify

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.synthesis.EagerSynthesiser
import peal.z3.Z3Caller
import peal.domain.{PealBottom, PealTrue}


class ExplicitOutputVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testBrokenModelBottom() {
    val input = "POLICIES\nb0 = min () default 0.4942\nb1 = + ((q3 0.2134)) default 0.6567\nPOLICY_SETS\np0_1 = min(b0,b1)\nCONDITIONS\ncond1 = 0.50 < p0_1\nANALYSES\nanalysis1 = always_true? cond1"
    //    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n)"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun always_true_analysis1 () Bool\n    false)\n)"
    new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueMaxGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1,b2)\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1"
    //    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueMinGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = min(b1,b2)\nCONDITIONS\ncond1 = 0.7 < pSet1\nANALYSES\nname1 = always_true? cond1"
    //    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueLessThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.7\nANALYSES\nname1 = always_true? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueAndConditions() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.5)) default 0.1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.7\ncond2 = 0 < pSet2\ncond3 = cond1 && cond2\nANALYSES\nname3 = always_true? cond3"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name3")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueOrConditions() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.5)) default 0.1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.7\ncond2 = 0.1 < pSet2\ncond3 = cond1 || cond2\nANALYSES\nname3 = always_true? cond3"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name3")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysFalse() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanDealWithTwoAnalyses() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_false? cond1\nname2 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyMin() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyMax() {
    val input = "POLICIES\nb1 = max ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyMul() {
    val input = "POLICIES\nb1 = * ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test(expected = classOf[RuntimeException])
  def testCanDealWithUnsatCase() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.2\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.1 < pSet1\nANALYSES\nname1 = always_true? cond1"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test(expected = classOf[RuntimeException])
  def testCanDealWithUnsatCase2() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = cond1 && cond2\nANALYSES\nname1 = always_false? cond3"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testBroken() {
    val input = "POLICIES\nb0 = * ((q0 0.9416) (q5 0.2881)) default 0.7321\nb1 = max ((q1 0.953) (q6 0.8343) (q2 0.8586) (q3 0.3076)) default 0.7995\nb2 = min ((q3 0.1408)) default 0.5171\nb3 = + ((q2 0.9152) (q4 0.9161) (q6 0.521)) default 0.9626\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")._1 should be(PealTrue)
  }

  @Test
  def testSingleStageProducesInconclusive() {
    val input = "POLICIES\nb16 = min ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = max ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\nANALYSES\nanalysis1 = always_true? cond1"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testDifferent() {
    val input = "POLICIES\nb16 = max ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = + ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\ncond2 = p16_17 <= 0.5\nANALYSES\nanalysis1 = different? cond1 cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testEquivalent() {
    val input = "POLICIES\nb16 = max ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = + ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\ncond2 = p16_17 <= 0.5\nANALYSES\nanalysis1 = equivalent? cond1 cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testImplies() {
    val input = "POLICIES\nb16 = max ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = + ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\ncond2 = p16_17 <= 0.5\nANALYSES\nanalysis1 = implies? cond1 cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testSatisfiable() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = satisfiable? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }
}
