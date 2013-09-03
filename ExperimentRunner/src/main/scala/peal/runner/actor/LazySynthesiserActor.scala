package peal.runner.actor

import akka.actor.Actor
import java.io.File
import util.FileUtil
import scala.sys.process._


class LazySynthesiserActor() extends Actor {

  def receive = {
    case input: String =>
      val tmp = File.createTempFile("lazyPealInput", "")
      FileUtil.writeToFile(tmp.getAbsolutePath, input)
      val synthesisedOutput = Seq("java", "-Xmx10240m", "-Xss1m", "-cp", "Peal.jar", "peal.lazysynthesis.LazyFileSynthesiser", tmp.getAbsolutePath).!!
      sender ! synthesisedOutput
  }
}
