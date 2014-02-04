package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.util.Z3ModelMatcher

class ExtendedSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

//  @Test
//  def testCanGeneratePolDeclarations() {
//    val input = "POLICIES\n" +
//      "b1 = + ((q1 0.5 [-0.1,0.1])) default 1\n" +
//      "POLICY_SETS\n" +
//      "pSet1 = b1\n" +
//      "CONDITIONS\n" +
//      "cond1 = pSet1 <= 0.5\n" +
//      "ANALYSES\nname1 = always_true? cond1\n"
//
//    val generator = new ExendedSynthesiser(input)
//    println(generator.generate())
//    generator.generate().contains("(declare-const b1_score Real)") should be (true)
//    generator.generate().contains("(declare-const b1_q1_U Real)") should be (true)
//
//  }
}
