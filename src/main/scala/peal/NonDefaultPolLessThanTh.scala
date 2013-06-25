package peal

import peal.synthesis.NonDefaultSet
import peal.domain.Pol
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Max, Plus, Min}


class NonDefaultPolLessThanTh(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = pol.operator match {
    case Min => {
      val rules = pol.rules.filter(th > _.score)
      rules.size match {
        case 0 => "false"
        case 1 => rules.map(_.q.name).mkString("")
        case _ => rules.map(_.q.name).mkString("(or ", " ", ")")
      }
    }
    case Plus | Max => new NonDefaultThLessThanPol(pol, th).notPhi
    case Mul => {
      //TODO do M2 stuff here
      throw new RuntimeException("wip")
    }
    case s => throw new RuntimeException("trying to synthesise with unsupported operator " + s + " in NonDefaultPolLessThanTh")
  }
}
