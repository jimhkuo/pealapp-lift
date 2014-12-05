package peal.synthesis

import org.junit.{Ignore, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.util.Z3ModelMatcher

class ExtendedSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Ignore("wip")
  @Test
  def testSynthesisBug() {
    val input = "POLICIES\nb0 = min ((q14 0*vl) (q0 0.7850)) default 0.8919\nb1 = * ((q6 0.6819) (q7 0.7271)) default 0.5390\nb2 = * ((q12 0.3504) (q0 0.4032)) default 3*vp\nb3 = + ((q3 0.3078) (q7 0.1332)) default 0.7163\nb4 = max ((q3 0.0948 [-0.1435,0.4347]) (q11 0.1327)) default 0.8418\nb5 = * ((q3 0.3235) (q9 2*v0 [-0.3561,0.7747])) default 0*vy\nb6 = max ((q14 0.5613) (q4 0.6564)) default 0.6351\nb7 = + ((q8 vx) (q2 0.9709)) default 0.5696 [-0.8854,0.0560]\nb8 = * ((q14 0.7031) (q5 0.6469)) default 0.3753\nb9 = min ((q11 0.3598) (q7 0.4311)) default 0.0868\nb10 = * ((q10 0.1041) (q12 0.6119)) default 0.8983\nb11 = + ((q1 0.4650) (q4 0.6019)) default 0.3478\nb12 = min ((q3 3*vu [-0.2797,0.6717]) (q9 2*vu)) default 0.0223\nb13 = max ((q5 0.9802) (q2 0.9236)) default 0.8039\nb14 = min ((q6 2*vk) (q13 0.4516)) default 2*vl\nb15 = max ((q13 0.3230) (q12 0.9485)) default 0.6186\nb16 = + ((q1 0.8562 [-0.9047,0.6472]) (q11 3*vb)) default 0.6468\nb17 = + ((q4 2*vl) (q11 0.8956)) default 0.5290\nb18 = min ((q6 0.0261) (q4 0.8287)) default 0.9865\nb19 = max ((q0 0.0663) (q10 0.5418)) default 0.7368\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis2 = always_false? cond2\nanalysis3 = always_false? cond2"
    val out = new ExtendedSynthesiser(input).generate(doVacuityCheck = false)
    val expected = "(declare-const q0 Bool)\n(declare-const q9 Bool)\n(declare-const q10 Bool)\n(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q12 Bool)\n(declare-const q2 Bool)\n(declare-const q8 Bool)\n(declare-const q3 Bool)\n(declare-const q13 Bool)\n(declare-const q14 Bool)\n(declare-const q11 Bool)\n(declare-const q7 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const b7_default_U Real)\n(assert (and (<= b7_default_U 0.056) (<= -0.8854 b7_default_U)))\n(declare-const b0_score Real)\n(declare-const b10_score Real)\n(declare-const b11_score Real)\n(declare-const b12_score Real)\n(declare-const b13_score Real)\n(declare-const b14_score Real)\n(declare-const b15_score Real)\n(declare-const b16_score Real)\n(declare-const b17_score Real)\n(declare-const b18_score Real)\n(declare-const b19_score Real)\n(declare-const b1_score Real)\n(declare-const b2_score Real)\n(declare-const b3_score Real)\n(declare-const b4_score Real)\n(declare-const b5_score Real)\n(declare-const b6_score Real)\n(declare-const b7_score Real)\n(declare-const b8_score Real)\n(declare-const b9_score Real)\n(declare-const p0_15_0_score Real)\n(declare-const p0_15_1_score Real)\n(declare-const p0_15_score Real)\n(declare-const p0_1_score Real)\n(declare-const p0_3_score Real)\n(declare-const p0_7_score Real)\n(declare-const p10_11_score Real)\n(declare-const p12_13_score Real)\n(declare-const p12_15_score Real)\n(declare-const p14_15_score Real)\n(declare-const p16_17_score Real)\n(declare-const p18_19_score Real)\n(declare-const p2_3_score Real)\n(declare-const p4_5_score Real)\n(declare-const p4_7_score Real)\n(declare-const p6_7_score Real)\n(declare-const p8_11_score Real)\n(declare-const p8_15_score Real)\n(declare-const p8_9_score Real)\n(declare-const v0 Real)\n(declare-const vb Real)\n(declare-const vk Real)\n(declare-const vl Real)\n(declare-const vp Real)\n(declare-const vu Real)\n(declare-const vx Real)\n(declare-const vy Real)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(declare-const b5_q9_U Real)\n(assert (and (<= b5_q9_U 0.7747) (<= -0.3561 b5_q9_U)))\n(declare-const b4_q3_U Real)\n(assert (and (<= b4_q3_U 0.4347) (<= -0.1435 b4_q3_U)))\n(declare-const b12_q3_U Real)\n(assert (and (<= b12_q3_U 0.6717) (<= -0.2797 b12_q3_U)))\n(declare-const b16_q1_U Real)\n(assert (and (<= b16_q1_U 0.6472) (<= -0.9047 b16_q1_U)))\n\n(assert (= cond2 (< 0.6 (ite (< (ite (< (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score)) (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score)) (+ b18_score b19_score)) (+ b18_score b19_score) (ite (< (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score)) (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score))))))\n(assert (= cond1 (< 0.5 (ite (< (ite (< (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score)) (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score)) (+ b18_score b19_score)) (+ b18_score b19_score) (ite (< (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score)) (* (+ (ite (< (ite (< b0_score b1_score) b0_score b1_score) (ite (< b2_score b3_score) b2_score b3_score)) (ite (< b2_score b3_score) b2_score b3_score) (ite (< b0_score b1_score) b0_score b1_score)) (ite (< (ite (< b4_score b5_score) b4_score b5_score) (ite (< b6_score b7_score) b6_score b7_score)) (ite (< b6_score b7_score) b6_score b7_score) (ite (< b4_score b5_score) b4_score b5_score))) (+ (ite (< (ite (< b8_score b9_score) b8_score b9_score) (ite (< b10_score b11_score) b10_score b11_score)) (ite (< b10_score b11_score) b10_score b11_score) (ite (< b8_score b9_score) b8_score b9_score)) (ite (< (ite (< b12_score b13_score) b12_score b13_score) (ite (< b14_score b15_score) b14_score b15_score)) (ite (< b14_score b15_score) b14_score b15_score) (ite (< b12_score b13_score) b12_score b13_score)))) (ite (< b16_score b17_score) b16_score b17_score))))))\n(assert (= b3_score (ite (or q3 q7) (+ (ite q3 0.3078 0.0) (ite q7 0.1332 0.0)) 0.7163)))\n(assert (= b2_score (ite (or q12 q0) (* (ite q12 0.3504 1.0) (ite q0 0.4032 1.0)) (* 3.0 vp))))\n(assert (= b5_score (ite (or q3 q9) (* (ite q3 0.3235 1.0) (ite q9 (+ (* 2.0 v0) b5_q9_U) 1.0)) (* 0.0 vy))))\n(assert (implies q3 (>= b4_score (+ 0.0948 b4_q3_U))))\n(assert (implies q11 (>= b4_score 0.1327)))\n(assert (implies (not (or q3 q11)) (= b4_score 0.8418)))\n(assert (implies (or q3 q11) (or (and q3 (= b4_score (+ 0.0948 b4_q3_U))) (and q11 (= b4_score 0.1327)))))\n(assert (= b7_score (ite (or q8 q2) (+ (ite q8 vx 0.0) (ite q2 0.9709 0.0)) (+ 0.5696 b7_default_U))))\n(assert (implies q14 (>= b6_score 0.5613)))\n(assert (implies q4 (>= b6_score 0.6564)))\n(assert (implies (not (or q14 q4)) (= b6_score 0.6351)))\n(assert (implies (or q14 q4) (or (and q14 (= b6_score 0.5613)) (and q4 (= b6_score 0.6564)))))\n(assert (implies q11 (<= b9_score 0.3598)))\n(assert (implies q7 (<= b9_score 0.4311)))\n(assert (implies (not (or q11 q7)) (= b9_score 0.0868)))\n(assert (implies (or q11 q7) (or (and q11 (= b9_score 0.3598)) (and q7 (= b9_score 0.4311)))))\n(assert (= b8_score (ite (or q14 q5) (* (ite q14 0.7031 1.0) (ite q5 0.6469 1.0)) 0.3753)))\n(assert (implies q0 (>= b19_score 0.0663)))\n(assert (implies q10 (>= b19_score 0.5418)))\n(assert (implies (not (or q0 q10)) (= b19_score 0.7368)))\n(assert (implies (or q0 q10) (or (and q0 (= b19_score 0.0663)) (and q10 (= b19_score 0.5418)))))\n(assert (= b17_score (ite (or q4 q11) (+ (ite q4 (* 2.0 vl) 0.0) (ite q11 0.8956 0.0)) 0.529)))\n(assert (implies q6 (<= b18_score 0.0261)))\n(assert (implies q4 (<= b18_score 0.8287)))\n(assert (implies (not (or q6 q4)) (= b18_score 0.9865)))\n(assert (implies (or q6 q4) (or (and q6 (= b18_score 0.0261)) (and q4 (= b18_score 0.8287)))))\n(assert (implies q14 (<= b0_score (* 0.0 vl))))\n(assert (implies q0 (<= b0_score 0.785)))\n(assert (implies (not (or q14 q0)) (= b0_score 0.8919)))\n(assert (implies (or q14 q0) (or (and q14 (= b0_score (* 0.0 vl))) (and q0 (= b0_score 0.785)))))\n(assert (= b1_score (ite (or q6 q7) (* (ite q6 0.6819 1.0) (ite q7 0.7271 1.0)) 0.539)))\n(assert (= b11_score (ite (or q1 q4) (+ (ite q1 0.465 0.0) (ite q4 0.6019 0.0)) 0.3478)))\n(assert (implies q3 (<= b12_score (+ (* 3.0 vu) b12_q3_U))))\n(assert (implies q9 (<= b12_score (* 2.0 vu))))\n(assert (implies (not (or q3 q9)) (= b12_score 0.0223)))\n(assert (implies (or q3 q9) (or (and q3 (= b12_score (+ (* 3.0 vu) b12_q3_U))) (and q9 (= b12_score (* 2.0 vu))))))\n(assert (= b10_score (ite (or q10 q12) (* (ite q10 0.1041 1.0) (ite q12 0.6119 1.0)) 0.8983)))\n(assert (implies q13 (>= b15_score 0.323)))\n(assert (implies q12 (>= b15_score 0.9485)))\n(assert (implies (not (or q13 q12)) (= b15_score 0.6186)))\n(assert (implies (or q13 q12) (or (and q13 (= b15_score 0.323)) (and q12 (= b15_score 0.9485)))))\n(assert (= b16_score (ite (or q1 q11) (+ (ite q1 (+ 0.8562 b16_q1_U) 0.0) (ite q11 (* 3.0 vb) 0.0)) 0.6468)))\n(assert (implies q5 (>= b13_score 0.9802)))\n(assert (implies q2 (>= b13_score 0.9236)))\n(assert (implies (not (or q5 q2)) (= b13_score 0.8039)))\n(assert (implies (or q5 q2) (or (and q5 (= b13_score 0.9802)) (and q2 (= b13_score 0.9236)))))\n(assert (implies q6 (<= b14_score (* 2.0 vk))))\n(assert (implies q13 (<= b14_score 0.4516)))\n(assert (implies (not (or q6 q13)) (= b14_score (* 2.0 vl))))\n(assert (implies (or q6 q13) (or (and q6 (= b14_score (* 2.0 vk))) (and q13 (= b14_score 0.4516)))))\n(echo \"Result of analysis [analysis2 = always_false? cond2]:\")\n(push)\n(declare-const always_false_analysis2 Bool)\n(assert (= always_false_analysis2 cond2))\n(assert always_false_analysis2)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [analysis3 = always_false? cond2]:\")\n(push)\n(declare-const always_false_analysis3 Bool)\n(assert (= always_false_analysis3 cond2))\n(assert always_false_analysis3)\n(check-sat)\n(get-model)\n(pop)\n"
    println(out)
    println("###############")
    println(expected)
    out should be(expected)
  }

  @Test
  def testNoDuplicateDeclarations() {
    val input = "POLICIES\nfuel' = +((gasoline 0.1) (coal 0.02) (wood 0.09)) default 0\n" +
      "ignition = +((matches 0.2) (gas_stove 0.1) (electrical_sparc 0.05)) default 0\n" +
      "oxygen = +() default 1\n" +
      "fire = *((True fuel'_score) (True ignition_score) (True oxygen_score)) default 1\n" +
      "POLICY_SETS\npSet1 = fire\n" +
      "CONDITIONS\ncond1 = 0.0734 < pSet1\ncond2 = 0.0735 < pSet1\ncond3 = pSet1 <= 0.0735\ncond45 = coal\ncond4 = cond45 && cond3\n" +
      "DOMAIN_SPECIFICS\n(assert True)\nANALYSES\nname1 = satisfiable? cond1\nname2 = satisfiable? cond2\nname3 = satisfiable? cond4"
    new ExtendedSynthesiser(input).generate(doVacuityCheck = true) should beZ3Model("(declare-const gas_stove Bool)\n" +
      "(declare-const gasoline Bool)\n" +
      "(declare-const electrical_sparc Bool)\n" +
      "(declare-const coal Bool)\n(declare-const wood Bool)\n" +
      "(declare-const matches Bool)\n" +
      "(declare-const True Bool)\n\n" +
      "(declare-const fire_score Real)\n" +
      "(declare-const fuel'_score Real)\n" +
      "(declare-const ignition_score Real)\n" +
      "(declare-const oxygen_score Real)\n" +
      "(declare-const pSet1_score Real)\n" +
      "(declare-const cond45 Bool)\n" +
      "(declare-const cond3 Bool)\n" +
      "(declare-const cond2 Bool)\n" +
      "(declare-const cond4 Bool)\n" +
      "(declare-const cond1 Bool)\n\n" +
      "(assert True)\n" +
      "(assert (= cond45 coal))\n" +
      "(assert (= cond1 (< 0.0734 fire_score)))\n" +
      "(assert (= cond2 (< 0.0735 fire_score)))\n" +
      "(assert (= cond3 (<= fire_score 0.0735)))\n" +
      "(assert (= cond4 (and coal (<= fire_score 0.0735))))\n" +
      "(assert (= oxygen_score 1.0))\n" +
      "(assert (= ignition_score (ite (or matches gas_stove electrical_sparc) (+ (ite matches 0.2 0.0) (ite gas_stove 0.1 0.0) (ite electrical_sparc 0.05 0.0)) 0.0)))\n" +
      "(assert (= fire_score (ite (or True True True) (* (ite True fuel'_score 1.0) (ite True ignition_score 1.0) (ite True oxygen_score 1.0)) 1.0)))\n" +
      "(assert (= fuel'_score (ite (or gasoline coal wood) (+ (ite gasoline 0.1 0.0) (ite coal 0.02 0.0) (ite wood 0.09 0.0)) 0.0)))\n" +
      "(echo \"Result of analysis [a1_cond1_vct = always_true? cond1]:\")\n(push)\n(declare-const always_true_a1_cond1_vct Bool)\n(assert (= always_true_a1_cond1_vct cond1))\n(assert (not always_true_a1_cond1_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a1_cond2_vct = always_true? cond2]:\")\n(push)\n(declare-const always_true_a1_cond2_vct Bool)\n(assert (= always_true_a1_cond2_vct cond2))\n(assert (not always_true_a1_cond2_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a1_cond3_vct = always_true? cond3]:\")\n(push)\n(declare-const always_true_a1_cond3_vct Bool)\n(assert (= always_true_a1_cond3_vct cond3))\n(assert (not always_true_a1_cond3_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a1_cond45_vct = always_true? cond45]:\")\n(push)\n(declare-const always_true_a1_cond45_vct Bool)\n(assert (= always_true_a1_cond45_vct cond45))\n(assert (not always_true_a1_cond45_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a1_cond4_vct = always_true? cond4]:\")\n(push)\n(declare-const always_true_a1_cond4_vct Bool)\n(assert (= always_true_a1_cond4_vct cond4))\n(assert (not always_true_a1_cond4_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond1_vcf = always_false? cond1]:\")\n(push)\n(declare-const always_false_a2_cond1_vcf Bool)\n(assert (= always_false_a2_cond1_vcf cond1))\n(assert always_false_a2_cond1_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond2_vcf = always_false? cond2]:\")\n(push)\n(declare-const always_false_a2_cond2_vcf Bool)\n(assert (= always_false_a2_cond2_vcf cond2))\n(assert always_false_a2_cond2_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond3_vcf = always_false? cond3]:\")\n(push)\n(declare-const always_false_a2_cond3_vcf Bool)\n(assert (= always_false_a2_cond3_vcf cond3))\n(assert always_false_a2_cond3_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond45_vcf = always_false? cond45]:\")\n(push)\n(declare-const always_false_a2_cond45_vcf Bool)\n(assert (= always_false_a2_cond45_vcf cond45))\n(assert always_false_a2_cond45_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond4_vcf = always_false? cond4]:\")\n(push)\n(declare-const always_false_a2_cond4_vcf Bool)\n(assert (= always_false_a2_cond4_vcf cond4))\n(assert always_false_a2_cond4_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = satisfiable? cond1]:\")\n(push)\n(declare-const satisfiable_name1 Bool)\n(assert (= satisfiable_name1 cond1))\n(assert satisfiable_name1)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name2 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name2 Bool)\n(assert (= satisfiable_name2 cond2))\n(assert satisfiable_name2)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name3 = satisfiable? cond4]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond4))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n")
  }

  @Test
  def testNoRule() {
    val input = "POLICIES\nb1 = min () default 1\nPOLICY_SETS\npSet1 = b1\n" +
      "CONDITIONS\ncond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1"

    println(new ExtendedSynthesiser(input).generate(doVacuityCheck = true))
    new ExtendedSynthesiser(input).generate(doVacuityCheck = true).trim should be ("(declare-const b1_score Real)\n(declare-const pSet1_score Real)\n(declare-const cond1 Bool)\n\n\n(assert (= cond1 (<= b1_score 0.5)))\n(assert (= b1_score 1.0))\n" +
      "(echo \"Result of analysis [a1_cond1_vct = always_true? cond1]:\")\n(push)\n(declare-const always_true_a1_cond1_vct Bool)\n(assert (= always_true_a1_cond1_vct cond1))\n(assert (not always_true_a1_cond1_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond1_vcf = always_false? cond1]:\")\n(push)\n(declare-const always_false_a2_cond1_vcf Bool)\n(assert (= always_false_a2_cond1_vcf cond1))\n(assert always_false_a2_cond1_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testCanGeneratePolCompositionWithPlusAndOneRule() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1 [-0.2,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))") should be(true)
  }

  @Test
  def testCanSkipCommentsInDomainSpecifics() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1 [-0.2,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "DOMAIN_SPECIFICS\n" +
      "%aaa\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    new ExtendedSynthesiser(input).generate(doVacuityCheck = true) should be ("(declare-const q1 Bool)\n(declare-const b1_default_U Real)\n(assert (and (<= b1_default_U 0.2) (<= -0.2 b1_default_U)))\n(declare-const b1_score Real)\n(declare-const pSet1_score Real)\n(declare-const cond1 Bool)\n(declare-const b1_q1_U Real)\n(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))\n\n(assert (= cond1 (<= b1_score 0.5)))\n(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))\n" +
      "(echo \"Result of analysis [a1_cond1_vct = always_true? cond1]:\")\n(push)\n(declare-const always_true_a1_cond1_vct Bool)\n(assert (= always_true_a1_cond1_vct cond1))\n(assert (not always_true_a1_cond1_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond1_vcf = always_false? cond1]:\")\n(push)\n(declare-const always_false_a2_cond1_vcf Bool)\n(assert (= always_false_a2_cond1_vcf cond1))\n(assert always_false_a2_cond1_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n")
  }

  @Test
  def testCanSkipCommentsWithSpaces() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1 [-0.2,0.2]\n" +
      "    %aaa\n" +
      "POLICY_SETS\n" +
      "    %aaa\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "    %aaa\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "DOMAIN_SPECIFICS\n" +
      "    %aaa\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    new ExtendedSynthesiser(input).generate(doVacuityCheck = true) should be ("(declare-const q1 Bool)\n(declare-const b1_default_U Real)\n(assert (and (<= b1_default_U 0.2) (<= -0.2 b1_default_U)))\n(declare-const b1_score Real)\n(declare-const pSet1_score Real)\n(declare-const cond1 Bool)\n(declare-const b1_q1_U Real)\n(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))\n\n(assert (= cond1 (<= b1_score 0.5)))\n(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))\n" +
      "(echo \"Result of analysis [a1_cond1_vct = always_true? cond1]:\")\n(push)\n(declare-const always_true_a1_cond1_vct Bool)\n(assert (= always_true_a1_cond1_vct cond1))\n(assert (not always_true_a1_cond1_vct))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [a2_cond1_vcf = always_false? cond1]:\")\n(push)\n(declare-const always_false_a2_cond1_vcf Bool)\n(assert (= always_false_a2_cond1_vcf cond1))\n(assert always_false_a2_cond1_vcf)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n")
  }

  @Test
  def testCanGeneratePolCompositionWithMulAndOneRule() {
    val input = "POLICIES\n" +
      "b1 = * ((q1 0.5 [-0.1,0.1])) default 1 [-0.2,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithPlusAndNoRule() {
    val input = "POLICIES\n" +
      "b2 = + () default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithMaxAndNoRule() {
    val input = "POLICIES\n" +
      "b2 = max () default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithMinAndNoRule() {
    val input = "POLICIES\n" +
      "b2 = min () default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithMaxAndOneRule() {
    val input = "POLICIES\n" +
      "b2 = max ((q1 0.2)) default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (ite q1 0.2 (+ 0.3 b2_default_U))))") should be(true)
    generator.generate().contains("(assert (implies q1 (and q1 (= b2_score (+ 0.3 b2_default_U)))))") should be(false)
  }

  @Test
  def testCanGeneratePolCompositionWithMinAndOneRule() {
    val input = "POLICIES\n" +
      "b2 = min ((q1 0.2)) default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (ite q1 0.2 (+ 0.3 b2_default_U))))") should be(true)
    generator.generate().contains("(assert (implies q1 (and q1 (= b2_score (+ 0.3 b2_default_U)))))") should be(false)
  }

  @Test
  def testCanGeneratePolCompositionWithMulAndNoRule() {
    val input = "POLICIES\n" +
      "b2 = * () default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithPlusAndMoreThanOneRule() {
    val input = "POLICIES\n" +
      "b2 = + ((q1 0.5 [-0.1,0.11]) (q2 0.7 [-0.2,0.4])) default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (ite (or q1 q2) (+ (ite q1 (+ 0.5 b2_q1_U) 0.0) (ite q2 (+ 0.7 b2_q2_U) 0.0)) (+ 0.3 b2_default_U))))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithMulAndMoreThanOneRule() {
    val input = "POLICIES\n" +
      "b2 = * ((q1 0.5 [-0.1,0.11]) (q2 0.7 [-0.2,0.4])) default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (ite (or q1 q2) (* (ite q1 (+ 0.5 b2_q1_U) 1.0) (ite q2 (+ 0.7 b2_q2_U) 1.0)) (+ 0.3 b2_default_U))))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithMaxAndMoreThanOneRule() {
    val input = "POLICIES\n" +
      "b2 = max ((q1 0.5 [-0.1,0.11]) (q2 0.7 [-0.2,0.4])) default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (implies q1 (>= b2_score (+ 0.5 b2_q1_U))))") should be(true)
    generator.generate().contains("(assert (implies q2 (>= b2_score (+ 0.7 b2_q2_U))))") should be(true)
    generator.generate().contains("(assert (implies (not (or q1 q2)) (= b2_score (+ 0.3 b2_default_U))))") should be(true)
    generator.generate().contains("(assert (implies (or q1 q2) (or (and q1 (= b2_score (+ 0.5 b2_q1_U))) (and q2 (= b2_score (+ 0.7 b2_q2_U))))))") should be(true)
  }

  @Test
  def testCanGeneratePolCompositionWithMinAndMoreThanOneRule() {
    val input = "POLICIES\n" +
      "b2 = min ((q1 0.5 [-0.1,0.11]) (q2 0.7 [-0.2,0.4])) default 0.3 [-0.5,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b2\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (implies q1 (<= b2_score (+ 0.5 b2_q1_U))))") should be(true)
    generator.generate().contains("(assert (implies q2 (<= b2_score (+ 0.7 b2_q2_U))))") should be(true)
    generator.generate().contains("(assert (implies (not (or q1 q2)) (= b2_score (+ 0.3 b2_default_U))))") should be(true)
    generator.generate().contains("(assert (implies (or q1 q2) (or (and q1 (= b2_score (+ 0.5 b2_q1_U))) (and q2 (= b2_score (+ 0.7 b2_q2_U))))))") should be(true)
  }

  @Test
  def testCanGeneratePolDeclarationsWithRangeInDefault() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1 [-0.2,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_default_U Real)") should be(true)
    generator.generate().contains("(assert (and (<= b1_default_U 0.2) (<= -0.2 b1_default_U)))") should be(true)
  }

  @Test
  def testCanGeneratePolDeclarationsWithNoRangeInDefault() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_default_U Real)") should be(false)
  }

  @Test
  def testCanGeneratePolDeclarationsWithRange() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1 [-0.2,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_score Real)") should be(true)
    generator.generate().contains("(declare-const b1_q1_U Real)") should be(true)
    generator.generate().contains("(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))") should be(true)
  }

  @Test
  def testCanGeneratePolDeclarationsWithRange2() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1]) (q2 x [-0.4,0.7]) (q3 0.9)) default 1 [-0.2,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_score Real)") should be(true)
    generator.generate().contains("(declare-const b1_q1_U Real)") should be(true)
    generator.generate().contains("(declare-const b1_q2_U Real)") should be(true)
    generator.generate().contains("(declare-const b1_q3_U Real)") should be(false)
    generator.generate().contains("(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))") should be(true)
    generator.generate().contains("(assert (and (<= b1_q2_U 0.7) (<= -0.4 b1_q2_U)))") should be(true)
  }

  @Test
  def testRepeatedVariable() {
    val input = "POLICIES\nb0 = max ((q4 0.7528) (q13 0.8110)) default 0.0489\nb1 = max ((q3 0.4505) (q8 0.7086)) default 0.4216\nb2 = * ((q8 0.8403) (q4 0.6922)) default 0.3636\nb3 = * ((q4 0.4179 [-0.7958,0.2001]) (q9 0.0763)) default 0.2523 [-0.6524,0.4110]\nb4 = + ((q6 0.0328) (q5 0.1452)) default 0.0701\nb5 = max ((q12 0.7179) (q9 0.8549)) default 0.2833\nb6 = min ((q4 0.8498) (q9 0.7248 [-0.8732,0.5363])) default 0.1216\nb7 = max ((q11 0.7320) (q3 0.3716 [-0.7678,0.8730])) default 0.2198\nb8 = max ((q5 0.3278) (q3 0.2107)) default 0.3878\nb9 = + ((q1 0.5593) (q2 0.5202)) default 0.4233\nb10 = min ((q2 0.8852) (q5 0.7320)) default 0.9117\nb11 = + ((q2 0.1844) (q9 0.3473 [-0.7293,0.1083])) default 0.4606\nb12 = * ((q4 0.5858) (q12 0.3064)) default 0.7399\nb13 = min ((q10 0.8935) (q14 0.2223)) default 0.7255\nb14 = + ((q9 0.6423) (q10 0.6708)) default 0.8657\nb15 = * ((q10 0.5926) (q3 0.7291)) default 0.5016\nb16 = + ((q11 0.5953) (q13 0.8902)) default 0.6373\nb17 = min ((q8 0.9006) (q11 0.1549)) default 0.6766\nb18 = min ((q2 0.0108) (q10 0.4235 [-0.0275,0.8143])) default 0.7508\nb19 = * ((q0 0.8657) (q9 0.6679)) default 0.6609\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\n\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    println(input)
    println(new ExtendedSynthesiser(input).generate())
  }
}
