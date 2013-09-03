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

    //      sender ! synthesiser.generate(input)

    //case Kill =>
    //   use this perl script to get process id or simply kill it
    //      foreach (`ps -A -f | grep EagerFileSynthesiser`)  {
    //        @a = split;
    //        $pid = $a[1];
    //        print $pid;
    //      `kill -9 $pid`;
    //      }
    //    on mac
    //      foreach (`ps aux | grep EagerFileSynthesiser`) {
    //        @a = split;
    //        $pid = $a[1];
    //        print $pid;
    //      `kill -9 $pid`;
    //      }
    // kill the process started above, use killall -u hk2109 -c procname
  }
}
