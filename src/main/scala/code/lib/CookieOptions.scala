package code.lib

import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import peal.util.ConsoleLogger

//This class needs to be called in the request cycle
//i.e. in snippets, calling it from within a comet won't work
object CookieOptions {

  //Using a different cookie name (with the port number) to separate out the use of cookie for different PEALT URLs
  val vacuityCheckCookie = "peal.vacuity.checks" + pullHostInfo
  val displayFormatCookie = "peal.display.format" + pullHostInfo


  private def pullHostInfo: String = {
    S.hostAndPath.replaceAll("/", "").split(":").mkString
  }

  def doVacuityChecks = {
    //TODO clean this up
    val boxedCookie = S.findCookie(vacuityCheckCookie)
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
    val cookie = HTTPCookie(vacuityCheckCookie, v.toString).setMaxAge(864000) //setting the hostName breaks it .setDomain(S.hostName)
    S.addCookie(cookie)
    ConsoleLogger.log("cookie added: " + cookie)
    ConsoleLogger.log("doVacuityChecks_ setter, checking after set: " + doVacuityChecks)
  }

  def displayFormat = {
    val boxedCookie = S.findCookie(displayFormatCookie)
    ConsoleLogger.log("CookieOptions.findCookies: " + boxedCookie)
    val cookieVal: String = boxedCookie.map(_.value.openOr("Both")).openOr("Both")
    ConsoleLogger.log("CookieOptions.findCookiesVal: " + cookieVal)

    val out = cookieVal match {
      case "DecimalFormat" => DecimalFormat
      case "RationalFormat" => RationalFormat
      case _ => Both
    }
    ConsoleLogger.log("CookieOptions.findCookies.result: " + out)
    out
  }

  def displayFormat_=(f: DisplayFormat) = {
    ConsoleLogger.log("saving: " + f)
    val cookie = HTTPCookie(displayFormatCookie, f.toString).setMaxAge(864000) //setting the hostName breaks it .setDomain(S.hostName)
    S.addCookie(cookie)
    ConsoleLogger.log("cookie added: " + cookie)
    ConsoleLogger.log("doVacuityChecks_ setter, checking after set: " + displayFormat)
  }
}
