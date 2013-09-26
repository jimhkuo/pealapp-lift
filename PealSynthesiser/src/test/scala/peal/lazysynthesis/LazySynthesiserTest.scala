package peal.lazysynthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.util.Z3ModelMatcher

class LazySynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Test
  def testGenerate() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q4 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b3 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b4 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = 0.6 < pSet2\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    generator.generate() should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(declare-const b2_score_q4 Real)\n(assert (implies q4 (= 0.1 b2_score_q4)))\n(assert (implies (not (= 0.0 b2_score_q4)) q4))\n(declare-const b2_score_q5 Real)\n(assert (implies q5 (= 0.2 b2_score_q5)))\n(assert (implies (not (= 0.0 b2_score_q5)) q5))\n(declare-const b2_score_q6 Real)\n(assert (implies q6 (= 0.2 b2_score_q6)))\n(assert (implies (not (= 0.0 b2_score_q6)) q6))\n(declare-const cond2_b1 Bool)\n(assert (= cond2_b1 (or (and (< 0.6 1.0) (not (or q1 q2 q4)))\n(and (or q1 q2 q4) (not (or q1 q2))))))\n(declare-const cond2_b2 Bool)\n(assert (= cond2_b2 (or (and (< 0.6 0.0) (not (or q4 q5 q6)))  (and (or q4 q5 q6)  (< 0.6 (+ b2_score_q4 b2_score_q5 b2_score_q6))))))\n(assert (= cond2 (and cond2_b1 cond2_b2)))\n(declare-const cond1_b1 Bool)\n(assert (= cond1_b1 (or (and (<= 1.0 0.5) (not (or q1 q2 q4)))\n(or q1 q2))))\n(declare-const cond1_b2 Bool)\n(assert (= cond1_b2 (or (and (<=  0.0 0.5) (not (or q4 q5 q6)))  (and (or q4 q5 q6)  (<=  (+ b2_score_q4 b2_score_q5 b2_score_q6) 0.5)))))\n(assert (= cond1 (and cond1_b1 cond1_b2)))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testGenerateNotCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q4 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b3 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b4 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = !cond1\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    generator.generate() should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(declare-const b2_score_q4 Real)\n(assert (implies q4 (= 0.1 b2_score_q4)))\n(assert (implies (not (= 0.0 b2_score_q4)) q4))\n(declare-const b2_score_q5 Real)\n(assert (implies q5 (= 0.2 b2_score_q5)))\n(assert (implies (not (= 0.0 b2_score_q5)) q5))\n(declare-const b2_score_q6 Real)\n(assert (implies q6 (= 0.2 b2_score_q6)))\n(assert (implies (not (= 0.0 b2_score_q6)) q6))\n(assert (= cond2 (not cond1)))\n(declare-const cond1_b1 Bool)\n(assert (= cond1_b1 (or (and (<= 1.0 0.5) (not (or q1 q2 q4)))\n(or q1 q2))))\n(declare-const cond1_b2 Bool)\n(assert (= cond1_b2 (or (and (<=  0.0 0.5) (not (or q4 q5 q6)))  (and (or q4 q5 q6)  (<=  (+ b2_score_q4 b2_score_q5 b2_score_q6) 0.5)))))\n(assert (= cond1 (and cond1_b1 cond1_b2)))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testGenerateAndCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q4 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b3 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b4 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = cond1 && cond1\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    generator.generate() should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(declare-const b2_score_q4 Real)\n(assert (implies q4 (= 0.1 b2_score_q4)))\n(assert (implies (not (= 0.0 b2_score_q4)) q4))\n(declare-const b2_score_q5 Real)\n(assert (implies q5 (= 0.2 b2_score_q5)))\n(assert (implies (not (= 0.0 b2_score_q5)) q5))\n(declare-const b2_score_q6 Real)\n(assert (implies q6 (= 0.2 b2_score_q6)))\n(assert (implies (not (= 0.0 b2_score_q6)) q6))\n(assert (= cond2 (and cond1 cond1)))\n(declare-const cond1_b1 Bool)\n(assert (= cond1_b1 (or (and (<= 1.0 0.5) (not (or q1 q2 q4)))\n(or q1 q2))))\n(declare-const cond1_b2 Bool)\n(assert (= cond1_b2 (or (and (<=  0.0 0.5) (not (or q4 q5 q6)))  (and (or q4 q5 q6)  (<=  (+ b2_score_q4 b2_score_q5 b2_score_q6) 0.5)))))\n(assert (= cond1 (and cond1_b1 cond1_b2)))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testGenerateOrCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q4 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b3 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b4 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = cond1 || cond1\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    generator.generate() should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(declare-const b2_score_q4 Real)\n(assert (implies q4 (= 0.1 b2_score_q4)))\n(assert (implies (not (= 0.0 b2_score_q4)) q4))\n(declare-const b2_score_q5 Real)\n(assert (implies q5 (= 0.2 b2_score_q5)))\n(assert (implies (not (= 0.0 b2_score_q5)) q5))\n(declare-const b2_score_q6 Real)\n(assert (implies q6 (= 0.2 b2_score_q6)))\n(assert (implies (not (= 0.0 b2_score_q6)) q6))\n(assert (= cond2 (or cond1 cond1)))\n(declare-const cond1_b1 Bool)\n(assert (= cond1_b1 (or (and (<= 1.0 0.5) (not (or q1 q2 q4)))\n(or q1 q2))))\n(declare-const cond1_b2 Bool)\n(assert (= cond1_b2 (or (and (<=  0.0 0.5) (not (or q4 q5 q6)))  (and (or q4 q5 q6)  (<=  (+ b2_score_q4 b2_score_q5 b2_score_q6) 0.5)))))\n(assert (= cond1 (and cond1_b1 cond1_b2)))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testGenerateTruthCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q4 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b3 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b4 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = false\n" +
      "cond2 = true\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    generator.generate() should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(assert (= cond2 true))\n(assert (= cond1 false))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testBroken() {
    val input = "POLICIES\nb0 = * ((q4 0.17)) default 0.95\nb1 = max ((q1 0.86)) default 0.51\nb2 = + ((q4 0.86)) default 0.99\nb3 = min ((q4 0.66) (q1 0.79)) default 0.83\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis1 = always_true? cond1"
    val generator = new LazySynthesiser(input)
    generator.generate() should beZ3Model("(declare-const q4 Bool)\n(declare-const q1 Bool)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(declare-const b0_score_q4 Real)\n(assert (implies q4 (= 0.17 b0_score_q4)))\n(assert (implies (not (= 1.0 b0_score_q4)) q4))\n(declare-const b2_score_q4 Real)\n(assert (implies q4 (= 0.86 b2_score_q4)))\n(assert (implies (not (= 0.0 b2_score_q4)) q4))\n(declare-const cond2_b0 Bool)\n(assert (= cond2_b0 (or (and (< 0.6 0.95) (not (or q4)))  (and (or q4)  (< 0.6 (* b0_score_q4))))))\n(declare-const cond2_b1 Bool)\n(assert (= cond2_b1 (or (and (< 0.6 0.51) (not (or q1)))\n(or q1))))\n(declare-const cond2_b2 Bool)\n(assert (= cond2_b2 (or (and (< 0.6 0.99) (not (or q4)))  (and (or q4)  (< 0.6 (+ b2_score_q4))))))\n(declare-const cond2_b3 Bool)\n(assert (= cond2_b3 (or (and (< 0.6 0.83) (not (or q4 q1)))\n(and (or q4 q1) (not false)))))\n(assert (= cond2 (or (and cond2_b0 cond2_b1) (and cond2_b2 cond2_b3))))\n(declare-const cond1_b0 Bool)\n(assert (= cond1_b0 (or (and (< 0.5 0.95) (not (or q4)))  (and (or q4)  (< 0.5 (* b0_score_q4))))))\n(declare-const cond1_b1 Bool)\n(assert (= cond1_b1 (or (and (< 0.5 0.51) (not (or q1)))\n(or q1))))\n(declare-const cond1_b2 Bool)\n(assert (= cond1_b2 (or (and (< 0.5 0.99) (not (or q4)))  (and (or q4)  (< 0.5 (+ b2_score_q4))))))\n(declare-const cond1_b3 Bool)\n(assert (= cond1_b3 (or (and (< 0.5 0.83) (not (or q4 q1)))\n(and (or q4 q1) (not false)))))\n(assert (= cond1 (or (and cond1_b0 cond1_b1) (and cond1_b2 cond1_b3))))\n\n(echo \"Result of analysis [analysis1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_analysis1 Bool)\n(assert (= always_true_analysis1 cond1))\n(assert (not always_true_analysis1))\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testGenerateWithNonConstant() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 x) (q2 y) (q4 y)) default 1\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    val expected =
      "(declare-const q1 Bool)\n" +
        "(declare-const q2 Bool)\n" +
        "(declare-const q4 Bool)\n" +
        "(declare-const x Real)\n" +
        "(declare-const y Real)\n" +
        "(declare-const cond1 Bool)\n" +
        "(declare-const b1_score_q1 Real)\n" +
        "(assert (implies q1 (= x b1_score_q1)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q1)) q1))\n" +
        "(declare-const b1_score_q2 Real)\n" +
        "(assert (implies q2 (= y b1_score_q2)))\n(assert (implies (not (= 0.0 b1_score_q2)) q2))\n" +
        "(declare-const b1_score_q4 Real)\n(assert (implies q4 (= y b1_score_q4)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q4)) q4))\n(declare-const cond1_b1 Bool)\n" +
        "(assert (= cond1_b1 (or (and (<=  1.0 0.5) (not (or q1 q2 q4)))  (and (or q1 q2 q4)  (<=  (+ b1_score_q1 b1_score_q2 b1_score_q4) 0.5)))))\n(assert (= cond1 cond1_b1))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)"
    generator.generate() should beZ3Model(expected)
  }

  @Test
  def testGenerateWithNonConstantWithMultipliers() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 x) (q2 2 *y) (q4 y*4)) default 1\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    val expected =
      "(declare-const q1 Bool)\n" +
        "(declare-const q2 Bool)\n" +
        "(declare-const q4 Bool)\n" +
        "(declare-const x Real)\n" +
        "(declare-const y Real)\n" +
        "(declare-const cond1 Bool)\n" +
        "(declare-const b1_score_q1 Real)\n" +
        "(assert (implies q1 (= x b1_score_q1)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q1)) q1))\n" +
        "(declare-const b1_score_q2 Real)\n" +
        "(assert (implies q2 (= (* 2.0 y) b1_score_q2)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q2)) q2))\n" +
        "(declare-const b1_score_q4 Real)\n" +
        "(assert (implies q4 (= (* 4.0 y) b1_score_q4)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q4)) q4))\n" +
        "(declare-const cond1_b1 Bool)\n" +
        "(assert (= cond1_b1 (or (and (<=  1.0 0.5) (not (or q1 q2 q4)))  (and (or q1 q2 q4)  (<=  (+ b1_score_q1 b1_score_q2 b1_score_q4) 0.5)))))\n(assert (= cond1 cond1_b1))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)"
    generator.generate() should beZ3Model(expected)
  }

  @Test
  def testGenerateWithNonConstantDefaultScoreNoMultiplier() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 x) (q2 2 *y) (q4 y*4)) default z\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    val expected =
      "(declare-const q1 Bool)\n" +
        "(declare-const q2 Bool)\n" +
        "(declare-const q4 Bool)\n" +
        "(declare-const x Real)\n" +
        "(declare-const y Real)\n" +
        "(declare-const z Real)\n" +
        "(declare-const cond1 Bool)\n" +
        "(declare-const b1_score_q1 Real)\n" +
        "(assert (implies q1 (= x b1_score_q1)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q1)) q1))\n" +
        "(declare-const b1_score_q2 Real)\n" +
        "(assert (implies q2 (= (* 2.0 y) b1_score_q2)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q2)) q2))\n" +
        "(declare-const b1_score_q4 Real)\n" +
        "(assert (implies q4 (= (* 4.0 y) b1_score_q4)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q4)) q4))\n" +
        "(declare-const cond1_b1 Bool)\n" +
        "(assert (= cond1_b1 (or (and (<= z 0.5) (not (or q1 q2 q4)))  (and (or q1 q2 q4)  (<=  (+ b1_score_q1 b1_score_q2 b1_score_q4) 0.5)))))\n(assert (= cond1 cond1_b1))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)"
    generator.generate() should beZ3Model(expected)
  }

  @Test
  def testGenerateWithNonConstantDefaultScore() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 x) (q2 2 *y) (q4 y*4)) default z * 2\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    val expected =
      "(declare-const q1 Bool)\n" +
        "(declare-const q2 Bool)\n" +
        "(declare-const q4 Bool)\n" +
        "(declare-const x Real)\n" +
        "(declare-const y Real)\n" +
        "(declare-const z Real)\n" +
        "(declare-const cond1 Bool)\n" +
        "(declare-const b1_score_q1 Real)\n" +
        "(assert (implies q1 (= x b1_score_q1)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q1)) q1))\n" +
        "(declare-const b1_score_q2 Real)\n" +
        "(assert (implies q2 (= (* 2.0 y) b1_score_q2)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q2)) q2))\n" +
        "(declare-const b1_score_q4 Real)\n" +
        "(assert (implies q4 (= (* 4.0 y) b1_score_q4)))\n" +
        "(assert (implies (not (= 0.0 b1_score_q4)) q4))\n" +
        "(declare-const cond1_b1 Bool)\n" +
        "(assert (= cond1_b1 (or (and (<= (* 2.0 z) 0.5) (not (or q1 q2 q4)))  (and (or q1 q2 q4)  (<=  (+ b1_score_q1 b1_score_q2 b1_score_q4) 0.5)))))\n(assert (= cond1 cond1_b1))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)"
    generator.generate() should beZ3Model(expected)
  }

  @Test
  def testGenerateWithNonConstantDefaultScoreGreaterThanTh() {
    val input = "POLICIES\n" +
      "b1 = * ((q1 x) (q2 2 *y) (q4 y*4)) default 1.1 * z\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = 0.5 < pSet1\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new LazySynthesiser(input)
    val expected =
      "(declare-const q1 Bool)\n" +
        "(declare-const q2 Bool)\n" +
        "(declare-const q4 Bool)\n" +
        "(declare-const x Real)\n" +
        "(declare-const y Real)\n" +
        "(declare-const z Real)\n" +
        "(declare-const cond1 Bool)\n" +
        "(declare-const b1_score_q1 Real)\n" +
        "(assert (implies q1 (= x b1_score_q1)))\n" +
        "(assert (implies (not (= 1.0 b1_score_q1)) q1))\n" +
        "(declare-const b1_score_q2 Real)\n" +
        "(assert (implies q2 (= (* 2.0 y) b1_score_q2)))\n" +
        "(assert (implies (not (= 1.0 b1_score_q2)) q2))\n" +
        "(declare-const b1_score_q4 Real)\n" +
        "(assert (implies q4 (= (* 4.0 y) b1_score_q4)))\n" +
        "(assert (implies (not (= 1.0 b1_score_q4)) q4))\n" +
        "(declare-const cond1_b1 Bool)\n" +
        "(assert (= cond1_b1 (or (and (< 0.5 (* 1.1 z)) (not (or q1 q2 q4)))  (and (or q1 q2 q4)  " +
        "(< 0.5 (* b1_score_q1 b1_score_q2 b1_score_q4))))))\n" +
        "(assert (= cond1 cond1_b1))" +
        "\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)"
    generator.generate() should beZ3Model(expected)
  }
}
