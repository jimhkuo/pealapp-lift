package peal.analyser

import org.junit.{Ignore, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.util.ConsoleLogger
import peal.verifier.explicit.ExplicitOutputVerifier
import peal.verifier.extended.ExtendedOutputVerifier


class InputAnalyserTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutputAnalysisAllRulesFalse() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    implicit val verifier = new ExplicitOutputVerifier(input)

    ConsoleLogger.log2(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    ConsoleLogger.log2(out)
    out should be ("uses cond1\nb1 = min () default 1.0")
  }

  @Test
  def testCanOutputAnalysisOneRuleTrueOneFalseMin() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = min ((q1 0.6) (q2 0.4)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    implicit val verifier = new ExplicitOutputVerifier(input)
    ConsoleLogger.log2(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    ConsoleLogger.log2(out)
    out should be ("uses cond1\nb1 = min (([q1] 0.6)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisOneRuleTrueOneBottomMax() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = max ((q1 0.8) (q2 0.8)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.65\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    implicit val verifier = new ExplicitOutputVerifier(input)
    ConsoleLogger.log2(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    out should be ("uses cond1\nb1 = max (([q1] 0.8) (q2? 0.8)) default 0.0")
  }

  @Test
  def testCanOutputAnalysisBothRulesTruePlus() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = + ((q1 0.5) (q2 0.1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    implicit val verifier = new ExplicitOutputVerifier(input)
    ConsoleLogger.log2(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    out should be ("uses cond1\nb1 = + (([q1 q2] 0.6)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisBothRulesTrueMul() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 0.8) (q2 0.8)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.65 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    implicit val verifier = new ExplicitOutputVerifier(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    out should be ("uses cond1\nb1 = * (([q1 q2] 0.64)) default 0.0")
  }

  @Test
  def testCanOutputAnalysisOneRuleNotDefinedMul() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    implicit val verifier = new ExplicitOutputVerifier(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    ConsoleLogger.log2(out)
    out should be ("uses cond1\nb1 = * (([q1] 1.0) (q2? 1.0)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisAlwaysFalse() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = always_false? cond1"
    val model = "Result of analysis [name1 = always_false? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun always_false_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    implicit val verifier = new ExplicitOutputVerifier(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    out should be ("uses cond1\nb1 = * () default 0.5")
  }

  @Test
  def testCanOutputAnalysisSatisfiable() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = satisfiable? cond1"
    val model = "Result of analysis [name1 = satisfiable? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun satisfiable_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n)"
    implicit val verifier = new ExplicitOutputVerifier(input)
    ConsoleLogger.log2(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    out should be ("uses cond1\nb1 = * () default 0.5")
  }

  @Test
  def testCanOutputAnalysisDifferent() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nb2 = * ((q3 1) (q4 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.9\ncond2 = pSet2 <= 0.8\nANALYSES\nname1 = different? cond1 cond2"
    val model = "Result of analysis [name1 = different? cond1 cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun different_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n)"
    implicit val verifier = new ExplicitOutputVerifier(input)
    ConsoleLogger.log2(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    out should be ("uses cond1, cond2\nb1 = * () default 0.5\nb2 = * (([q3] 1.0) (q4? 1.0)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisImplies() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nb2 = * ((q3 1) (q4 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.9\ncond2 = pSet2 <= 0.8\nANALYSES\nname1 = implies? cond1 cond2"
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun implies_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    implicit val verifier = new ExplicitOutputVerifier(input)
    val out = new InputAnalyser(input).analyse(model, "name1")
    out should be ("uses cond1, cond2\nb1 = * () default 0.5\nb2 = * (([q3] 1.0) (q4? 1.0)) default 0.5")
  }

  @Test
  def testCanAnalyseExtended() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb0 = * ((q5 0.9880)) default 0.6085\nb1 = min ((q3 0.6166) (q4 0.9412) (q5 0.2340) (q0 2*vb)) default 0.1661\nb2 = max ((q5 0.1117) (q3 0.1726) (q4 3*vi [-0.9282,0.0551])) default 0.2029\nb3 = + ((q2 0.8261) (q1 0.3104)) default 2*vt\nb4 = min ((q0 0.8647) (q5 2*v5) (q2 0.0736) (q4 0.1695)) default 0.9265\nb5 = max ((q2 0.1412) (q0 0.2796) (q4 0.3937)) default 2*vs\nb6 = + ((q2 0.2751 [-0.6356,0.6508]) (q3 3*v7 [-0.5388,0.2106])) default 0.2814 [-0.1182,0.4017]\nb7 = * ((q2 0.5470)) default 0.6631\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = +(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nANALYSES\nanalysis1 = always_true? cond1"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun b2_q4_U () Real\n    0.0)\n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 117.0 500.0))\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b6_score () Real\n    (/ 46.0 625.0))\n  (define-fun vi () Real\n    (/ 863.0 15000.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b0_score () Real\n    (/ 247.0 250.0))\n  (define-fun b3_score () Real\n    (/ 8261.0 10000.0))\n  (define-fun b7_score () Real\n    (/ 547.0 1000.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun vb () Real\n    (/ 117.0 1000.0))\n  (define-fun q5 () Bool\n    true)\n  (define-fun b6_q2_U () Real\n    (- (/ 2751.0 10000.0)))\n  (define-fun q3 () Bool\n    true)\n  (define-fun b6_q3_U () Real\n    0.0)\n  (define-fun q2 () Bool\n    true)\n  (define-fun b5_score () Real\n    (/ 3937.0 10000.0))\n  (define-fun b6_default_U () Real\n    0.0)\n  (define-fun b2_score () Real\n    (/ 863.0 5000.0))\n  (define-fun v7 () Real\n    (/ 46.0 1875.0))\n  (define-fun b4_score () Real\n    (/ 46.0 625.0))\n  (define-fun v5 () Real\n    (/ 23.0 625.0))\n)"
    implicit val verifier = new ExtendedOutputVerifier(input)

    ConsoleLogger.log2(input)
    val out = new InputAnalyser(input).analyse(model, "analysis1")
    ConsoleLogger.log1(out)
    out should be ("uses cond1\nb5 = max (([q2 q0 q4] 0.3937)) default 0\nb6 = + (([q2 q3] 0.0736)) default 0.2814\nb4 = min (([q0 q5 q2 q4] 0.0736)) default 0.9265\nb0 = * (([q5] 0.988)) default 0.6085\nb2 = max (([q5 q3 q4] 0.1726)) default 0.2029\nb3 = + (([q2] 0.8261)) default 0\nb7 = * (([q2] 0.547)) default 0.6631\nb1 = min (([q3 q4 q5 q0] 0.234)) default 0.1661")
  }
}
