package peal.runner.actor

import akka.actor.Actor
import peal.eagersynthesis.EagerSynthesiser
import java.io.File
import scala.sys.process._
import util.FileUtil

class EagerSynthesiserActor extends Actor {

  var synthesiser = new EagerSynthesiser()

  def receive = {
    case input: String =>
      val tmp = File.createTempFile("pealInput", "")
      FileUtil.writeToFile(tmp.getAbsolutePath, input)
      val synthesisedOutput = Seq("java", "-Xmx10240m", "-cp", "Peal.jar", "peal.eagersynthesis.EagerFileSynthesiser", tmp.getAbsolutePath).!!
      sender ! synthesisedOutput

    case KillSynthesiser =>
      val execTmp = File.createTempFile("kill", "")
      execTmp.setExecutable(true)
      val script = "foreach (`ps -A -f | grep EagerFileSynthesiser`)  {\n@a = split;\n$pid = $a[1];\n`kill -9 $pid`;}"
      FileUtil.writeToFile(execTmp.getAbsolutePath, script)
      Seq("perl", execTmp.getAbsolutePath).!!
  }
}
