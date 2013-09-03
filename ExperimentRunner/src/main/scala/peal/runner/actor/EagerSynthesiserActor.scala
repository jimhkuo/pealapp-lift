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

      //      //TODO launch individual process
      //      val tmp = File.createTempFile("z3file", "")
      //      FileUtil.writeToFile(tmp.getAbsolutePath, input)
      //      val synthesisedOutput = Seq("java", "-Xmx1024m", "-cp", "/Users/jkuo/Peal.jar", "peal.eagersynthesis.EagerSynthesiser", tmp.getAbsolutePath).!!
      //      sender ! synthesisedOutput

      sender ! synthesiser.generate(input)

    //case Kill =>
    //   use this perl script to get process id or simply kill it
    //      foreach (`ps -A -f | grep BatchExperimentRunner`)  {
    //        @a = split;
    //        $pid = $a[1];
    //        print $pid;
    //      `kill -9 $pid`;
    //      }
    //    on mac
    //      foreach (`ps aux | grep java`) {
    //        @a = split;
    //        $pid = $a[1];
    //        print $pid;
    //      `kill -9 $pid`;
    //      }
    // kill the process started above, use killall -u hk2109 -c procname
  }

  override def postStop() {
    synthesiser.cleanup()
    synthesiser = null
    System.gc()
  }
}
