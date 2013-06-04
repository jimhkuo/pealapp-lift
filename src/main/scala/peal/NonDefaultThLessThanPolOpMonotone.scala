package peal

import peal.synthesis.NonDefaultSet
import scala.collection.immutable.Set
import peal.domain.{Rule, Pol}

class NonDefaultThLessThanPolOpMonotone(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = ""

  def enumOne(): Set[Set[Rule]] = {
    val sortedRulesByScore = pol.rules.sortBy(_.score)

    val m1 = Set[Set[Rule]]()

    def enumOne(x: Set[Rule], sum: Double, index: Integer) {
    }

    Set()
  }

}
