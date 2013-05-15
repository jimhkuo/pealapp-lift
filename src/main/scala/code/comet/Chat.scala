package code
package comet

import net.liftweb._
import http._
import util._

class Chat extends CometActor with CometListener {
  private var msgs: Vector[String] = Vector()

  def registerWith = ChatServer

  override def lowPriority = {
    case v: Vector[String] => msgs = v; reRender()
  }

  def render = "li *" #> msgs & ClearClearable
}