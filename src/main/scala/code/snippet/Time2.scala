package code.snippet

import java.util.Date

import code.lib.{CookieOptions, DependencyFactory}
import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class Time2 {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  val vacuityCheck = "pealvacuitychecks"

  def render: CssSel = {

    //This is working
    val boxedCookie = S.findCookie(vacuityCheck)
    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
    println("findCookies: " + boxedCookie + " " + cookieVal)

    println("time2: " + CookieOptions.doVacuityChecks)

    "* *" #> ("The time now is " + dateString)
  }
}
