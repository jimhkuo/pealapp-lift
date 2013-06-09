package peal

import peal.synthesis.DefaultSet
import peal.domain.Pol
import scala.collection.JavaConversions._


class DefaultPolLessThanTh(pol: Pol, th: Double) extends DefaultSet {
  def synthesis: String = pol.defaultScore match {
    case s if s > th => "false"
    case _ if pol.rules.size > 1 => pol.rules.map("(not " + _.q.name + ")").mkString("(and ", " ", ")")
    case _ => pol.rules.map("(not " + _.q.name + ")").mkString(" ")
  }
}
