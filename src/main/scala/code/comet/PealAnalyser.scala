package code
package comet

import net.liftweb._
import http._
import actor._

object PealAnalyser extends LiftActor with ListenerManager {
  private var msgs = Vector[String]()

  def createUpdate = msgs

  //action when receiving a message
  override def lowPriority = {
    case s: String => msgs :+= s; updateListeners()
  }
}
 
