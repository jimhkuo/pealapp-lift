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

  def render = {
    val checks = CookieOptions.doVacuityChecks


    <div>
      {SHtml.checkbox(checks, (v) => CookieOptions.doVacuityChecks = v)}
    </div>
  }
}
