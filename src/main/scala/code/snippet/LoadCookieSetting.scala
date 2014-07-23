package code.snippet

import code.lib.{CookieOptions, VCOption}
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class LoadCookieSetting {

  val vacuityCheck = "peal.vacuity.checks"

  def render: CssSel = {

    VCOption.set(CookieOptions.doVacuityChecks)

    "* *" #> ""
  }
}
