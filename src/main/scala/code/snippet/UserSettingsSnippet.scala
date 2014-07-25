package code.snippet


import code.lib.CookieOptions
import net.liftweb.common.{Empty, Full}
import net.liftweb.http.js.JsCmd
import net.liftweb.http.{S, SHtml}
import net.liftweb.util.Helpers._

object UserSettingsSnippet {
  val vacuityCheck = "peal.vacuity.checks"

  def render = {

    var vc = CookieOptions.doVacuityChecks
    var dn = CookieOptions.displayFormat

    def process(): JsCmd = {

      S.redirectTo("/done", () => {
        CookieOptions.doVacuityChecks = vc
        CookieOptions.displayFormat = dn
      })
    }

    //The order of elements matter here!!
    "name=vc" #> SHtml.checkbox(vc, vc = _, "id" -> "vc") &
      "name=dn" #> (SHtml.select(CookieOptions.displayFormatsMap.toSeq, Full(dn), (s) => {
        println("s is " + s); dn = s
      }) ++ SHtml.hidden(process))
  }
}