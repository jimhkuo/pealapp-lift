package code.snippet


import code.lib._
import net.liftweb.common.{Empty, Full}
import net.liftweb.http.js.JsCmd
import net.liftweb.http.{S, SHtml}
import net.liftweb.util.Helpers._

object UserSettingsSnippet {
  val displayFormatsMap = Map(RationalFormat -> "the original formats as returned by Z3 (can be rational OR decimal)",
    DecimalFormat -> "the decimal formats converted by PEALT",
    Both -> "both rational (if available) AND decimal formats")


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
      "name=dn" #> (SHtml.selectObj(displayFormatsMap.toSeq, Full(dn), (f: DisplayFormat) => dn = f) ++ SHtml.hidden(process))
  }
}