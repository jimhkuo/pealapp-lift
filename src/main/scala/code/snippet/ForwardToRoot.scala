package code.snippet

import net.liftweb.http.S

object ForwardToRoot {
  val vacuityCheck = "peal.vacuity.checks"

  def render = {

    S.redirectTo("/")
  }
}