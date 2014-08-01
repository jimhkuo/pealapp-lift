package code.snippet


import code.lib._
import net.liftweb.common.{Empty, Full}
import net.liftweb.http.js.JsCmd
import net.liftweb.http.{S, SHtml}
import net.liftweb.util.Helpers._

object UserSettingsSnippet {
  val displayFormatsMap = Map(RationalFormat -> "the original formats as returned by Z3 (can be rational OR decimal)",
    DecimalFormat -> "the decimal format (calculated by PEALT from raw Z3 data)",
    Both -> "both of the above")


  def render = {

    var vc = CookieOptions.doVacuityChecks
    var df = CookieOptions.displayFormat

    def process(): JsCmd = {

      S.redirectTo("/done", () => {
        CookieOptions.doVacuityChecks = vc
        CookieOptions.displayFormat = df
      })
    }

    //The order of elements matter here!! It has to match the ordering of tags in settings.html
    "name=vc" #> SHtml.checkbox(vc, vc = _, "id" -> "vc") &
      "name=df" #> (SHtml.selectObj(displayFormatsMap.toSeq, Full(df), (f: DisplayFormat) => df = f) ++ SHtml.hidden(process))
  }
}