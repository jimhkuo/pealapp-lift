package peal.explicit

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class ExplicitAnalyserTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutputAnalysis() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = cond1 && cond2\ncond4 = cond1 || cond2\ncond5 = !cond4\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond3 () Bool\n    false)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun cond5 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun a () Real\n    0.0)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
  }
}
