package code.snippet


import net.liftweb._
import java.util.Date
import util.Helpers._
import common._
import code.lib.DependencyFactory

class Time {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  def render = "* *" #> date.map(_.toString)
}
