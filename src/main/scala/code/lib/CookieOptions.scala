package code.lib

import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie

object CookieOptions {
  val vacuityCheck = "peal.vacuity.checks"

  def doVacuityChecks = {

    S.responseCookies.foreach("cookies " + println(_))

    val output = for (cookie <- S.responseCookies if cookie.name == vacuityCheck) yield {
      val cookieVal: String = cookie.value.openOr("")
      val out = cookieVal.toString match {
        case "true" => true
        case _ => false
      }

      println("get doVacuityChecks: " + out)
      out
    }

    if (output.isEmpty) false else output(0)
//    val boxedCookie = S.findCookie(vacuityCheck)
//    println(boxedCookie)
////    boxedCookie match {
////      case Full(cookie) => cookie.value match {
////        case Full(value) => value.toString match {
////          case "true" => true
////          case _ => println("value is \"\""); false
////        }
////        case _ =>  println("cookie is empty"); false
////      }
////      case _ =>  println("cookie is not set"); false
////    }
//    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
//    val out = cookieVal.toString match {
//      case "true" => true
//      case _ => false
//    }
//
//    println("get doVacuityChecks: " + out)
//    out
  }

  def doVacuityChecks_= (v: Boolean) = {
    val cookie = HTTPCookie(vacuityCheck, v.toString).setDomain(S.hostName).setPath("/").setMaxAge(3600)
    S.addCookie(cookie)
    println("doVacuityChecks_ setter, checking after set: " + doVacuityChecks)
  }
}
