package code.snippet.samples

import java.util.Date

import code.lib.DependencyFactory
import net.liftweb._
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class Time {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  def render: CssSel = "* *" #> ("The time now is " + dateString)
}
