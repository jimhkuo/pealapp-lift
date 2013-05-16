package code.snippet


import net.liftweb._
import java.util.Date
import util.Helpers._
import code.lib.DependencyFactory

class Time {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  def render = "* *" #> ("The time now is " + dateString)
}
