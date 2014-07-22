package code.snippet

import java.util.Date

import code.lib.DependencyFactory
import net.liftweb.http.S
import net.liftweb.http.provider.HTTPCookie
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class Time2 {
  lazy val dateString = DependencyFactory.inject[Date].openOrThrowException("wantcontent").toString

  val vacuityCheck = "pealvacuitychecks"

  def render: CssSel = {

    //    println("in time: " + CookieOptions.doVacuityChecks)

    S.responseCookies.foreach(c => "cookies: " + println(c))

    val output = for (cookie <- S.responseCookies if cookie.name == vacuityCheck) yield {
      val cookieVal: String = cookie.value.openOr("")
      val out = cookieVal.toString match {
        case "true" => true
        case _ => false
      }

      out
    }

    val out = if (output.isEmpty) false else output(0)
    println("Get: " + out)

    val boxedCookie = S.findCookie(vacuityCheck)
    val cookieVal: String = boxedCookie.map(_.value.openOr("")).openOr("")
    println("findCookies: " + boxedCookie + " " + cookieVal)

    "* *" #> ("The time now is " + dateString)
  }
}
