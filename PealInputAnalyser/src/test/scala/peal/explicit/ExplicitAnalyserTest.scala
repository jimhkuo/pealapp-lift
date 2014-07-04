package peal.explicit

import org.junit.{Ignore, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.util.ConsoleLogger


class ExplicitAnalyserTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutputAnalysisAllRulesFalse() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = default 1.0")
  }

  @Test
  def testCanOutputAnalysisOneRuleTrue() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = min ((q1 0.6) (q2 0.4)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = min ([q1] 0.6)")
  }

  @Test
  def testCanOutputAnalysisBothRulesTruePlus() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = + ((q1 0.5) (q2 0.1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = + ([q1 q2] 0.6)")
  }

  @Test
  def testCanOutputAnalysisBothRulesTrueMul() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 0.8) (q2 0.8)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.65 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = * ([q1 q2] 0.64)")
  }

  @Test
  def testCanOutputAnalysisOneRuleNotDefinedMul() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = + ([q1 q2] 0.6)")
  }

  @Ignore("not done")
  @Test
  def testCanOutputAnalysis2() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\nANALYSES\nname2 = equivalent? cond1 cond2"
    val model = "Result of analysis [name2 = equivalent? cond1 cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun q5 () Bool\n    false)\n  (define-fun equivalent_name2 () Bool\n    true)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q6 () Bool\n    false)\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n)"
    val out = new ExplicitAnalyser(input).analyse(model, "name2")

  }
}
