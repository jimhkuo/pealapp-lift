package peal.model

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class MajorityVotingGeneratorTest extends ShouldMatchersForJUnit {

  @Test
  def testGenerate() {
    val expected = "b1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)(q5 0.1)(q6 0.1)(q7 0.1)(q8 0.1)(q9 0.1) ) default 0\npSet = b1\ncond = 0.5 < pSet\nANALYSES\nanalysis = always_true? cond\n"
    MajorityVotingGenerator.generateForCount(10) should be(expected)
  }
}
