package peal.lazysynthesis

import _root_.z3.scala.{Z3Config, Z3Context}
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class LazySynthesiserTest extends ShouldMatchersForJUnit {

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

  @Test
  def testGenerate() {
    val z3 = new Z3Context(new Z3Config("MODEL" -> true))

    val generator = new LazySynthesiser(z3, input)
    println("output:\n" + generator.generate())
  }
}
