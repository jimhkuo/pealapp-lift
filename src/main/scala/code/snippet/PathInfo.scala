package code.snippet

import net.liftweb._
import util.Helpers._

class PathInfo {
  lazy val path = System.getenv("PATH")

  def render = "* *" #> ("PATH: " + path)
}
