package peal.synthesis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.util.Z3ModelMatcher

class ExtendedSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Test
  def testDuplicateDeclarations() {
    val input = "POLICIES\nfuel = +((gasoline 0.1) (coal 0.02) (wood 0.09)) default 0\nignition = +((matches 0.2) (gas_stove 0.1) (electrical_sparc 0.05)) default 0\noxygen = +() default 1\nfire = *((True fuel_score) (True ignition_score) (True oxygen_score)) default 1\nPOLICY_SETS\npSet1 = fire\nCONDITIONS\ncond1 = 0.0734 < pSet1\ncond2 = 0.0735 < pSet1\ncond3 = pSet1 <= 0.0735\ncond45 = coal\ncond4 = cond45 && cond3\nDOMAIN_SPECIFICS\n(assert True)\nANALYSES\nname1 = satisfiable? cond1\nname2 = satisfiable? cond2\nname3 = satisfiable? cond4"
    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate() should be("(declare-const gas_stove Bool)\n(declare-const gasoline Bool)\n(declare-const electrical_sparc Bool)\n(declare-const coal Bool)\n(declare-const wood Bool)\n(declare-const matches Bool)\n(declare-const True Bool)\n\n(declare-const fuel_score Real)\n(declare-const ignition_score Real)\n(declare-const oxygen_score Real)\n(declare-const cond45 Bool)\n(declare-const cond3 Bool)\n(declare-const cond2 Bool)\n(declare-const cond4 Bool)\n(declare-const cond1 Bool)\n\n(declare-const fuel_score Real)\n(declare-const oxygen_score Real)\n(declare-const ignition_score Real)\n(declare-const fire_score Real)\n(declare-const pSet1_score Real)\n(assert True)\n(assert (= cond45 coal))\n(assert (= cond1 (< 0.0734 fire_score)))\n(assert (= cond2 (< 0.0735 fire_score)))\n(assert (= cond3 (<= fire_score 0.0735)))\n(assert (= cond4 (and coal (<= fire_score 0.0735))))\n(assert (= oxygen_score 1.0))\n(assert (= ignition_score (ite (or matches gas_stove electrical_sparc) (+ (ite matches 0.2 0.0) (ite gas_stove 0.1 0.0) (ite electrical_sparc 0.05 0.0)) 0.0)))\n(assert (= fuel_score (ite (or gasoline coal wood) (+ (ite gasoline 0.1 0.0) (ite coal 0.02 0.0) (ite wood 0.09 0.0)) 0.0)))\n(assert (= fire_score (ite (or True True True) (* (ite True fuel_score 1.0) (ite True ignition_score 1.0) (ite True oxygen_score 1.0)) 1.0)))\n(echo \"Result of analysis [name1 = satisfiable? cond1]:\")\n(push)\n(declare-const satisfiable_name1 Bool)\n(assert (= satisfiable_name1 cond1))\n(assert satisfiable_name1)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name2 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name2 Bool)\n(assert (= satisfiable_name2 cond2))\n(assert satisfiable_name2)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name3 = satisfiable? cond4]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond4))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n")
  }

  @Test
  def testNoRule() {
    val input = "POLICIES\nb1 = min () default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const cond1 Bool)\n\n" +
      "(declare-const b1_score Real)\n" +
      "(declare-const pSet1_score Real)\n\n" +
      "(assert (= cond1 (<= b1_score 0.5)))\n" +
      "(assert (= b1_score 1.0))\n" +
      "(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)") should be(true)
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

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate() should be ("(declare-const q1 Bool)\n(declare-const b1_default_U Real)\n(assert (and (<= b1_default_U 0.2) (<= -0.2 b1_default_U)))\n(declare-const cond1 Bool)\n(declare-const b1_q1_U Real)\n(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))\n(declare-const b1_score Real)\n(declare-const pSet1_score Real)\n\n(assert (= cond1 (<= b1_score 0.5)))\n(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n")
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

    val generator = new ExtendedSynthesiser(input)
    println(generator.generate())
    generator.generate() should be ("(declare-const q1 Bool)\n(declare-const b1_default_U Real)\n(assert (and (<= b1_default_U 0.2) (<= -0.2 b1_default_U)))\n(declare-const cond1 Bool)\n(declare-const b1_q1_U Real)\n(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))\n(declare-const b1_score Real)\n(declare-const pSet1_score Real)\n\n(assert (= cond1 (<= b1_score 0.5)))\n(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n")
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
