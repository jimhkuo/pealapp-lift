package code.snippet


import net.liftweb.http.js.JsCmd
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.http.{S, SHtml}
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

      S.redirectTo("/done", () => {
        S.deleteCookie(vacuityCheck)
        val cookie = HTTPCookie(vacuityCheck, vc.toString)
        println("process() " + cookie)
        S.addCookie(cookie)
      })
    }

    "name=vc" #> (SHtml.checkbox(vc, vc= _, "id" -> "vc") ++ SHtml.hidden(process))
  }
}