package code.lib

import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import peal.util.ConsoleLogger

//This class needs to be called in the request cycle
//i.e. in snippets, calling it from within a comet won't work
object CookieOptions {

  //Using a common cookie name (without the port number) will make all PEALT hosted on various urls to share the cookie
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
    val cookie = HTTPCookie(vacuityCheck, v.toString)//setting the hostName breaks it .setDomain(S.hostName)
    S.addCookie(cookie)
    ConsoleLogger.log("cookie added: " + cookie)
    ConsoleLogger.log("doVacuityChecks_ setter, checking after set: " + doVacuityChecks)
  }
}
