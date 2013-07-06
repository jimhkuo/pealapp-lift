package peal

import _root_.z3.scala.Z3Context
import peal.synthesis.NonDefaultSet
import peal.domain.{Rule, Pol}
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Max, Plus, Min}


class NonDefaultPolLessThanTh(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis(z3: Z3Context): String = pol.operator match {
    case Min => {
      val rules = pol.rules.filter(th > _.score)
      val z3Model = rules.size match {
        case 0 => z3.mkFalse()
        case 1 => z3.mkBoolConst(rules(0).q.name) //rules.map(_.q.name).mkString("")
        case _ => z3.mkOr(rules.map(r => z3.mkBoolConst(r.q.name)): _*) //rules.map(_.q.name).mkString("(or ", " ", ")")
      }

      z3Model.toString()
    }
    case Plus | Max => new NonDefaultThLessThanPol(pol, th).notPhi(z3)
    case Mul => {
      val m2 = enumTwoForMul()

      if (m2.nonEmpty) {
        val m2Sets = m2.map {
          s =>
            s.size match {
              case 1 => z3.mkBoolConst(s.toSeq(0).q.name) //s.map(_.q.name).mkString(" ")
              case _ => z3.mkAnd(s.map(r => z3.mkBoolConst(r.q.name)).toSeq: _*) //s.map(_.q.name).mkString("(and ", " ", ")")
            }
        }

        val z3Model = m2Sets.size match {
          case 1 => m2Sets.toSeq(0)
          case _ => z3.mkOr(m2Sets.toSeq: _*) //m2Sets.mkString("(or ", " ", ")")
        }
        z3Model.toString
      }
      else {
        z3.mkFalse().toString()
      }
    }
    case s => throw new RuntimeException("trying to synthesise with unsupported operator " + s + " in NonDefaultPolLessThanTh")
  }

  def enumTwoForMul(): Set[Set[Rule]] = {
    val sortedRulesByScoreDesc = pol.rules.sortWith(_.score > _.score)
    val t = sortedRulesByScoreDesc.map(_.score).scanLeft(1.0)((remaining, score) => remaining * score).drop(1)
    var m2 = Set[Set[Rule]]()

    def enumTwo(x: Set[Rule], sum: Double, index: Integer) {
      if (sum <= th) {
        m2 += x
      }
      else {
        var j = index - 1
        while ((j > -1) && ((t(j) * sum) <= th)) {
          enumTwo(x + sortedRulesByScoreDesc(j), sum * sortedRulesByScoreDesc(j).score, j)
          j -= 1
        }
      }
    }

    enumTwo(Set[Rule](), 1.0, sortedRulesByScoreDesc.size)
    m2
  }
}
