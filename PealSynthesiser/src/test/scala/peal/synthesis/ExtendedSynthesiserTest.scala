package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}
import peal.util.Z3ModelMatcher

class ExtendedSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Test
  def testCanGeneratePolCompositionWithPlusAndOneRule() {
    val input = "POLICIES\n" +
      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1 [-0.2,0.2]\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b1_score (ite q1 (+ 0.5 b1_q1_U) (+ 1.0 b1_default_U))))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (+ 0.3 b2_default_U)))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (ite (or q1 q2) (+ (ite q1 (+ 0.5 b2_q1_U) 0.0) (ite q2 (+ 0.7 b2_q2_U) 0.0)) (+ 0.3 b2_default_U))))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(assert (= b2_score (ite (or q1 q2) (* (ite q1 (+ 0.5 b2_q1_U) 1.0) (ite q2 (+ 0.7 b2_q2_U) 1.0)) (+ 0.3 b2_default_U))))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_default_U Real)") should be (true)
    generator.generate().contains("(assert (and (<= b1_default_U 0.2) (<= -0.2 b1_default_U)))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_default_U Real)") should be (false)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_score Real)") should be (true)
    generator.generate().contains("(declare-const b1_q1_U Real)") should be (true)
    generator.generate().contains("(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))") should be (true)
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

    val generator = new ExendedSynthesiser(input)
    println(generator.generate())
    generator.generate().contains("(declare-const b1_score Real)") should be (true)
    generator.generate().contains("(declare-const b1_q1_U Real)") should be (true)
    generator.generate().contains("(declare-const b1_q2_U Real)") should be (true)
    generator.generate().contains("(declare-const b1_q3_U Real)") should be (false)
    generator.generate().contains("(assert (and (<= b1_q1_U 0.1) (<= -0.1 b1_q1_U)))") should be (true)
    generator.generate().contains("(assert (and (<= b1_q2_U 0.7) (<= -0.4 b1_q2_U)))") should be (true)
  }
}
