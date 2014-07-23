package code.snippet

import code.lib.VCOption
import net.liftweb.http.js.JsCmd
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.http.{S, SHtml}
import net.liftweb.util.Helpers._

object ForwardToRoot {
  val vacuityCheck = "peal.vacuity.checks"

  def render = {

    S.redirectTo("/")
  }
}