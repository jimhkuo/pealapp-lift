package model

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.BasicPolicySet

class SomeTest extends ShouldMatchersForJUnit {

  @Test
  def testSubProject() {
    println("SomeTest in PealModelGenerator")
    BasicPolicySet
  }
}
