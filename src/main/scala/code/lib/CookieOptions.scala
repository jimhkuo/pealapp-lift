package code.lib

import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import peal.util.ConsoleLogger

object CookieOptions {
  val vacuityCheck = "peal.vacuity.checks"

  def doVacuityChecks = {

    val boxedCookie = S.findCookie(vacuityCheck)
    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
    ConsoleLogger.log("CookieOptions.findCookies: " + boxedCookie + " " + cookieVal)
    val out = cookieVal.toString match {
      case "true" => true
      case _ => false
    }

    ConsoleLogger.log("CookieOptions.findCookies.result: " + out)
    out

  }

  def doVacuityChecks_=(v: Boolean) = {
    val cookie = HTTPCookie(vacuityCheck, v.toString).setMaxAge(3600)
    S.addCookie(cookie)
    ConsoleLogger.log("cookie added: " + cookie)
    ConsoleLogger.log("doVacuityChecks_ setter, checking after set: " + doVacuityChecks)
  }
}
