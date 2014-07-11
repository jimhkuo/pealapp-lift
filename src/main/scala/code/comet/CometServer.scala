package code
package comet

import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import peal.util.ConsoleLogger

object CometServer extends LiftActor with ListenerManager {

  private var msg : Any = ""

  override def lowPriority = {
    case s =>
      ConsoleLogger.log1("CometServer: " + s)
      msg = s
      updateListeners()
  }

  override protected def createUpdate = msg
}