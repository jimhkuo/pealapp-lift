package code.snippet.samples

import net.liftweb._
import net.liftweb.util.Helpers._

class DyldInfo {
  lazy val dyldPath = System.getenv("DYLD_LIBRARY_PATH")

  def render = "* *" #> ("DYLD: " + dyldPath)
}
