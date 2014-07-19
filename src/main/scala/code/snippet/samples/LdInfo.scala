package code.snippet.samples

import net.liftweb._
import net.liftweb.util.Helpers._

class LdInfo {
  lazy val ldPath = System.getenv("LD_LIBRARY_PATH")

  def render = "* *" #> ("LD: " + ldPath)
}
