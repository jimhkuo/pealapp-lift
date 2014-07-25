package code.snippet

import net.liftweb.http.S

object ForwardToRoot {
  def render = S.redirectTo("/")
}