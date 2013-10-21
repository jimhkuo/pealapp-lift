package peal.newsynthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import peal.util.Z3ModelMatcher
import org.junit.{Ignore, Test}

class NewSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  @Ignore("wip")
  @Test
  def testExample4() {
    val input = "POLICIES\nb1 = max ((q1 0.1) (q2 0.2) (q3 0.4)) default 0.1\nb2 = min ((q1 0.5) (q2 0.6) (q3 0.7)) default 0.2\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond = pSet1 < pSet2\nANALYSES\nname1 = always_true? cond"
    val expected = "(declare-const q1 Bool)\n" +
      "(declare-const q2 Bool)\n" +
      "(declare-const q3 Bool)\n" +
      "(declare-const pSet1_score Real)\n" +
      "(declare-const pSet2_score Real)\n" +
      "(declare-const b1_score Real)\n" +
      "(declare-const b2_score Real)\n" +
      "(assert (= pSet1_score b1_score))\n" +
      "(assert (= pSet2_score b2_score))\n" +
      "(assert (implies q1 (<= 0.1 b1_score)))\n" +
      "(assert (implies q2 (<= 0.2 b1_score)))\n" +
      "(assert (implies q3 (<= 0.4 b1_score)))\n" +
      //      ";(assert (implies (not (or q1 q2 q3)) (= b1_score 0.1)))\n" +
      "(assert (or (and (not (or q1 q2 q3)) (= b1_score 0.1)) (and q1 (= b1_score 0.1)) (and q2 (= b1_score 0.2)) (and q3 (= b1_score 0.4))))\n" +
      "(assert (implies q1 (<= b2_score 0.5)))\n" +
      "(assert (implies q2 (<= b2_score 0.6)))\n" +
      "(assert (implies q3 (<= b2_score 0.7)))\n" +
      //      ";(assert (implies (not (or q1 q2 q3)) (= b2_score 0.2)))\n" +
      "(assert (or (and (not (or q1 q2 q3)) (= b2_score 0.2)) (and q1 (= b2_score 0.5)) (and q2 (= b2_score 0.6)) (and q3 (= b2_score 0.7))))\n" +
      "(push)\n" +
      "(declare-const always_true_name1 Bool)\n" +
      "(assert (= always_true_name1 (< pSet1_score pSet2_score)))\n" +
      "(assert (not always_true_name1))\n" +
      "(check-sat)\n" +
      "(get-model)\n" +
      "(pop)"

    new NewSynthesiser(input).generate() should beZ3Model(expected)
  }

}
