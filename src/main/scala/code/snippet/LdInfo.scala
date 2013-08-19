package code.snippet

import net.liftweb._
import util.Helpers._

class LdInfo {
  lazy val ldPath = System.getenv("LD_LIBRARY_PATH")

  def render = "* *" #> ("LD: " + ldPath)
}
