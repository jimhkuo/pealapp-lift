package peal.z3

import z3.scala.dsl._
import z3.scala._
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}

class Z3AnalyserTest extends ShouldMatchersForJUnit {

  @Ignore("wip")
  @Test
  def testFirstAnalysisNotAlwaysTrue() {

    val input = "cond = pSet <= 0.5" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "pSet = max(b1, b2)"
    val analyser = new Z3Analyser()
    analyser.isAlwaysTrue(input) should be (false)
    analyser.cleanUp
  }

  @Ignore("wip")
  @Test
  def testFirstAnalysisAlwaysFalse() {
    val input = "cond = pSet <= 0.5\nb1 = + ((q1 0.9)) default 1\npSet = b1"
    val analyser = new Z3Analyser()
    analyser.isAlwaysTrue(input) should be (false)
    analyser.cleanUp
  }

  @Ignore("wip")
  @Test
  def testFirstAnalysisAlwaysTrue() {
    val input = "cond = pSet <= 0.5\nb1 = + ((q1 0.4)) default 0\npSet = b1"
    val analyser = new Z3Analyser()
    analyser.isAlwaysTrue(input) should be (true)
    analyser.cleanUp
  }
}
