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

}
