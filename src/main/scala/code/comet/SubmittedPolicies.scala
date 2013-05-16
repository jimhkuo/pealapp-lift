package code
package comet

import net.liftweb._
import http._
import util._

class SubmittedPolicies extends CometActor with CometListener {
  private var msgs: Vector[String] = Vector()

  //this is where registration occurs
  def registerWith = PealAnalyser

  override def lowPriority = {
    case v: Vector[String] => msgs = v; reRender()
  }

  def render = "li *" #> msgs & ClearClearable
}