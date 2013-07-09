package code.snippet


import net.liftweb._
import java.util.Date
import util.Helpers._
import code.lib.DependencyFactory
import net.liftweb.util.Props

class Time {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  def render = "* *" #> ("The time now is " + dateString)
}
