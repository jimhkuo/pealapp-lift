package peal

import peal.synthesis.NonDefaultSet
import peal.domain.{Rule, Pol}
import scala._

class NonDefaultThLessThanPolOpMonotone(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = enumOne().map(_.mkString(" ")).mkString("\n")

  def enumOne(): Set[Set[Rule]] = {
    val sortedRulesByScore = pol.rules.sortBy(_.score)
    val t = sortedRulesByScore.map(_.score).scanLeft(0.0)((remaining, score) => remaining + score).drop(1)
    var m1 = Set[Set[Rule]]()

    def enumOne(x: Set[Rule], sum: Double, index: Integer) {
      if (sum > th) {
        m1 += x
      }
      else {
        var j = index - 1
        while ((j > -1) && (th < (t(j) + sum))) {
          enumOne(x + sortedRulesByScore(j), sum + sortedRulesByScore(j).score, j)
          j -= 1
        }
      }
    }

    enumOne(Set[Rule](), 0, sortedRulesByScore.size)
    m1
  }
}
