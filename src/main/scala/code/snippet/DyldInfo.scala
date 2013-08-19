package code.snippet

import net.liftweb._
import util.Helpers._

class DyldInfo {
  lazy val dyldPath = System.getenv("DYLD_LIBRARY_PATH")

  def render = "* *" #> ("DYLD: " + dyldPath)
}
