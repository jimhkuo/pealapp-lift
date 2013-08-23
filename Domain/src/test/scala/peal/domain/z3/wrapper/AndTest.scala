package peal.domain.z3.wrapper

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class AndTest extends ShouldMatchersForJUnit {

  @Test
  def testApplyEmptyTerm() {
    And().toString should be ("")

  }

}
