package peal.runner

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class RunnerTest extends ShouldMatchersForJUnit {

  @Test
  def testRunMode() {
    Seq[RunMode](Explicit) should contain(Explicit.asInstanceOf[RunMode])
    Seq[RunMode](Explicit) should (not contain(Symbolic.asInstanceOf[RunMode]))
    Seq(Explicit, Symbolic) should contain(Symbolic.asInstanceOf[RunMode])
  }

}
