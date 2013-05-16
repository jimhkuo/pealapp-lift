package code
package comet

import net.liftweb._
import http._
import actor._

object PealAnalyser extends LiftActor with ListenerManager {
  private var msgs = Vector[String]()

  //defines the message to send when we update listeners
  def createUpdate = msgs

  //action when receiving a message
  override def lowPriority = {
    case s: String => msgs :+= s; updateListeners()
  }
}
 
