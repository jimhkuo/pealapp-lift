package code.snippet

import java.util.Date

import code.lib.{CookieOptions, DependencyFactory}
import net.liftweb.common.{Empty, Full}
import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class Time {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  val vacuityCheck = "pealvacuitychecks"

  def render: CssSel = {

    println("in time: " + CookieOptions.doVacuityChecks)

    "* *" #> ("The time now is " + dateString)
  }
}
