package code.snippet


import code.lib.CookieOptions
import net.liftweb.common.{Full, Empty}
import net.liftweb.http.{S, SHtml}
import net.liftweb._
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class UserSettingsSnippet {

  val vacuityCheck = "pealvacuitychecks"

  def render= {
//    CookieOptions.doVacuityChecks = !CookieOptions.doVacuityChecks
//    val cookie = HTTPCookie(vacuityCheck, true.toString).setDomain(S.hostName).setPath("/")
    S.addCookie(HTTPCookie(vacuityCheck,Full("true"),Full(S.hostName),Full("/"),Empty,Empty,Empty))
//    S.addCookie(cookie)
    println("***** Set doVacuityChecks")
    val boxedCookie = S.findCookie(vacuityCheck)
    println("immediate after set " + boxedCookie)

    <div>{CookieOptions.doVacuityChecks}</div>
  }
}
