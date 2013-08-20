package code.snippet

import net.liftweb._
import util.Helpers._

class PathInfo {
//  lazy val z3Location = Props.get("z3.location").get

  lazy val path = System.getenv("PATH")

  def render = "* *" #> ("PATH: " + path)
}
