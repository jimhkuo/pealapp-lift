package peal.domain.z3.wrapper

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.domain.z3.{Or, And}

class AstTest extends ShouldMatchersForJUnit {

  @Test
  def testApplySingleTermAnd() {
    And("a").toString should be ("a")
    And(And("a")).toString should be ("a")
  }

  @Test
  def testEmptyConjunction() {
    And().toString should be ("true")
  }

  @Test
  def testEmptyDisjunction() {
    Or().toString should be ("false")
  }

  @Test
  def testSingleTermForOr() {
    Or("a").toString should be ("a")
    Or(Or("a")).toString should be ("a")
  }

  @Test
  def testApplyMultipleTermAnd() {
    And(And("a"), And("b")).toString should be ("(and a b)")
    And(And("a"), And("b"), And("c")).toString should be ("(and a b c)")
    And(And("a"), And(And("b"), And("d")), And("c")).toString should be ("(and a (and b d) c)")
  }

  @Test
  def testApplyMultipleTermOr() {
    Or(And("a"), Or("b")).toString should be ("(or a b)")
    Or(And("a"), And("b"), And("c")).toString should be ("(or a b c)")
    Or(And("a"), And(And("b"), And("d")), And("c")).toString should be ("(or a (and b d) c)")
  }
}
