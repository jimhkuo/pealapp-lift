package code
package comet

import code.lib.UploadFile
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import peal.util.ConsoleLogger

object CometServer extends LiftActor with ListenerManager {

  private var msg = ""

  override def lowPriority = {
    case UploadFile(s) =>
      ConsoleLogger.log1("CometServer: " + s)
      msg = s
      updateListeners()
  }

  override protected def createUpdate = msg
}