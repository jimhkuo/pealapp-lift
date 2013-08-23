package peal.domain.z3.wrapper

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class AndTest extends ShouldMatchersForJUnit {

  @Test
  def testApplySingleTerm() {
    And().toString should be ("")
    And("a").toString should be ("a")
    And(And("a")).toString should be ("a")
  }

  @Test
  def testApplyMultipleTerm() {
    And(And("a"), And("b")).toString should be ("(and a b)")
    And(And("a"), And("b"), And("c")).toString should be ("(and a b c)")
    And(And("a"), And(And("b"), And("d")), And("c")).toString should be ("(and a (and b d) c)")
  }

}
