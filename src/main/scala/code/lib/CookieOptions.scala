package code.lib

import java.io.Serializable

import net.liftweb.common.{Full, Box}
import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie

object CookieOptions {
  val vacuityCheck = "peal.vacuity.checks"

  def doVacuityChecks = {
    val boxedCookie = S.findCookie(vacuityCheck)
    println(boxedCookie)
//    boxedCookie match {
//      case Full(cookie) => cookie.value match {
//        case Full(value) => value.toString match {
//          case "true" => true
//          case _ => println("value is \"\""); false
//        }
//        case _ =>  println("cookie is empty"); false
//      }
//      case _ =>  println("cookie is not set"); false
//    }
    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
    val out = cookieVal.toString match {
      case "true" => true
      case _ => false
    }

    println("get doVacuityChecks: " + out)
    out
  }

  def doVacuityChecks_= (v: Boolean) = {
    val cookie = HTTPCookie(vacuityCheck, v.toString).setDomain(S.hostName).setPath("/")
    S.addCookie(cookie)
    println("***** Set doVacuityChecks " + v)
    val boxedCookie = S.findCookie(vacuityCheck)
    println("immediate after set " + boxedCookie)

    println(doVacuityChecks)
  }
}
