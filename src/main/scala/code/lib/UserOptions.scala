package code.lib

import java.io.Serializable

import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie

object UserOptions {
  val vacuityCheck = "peal.vacuity.checks"

  def doVacuityChecks = {
    val cookieVal: Serializable = S.findCookie(vacuityCheck).map(_.value) openOr ""
    val out = cookieVal.toString match {
      case "true" => true
      case _ => false
    }

    println("get doVacuityChecks: " + out)
    out
  }

  def doVacuityChecks_= (v: Boolean) = {
    val cookie = HTTPCookie(vacuityCheck, v.toString)
    println("set doVacuityChecks " + v)

    S.addCookie(cookie)
  }
}
