package code.snippet

import net.liftweb._
import java.util.Date
import util.Helpers._
import code.lib.DependencyFactory
import net.liftweb.util.Props

class Info {
  lazy val z3Location = Props.get("z3.location").get

  def render = "* *" #> ("z3 is installed at " + z3Location)
}
