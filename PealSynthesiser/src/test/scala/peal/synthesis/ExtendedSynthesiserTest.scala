package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.util.Z3ModelMatcher

class ExtendedSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Test
  def testCanGeneratePolDeclarations() {
    val input = "POLICIES\n" +
      "b1 = + () default 1\n" +
      "POLICY_SETS\n" +
      "pSet1 = b1\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val generator = new ExendedSynthesiser(input)
//    println(generator.generate())
    generator.generate() should startWith("(declare-const cond1 Bool)")
    //    generator.generate() should beZ3Model("(declare-const cond1 Bool)\n(declare-const cond1_b1 Bool)\n(assert (= cond1_b1 (or (and (<= 1.0 0.5) (not false))  (and false  (<= 0.0 0.5)))))\n(assert (= cond1 cond1_b1))\n\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)")

  }
}
