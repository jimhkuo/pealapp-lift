package code
package comet

import net.liftweb._
import http._
import actor._

object ChatServer extends LiftActor with ListenerManager {
  private var msgs = Vector[String]()

  def createUpdate = msgs

  override def lowPriority = {
    case s: String => msgs :+= s; updateListeners()
  }
}
 
