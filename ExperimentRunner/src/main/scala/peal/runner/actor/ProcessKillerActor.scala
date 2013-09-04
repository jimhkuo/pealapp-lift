package peal.runner.actor

import akka.actor.Actor
import java.io.File
import scala.sys.process._
import util.FileUtil

class ProcessKillerActor extends Actor {

  //TODO may modify this to kill z3 process
  def receive = {
    case file: File =>
      val execTmp = File.createTempFile("kill", "")
      execTmp.setExecutable(true)
      val script = "foreach (`ps -A -f | grep " + file.getAbsoluteFile + " | grep Peal.jar`)  {\n@a = split;\n$pid = $a[1];\n`kill -9 $pid`;}"
      FileUtil.writeToFile(execTmp.getAbsolutePath, script)
      Seq("perl", execTmp.getAbsolutePath).!!
  }
}