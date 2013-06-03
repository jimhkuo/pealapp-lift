package peal

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class PolLessThanThTest extends ShouldMatchersForJUnit {

  @Test
  def testCanDoSimpleSynthesis() {
    val q = new Predicate("q")
    val p = new Pol(List(new Rule(q, 0.5)), 0)

    val defaultSet = new PolLessThanTh(p, 0.6)

    println(defaultSet.synthesis)

  }

}
