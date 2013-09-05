package peal.runner.actor

import akka.actor.Actor
import java.io.File
import scala.sys.process._
import util.FileUtil

class ProcessKillerActor extends Actor {

  def receive = {
    case inputFile: File =>
      val killScript = File.createTempFile("killZ3Process", "")
      killScript.setExecutable(true)
      val script = "foreach (`ps -A -f | grep " + inputFile.getAbsoluteFile + " | grep nw`)  {\n@a = split;\n$pid = $a[1];\n$exists = kill 0, $pid;\n`kill -9 $pid` if ($exists);}"
      FileUtil.writeToFile(killScript.getAbsolutePath, script)
      Seq("perl", killScript.getAbsolutePath).!!
      killScript.delete()
      inputFile.delete()
  }
}