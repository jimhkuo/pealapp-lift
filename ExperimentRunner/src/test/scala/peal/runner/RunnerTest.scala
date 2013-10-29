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

  private def allEqual(list: List[Any]): Boolean = list match {
    case x1 :: x2 :: xs => (x1 == x2) && allEqual(x2 :: xs)
    case x1 :: Nil => true
  }

  @Test
  def testList() {
      allEqual(List(1,1,1)) should be (true)
      allEqual(List(1,1,2)) should be (false)
  }
}
