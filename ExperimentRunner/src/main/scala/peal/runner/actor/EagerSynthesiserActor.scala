package peal.runner.actor

import akka.actor.Actor
import peal.eagersynthesis.EagerSynthesiser
import java.io.File
import scala.sys.process._
import util.FileUtil

class EagerSynthesiserActor extends Actor {

  var synthesiser = new EagerSynthesiser()

  var tmp : File = null

  def receive = {
    case input: String =>
      tmp = File.createTempFile("pealInput", "")
      FileUtil.writeToFile(tmp.getAbsolutePath, input)
      val synthesisedOutput = Seq("java", "-Xmx10240m", "-Xss1m", "-cp", "Peal.jar", "peal.eagersynthesis.EagerFileSynthesiser", tmp.getAbsolutePath).!!
      sender ! synthesisedOutput

    case KillSynthesiser =>
      val execTmp = File.createTempFile("kill", "")
      execTmp.setExecutable(true)
      val script = "foreach (`ps -A -f | grep " + tmp.getAbsoluteFile + "`)  {\n@a = split;\n$pid = $a[1];\n`kill -9 $pid`;}"
      FileUtil.writeToFile(execTmp.getAbsolutePath, script)
      Seq("perl", execTmp.getAbsolutePath).!!
  }
}
