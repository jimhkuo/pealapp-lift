package peal.runner.actor

import akka.actor.Actor
import peal.eagersynthesis.EagerSynthesiser

class EagerSynthesiserActor extends Actor {

  def receive = {
    case input: String =>

      //TODO launch individual process
      //    val solverProcess = Seq("java", "-Xss1m", "-Xms512m", "-Xmx1024m", "-cp", "build/main:lib/compilation/*:lib/deployment/*", clazz.getName.dropRight(1), runAlternativeMode.toString, gameFileName, outputFileName)
      // need to output the process id to a file
      //      solverProcess.!!

      //TODO
      //put the following in a class
      sender ! new EagerSynthesiser(input).generate()

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
}
