package code.snippet

import code.lib.{DnOption, CookieOptions, VcOption}
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class LoadCookieSetting {

  def render: CssSel = {

    VcOption.set(CookieOptions.doVacuityChecks)
    DnOption.set(CookieOptions.displayFormat)

    "* *" #> ""
  }
}
