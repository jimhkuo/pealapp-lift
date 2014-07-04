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
  def testCanOutputAnalysisOneRuleTrueOneFalseMin() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = min ((q1 0.6) (q2 0.4)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = min (([q1] 0.6)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisOneRuleTrueOneBottomMax() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = max ((q1 0.8) (q2 0.8)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.65\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = max (([q1] 0.8) (q2? 0.8)) default 0.0")
  }

  @Test
  def testCanOutputAnalysisBothRulesTruePlus() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = + ((q1 0.5) (q2 0.1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = + (([q1 q2] 0.6)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisBothRulesTrueMul() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 0.8) (q2 0.8)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.65 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = * (([q1 q2] 0.64)) default 0.0")
  }

  @Test
  def testCanOutputAnalysisOneRuleNotDefinedMul() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = * (([q1] 1.0) (q2? 1.0)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisAlwaysFalse() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = always_false? cond1"
    val model = "Result of analysis [name1 = always_false? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun always_false_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = default 0.5")
  }
}
