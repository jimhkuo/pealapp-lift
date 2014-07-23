package code.snippet

import code.lib.VCOption
import net.liftweb.http.S
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._
import peal.util.ConsoleLogger

class LoadCookieSetting {

  val vacuityCheck = "peal.vacuity.checks"

  def render: CssSel = {

    val boxedCookie = S.findCookie(vacuityCheck)
    val cookieVal = boxedCookie.map(_.value.openOr("")).openOr("") match {
      case "true" => true
      case _ => false
    }
    ConsoleLogger.log("LoadCookieSetting.findCookies: " + boxedCookie + " " + cookieVal)

    VCOption.set(cookieVal)

    "* *" #> ""
  }
}
