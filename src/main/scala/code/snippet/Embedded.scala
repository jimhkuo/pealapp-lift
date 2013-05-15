package code
package snippet

import net.liftweb._
import http._
import util.Helpers._

object Embedded {
  def from = "*" #> S.location.map(_.name)
}
