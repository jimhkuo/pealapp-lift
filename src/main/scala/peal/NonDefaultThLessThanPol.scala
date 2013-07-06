package peal

import _root_.z3.scala.Z3Context
import peal.synthesis.NonDefaultSet
import peal.domain.{Rule, Pol}
import scala._
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Min, Max, Plus}
import scala.collection.mutable.ListBuffer


class NonDefaultThLessThanPol(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis(z3:Z3Context): String = pol.operator match {
    case Plus => {
      val m1 = enumOneForPlus()

      if (m1.nonEmpty) {
        val m1Sets = m1.map {
          s =>
            s.size match {
              case 1 => z3.mkBoolConst(s(0).q.name) //s.map(_.q.name).mkString(" ")
              case _ => z3.mkAnd(s.map(r => z3.mkBoolConst(r.q.name)).toSeq:_*)//s.map(_.q.name).mkString("(and ", " ", ")")
            }
        }

        val z3Model = m1Sets.size match {
          case 1 => m1Sets(0)//m1Sets.mkString(" ")
          case _ => z3.mkOr(m1Sets.toSeq:_*)//m1Sets.mkString("(or ", " ", ")")
        }
        z3Model.toString()
      }
      else {
        z3.mkFalse().toString()
      }
    }
    case Max => {
      val rules = pol.rules.filter(th < _.score)
      rules.size match {
        case 0 => "false"
        case 1 => rules.map(_.q.name).mkString("")
        case _ => rules.map(_.q.name).mkString("(or ", " ", ")")
      }
    }
    case Min | Mul => new NonDefaultPolLessThanTh(pol, th).notPhi(z3)
    case s => throw new RuntimeException("trying to synthesise with unsupported operator " + s + " in NonDefaultThLessThanPol")
  }

  def enumOneForPlus(): ListBuffer[List[Rule]] = {
    val sortedRulesByScore = pol.rules.sortBy(_.score)
    val t = sortedRulesByScore.map(_.score).scanLeft(0.0)((remaining, score) => remaining + score).drop(1)
    var m1 = ListBuffer[List[Rule]]()

    def enumOne(x: List[Rule], sum: Double, index: Integer) {
      if (sum > th) {
        m1 += x
      }
      else {
        var j = index - 1
        while ((j > -1) && (th < (t(j) + sum))) {
          enumOne(x :+ sortedRulesByScore(j), sum + sortedRulesByScore(j).score, j)
          j -= 1
        }
      }
    }

    enumOne(List[Rule](), 0, sortedRulesByScore.size)
    m1
  }
}
