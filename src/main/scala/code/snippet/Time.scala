package code.snippet

import java.util.Date

import code.lib.DependencyFactory
import net.liftweb.common.{Empty, Full}
import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class Time {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  val vacuityCheck = "pealvacuitychecks"

  def render: CssSel = {
    S.addCookie(HTTPCookie("city",Full("true"),Full(S.hostName),Full("/"),Empty,Empty,Empty))
    println("***** Set doVacuityChecks")
    val boxedCookie = S.findCookie("city")
    println("immediate after set " + boxedCookie)

    "* *" #> ("The time now is " + dateString)
  }
}
