package peal

import peal.synthesis.NonDefaultSet
import peal.domain.Pol

class NonDefaultPolLessThanThOpMin(pol: Pol, th: Double) extends NonDefaultSet {
  def synthesis: String = pol.rules.filter(th > _.score).size match {
    case 0 => "false"
    case 1 => pol.rules.filter(th > _.score).map(_.q.name).mkString("")
    case _ => pol.rules.filter(th > _.score).map(_.q.name).mkString("(or ", " ", ")")
    //    case _ => {
    //      //      pol.rules.filter(th > _.score).map(_.q.name).mkString(" ")
    //      val ss = for (p <- pol.rules.filter(th > _.score)) yield (p.q.name)
    //
    //      ss.mkString(" ")
    //    }
  }
}
