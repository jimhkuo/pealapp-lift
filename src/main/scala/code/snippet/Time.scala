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

    val cookie = HTTPCookie(vacuityCheck, "abc").setMaxAge(3600)//.setDomain(S.hostName).setPath("/debug2")
    S.addCookie(cookie)
    println("Set: " + cookie)

    "* *" #> ("The time now is " + dateString)
  }
}
