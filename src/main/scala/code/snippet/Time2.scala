package code.snippet

import java.util.Date

import code.lib.{VCOption, CookieOptions, DependencyFactory}
import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class Time2 {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  val vacuityCheck = "peal.vacuity.checks"

  def render: CssSel = {

    //This is working
    val boxedCookie = S.findCookie(vacuityCheck)
    val cookieVal = boxedCookie.map(_.value.openOr("")).openOr("") match {
      case "true" => true
      case _ => false
    }
    println("findCookies: " + boxedCookie + " " + cookieVal)

    VCOption.set(cookieVal)

    "* *" #> ("The time now is " + dateString)
  }
}
