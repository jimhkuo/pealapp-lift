package code.lib

import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import peal.util.ConsoleLogger

//This class needs to be called in the request cycle
//i.e. in snippets, calling it from within a comet won't work
object CookieOptions {

  val vacuityCheckCookie = "peal.vacuity.checks" + pullHostInfo
  val displayFormatCookie = "peal.display.format" + pullHostInfo


  private def pullHostInfo: String = {
    S.hostAndPath.replaceAll("/", "").split(":").mkString
  }

  private def pullCookieValue(cookieName: String) = {
    val boxedCookie = S.findCookie(cookieName)
    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
    ConsoleLogger.log("CookieOptions.findCookies: " + boxedCookie + " " + cookieVal)
    cookieVal
  }

  def doVacuityChecks = pullCookieValue(vacuityCheckCookie) match {
    case "true" => true
    case _ => false
  }

  def doVacuityChecks_=(v: Boolean) = {
    val cookie = HTTPCookie(vacuityCheckCookie, v.toString).setMaxAge(864000) //setting the hostName breaks it .setDomain(S.hostName)
    S.addCookie(cookie)
    ConsoleLogger.log("cookie added: " + cookie)
    ConsoleLogger.log("doVacuityChecks_ setter, checking after set: " + doVacuityChecks)
  }

  def displayFormat = pullCookieValue(displayFormatCookie) match {
    case "DecimalFormat" => DecimalFormat
    case "RationalFormat" => RationalFormat
    case _ => Both
  }

  def displayFormat_=(f: DisplayFormat) = {
    ConsoleLogger.log("saving: " + f)
    val cookie = HTTPCookie(displayFormatCookie, f.toString).setMaxAge(864000) //setting the hostName breaks it .setDomain(S.hostName)
    S.addCookie(cookie)
    ConsoleLogger.log("cookie added: " + cookie)
    ConsoleLogger.log("doVacuityChecks_ setter, checking after set: " + displayFormat)
  }
}
