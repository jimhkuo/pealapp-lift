package peal.lazysynthesis

import z3.scala.{Z3Config, Z3Context}
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class LazySynthesiserTest extends ShouldMatchersForJUnit {

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
      //    "cond3 = 0.5 < pSet2\n" +
      //    "cond4 = 0.4 < pSet2\n" +
      "ANALYSES\nname1 = always_true? cond1\n"

    val z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val generator = new LazySynthesiser(z3, input)
    println("output:\n" + generator.generate())
  }

  @Test
  def testBroken() {
    val input = "POLICIES\nb0 = * ((q4 0.17)) default 0.95\nb1 = max ((q1 0.86)) default 0.51\nb2 = + ((q4 0.86)) default 0.99\nb3 = min ((q4 0.66) (q1 0.79)) default 0.83\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis1 = always_true? cond1"
    val z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val generator = new LazySynthesiser(z3, input)
    println("output:\n" + generator.generate())
  }
}
