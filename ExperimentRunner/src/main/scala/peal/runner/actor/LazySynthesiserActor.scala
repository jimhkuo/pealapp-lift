package peal.runner.actor

import akka.actor.Actor
import java.io.File
import scala.sys.process._


class LazySynthesiserActor() extends Actor {

  def receive = {
    case tmp: File =>
      val synthesisedOutput = Seq("java", "-Xmx10240m", "-Xss32m", "-cp", "./Peal.jar", "peal.lazysynthesis.LazyFileSynthesiser", tmp.getAbsolutePath).!!
      sender ! synthesisedOutput
  }
}
