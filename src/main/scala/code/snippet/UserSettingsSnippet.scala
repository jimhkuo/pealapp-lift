package code.snippet


import code.lib.CookieOptions
import net.liftweb.common.{Full, Empty}
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.{_Noop, RedirectTo}
import net.liftweb.http.{S, SHtml}
import net.liftweb._
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

object UserSettingsSnippet {
  val vacuityCheck = "peal.vacuity.checks"

  def render = {

    val boxedCookie = S.findCookie(vacuityCheck)
    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
    println("UserSettingsSnippet.findCookies: " + boxedCookie + " " + cookieVal)

    var vc = cookieVal match {
      case "true" => true
      case _ => false
    }

    def process(): JsCmd= {

      S.redirectTo("/", () => {
        val cookie = HTTPCookie(vacuityCheck, vc.toString).setMaxAge(3600)
        println("process() " + cookie)
        S.addCookie(cookie)
      })
    }

    "name=vc" #> (SHtml.checkbox(vc, vc= _, "id" -> "vc") ++ SHtml.hidden(process))
  }
}