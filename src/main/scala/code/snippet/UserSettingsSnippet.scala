package code.snippet


import code.lib.CookieOptions
import net.liftweb.http.js.JsCmd
import net.liftweb.http.{S, SHtml}
import net.liftweb.util.Helpers._

object UserSettingsSnippet {
  val vacuityCheck = "peal.vacuity.checks"

  def render = {

    var vc = CookieOptions.doVacuityChecks

    def process(): JsCmd= {

      S.redirectTo("/done", () => {
        CookieOptions.doVacuityChecks = vc
      })
    }

    "name=vc" #> (SHtml.checkbox(vc, vc= _, "id" -> "vc") ++ SHtml.hidden(process))
  }
}