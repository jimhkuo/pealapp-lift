package peal.eagersynthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.util.Z3ModelMatcher

class EagerSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Test
  def testCanDealWithDefaultInput() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1\nname3 = satisfiable? cond2\nname4 = equivalent? cond1 cond2\nname5 = different? cond1 cond2"
    val output = new EagerSynthesiser(input).generate()
    output should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q3 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond3 Bool)\n(declare-const cond4 Bool)\n(declare-const cond1 Bool)\n(assert (= cond1 (and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))))\n(assert (= cond2 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond3 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond4 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) (and q6 q5 q4)))))\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name2 = always_false? cond1]:\")\n(push)\n(declare-const always_false_name2 Bool)\n(assert (= always_false_name2 cond1))\n(assert always_false_name2)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name3 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond2))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name4 = equivalent? cond1 cond2]:\")\n(push)\n(declare-const equivalent_name4 Bool)\n(assert (= equivalent_name4 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert equivalent_name4)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name5 = different? cond1 cond2]:\")\n(push)\n(declare-const different_name5 Bool)\n(assert (= different_name5 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert different_name5)\n(check-sat)\n(get-model)\n(pop)")
  }

}
