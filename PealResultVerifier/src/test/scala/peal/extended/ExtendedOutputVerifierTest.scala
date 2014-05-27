package peal.extended

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}


class ExtendedOutputVerifierTest extends ShouldMatchersForJUnit {

  @Ignore("wip")
  @Test
  def testSampleCase() {
    val input = "POLICIES\nb1 = min ((q1 0.2 [-0.2,0.1]) (q2 0.4) (q3 0.9)) default 0.8*z [-0.05,0.4]\nb2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y [-0.2,0.05])) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const a Real)\n(declare-const b Real)\n(assert (= q1 (< a (+ b 1))))\nANALYSES\nname1 = satisfiable? cond1\nname2 = satisfiable? cond2"
    val model = "Result of analysis [name1 = satisfiable? cond1]:\nsat\n(model \n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun cond3 () Bool\n    false)\n  (define-fun y () Real\n    0.0)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun satisfiable_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun b1_default_U () Real\n    0.0)\n  (define-fun q5 () Bool\n    true)\n  (define-fun b () Real\n    (- 1.0))\n  (define-fun cond4 () Bool\n    true)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun b1_q1_U () Real\n    0.0)\n  (define-fun z () Real\n    (/ 5.0 8.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun q6 () Bool\n    true)\n  (define-fun b2_q6_U () Real\n    0.0)\n  (define-fun x () Real\n    5.0)\n)"
    new ExtendedOutputVerifier(input).verifyModel(model, "name1")
  }
}
