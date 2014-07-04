package peal.explicit

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class ExplicitAnalyserTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutputAnalysis() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    val out = new ExplicitAnalyser(input).analyse(model, "name1")
    out should be ("b1 = default 1")
  }
}
