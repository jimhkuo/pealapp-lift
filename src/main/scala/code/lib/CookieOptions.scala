package code.lib

import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie

object CookieOptions {
  val vacuityCheck = "peal.vacuity.checks"

  def doVacuityChecks = {

    val boxedCookie = S.findCookie(vacuityCheck)
    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
    println("In CookieOptions, foundCookies: " + boxedCookie + " " + cookieVal)
    val out = cookieVal.toString match {
      case "true" => true
      case _ => false
    }

    println("get doVacuityChecks: " + out)
    out

  }

  def doVacuityChecks_=(v: Boolean) = {
    val cookie = HTTPCookie(vacuityCheck, v.toString).setMaxAge(3600)
    S.addCookie(cookie)
    println("cookie added: " + cookie)
    println("doVacuityChecks_ setter, checking after set: " + doVacuityChecks)
  }
}
