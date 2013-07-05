package peal.z3

import z3.scala.dsl._
import z3.scala._
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class Z3AnalyserTest extends ShouldMatchersForJUnit {

  @Test
  def testCanRunSimpleFirstAnalysis() {

    val input = "cond = pSet <= 0.5" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"

    new Z3Analyser().isAlwaysTrue(input) should be (false)
  }
}
