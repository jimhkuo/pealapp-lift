package peal.synthesis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.util.Z3ModelMatcher
import peal.domain.z3.{PealAst, Term}

class NotConditionTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"))

  @Test
  def testSimpleCase() {
    val cond1 = new NotCondition("cond2")
    cond1.synthesis(consts) should beZ3Model("(not cond2)")
  }
}
