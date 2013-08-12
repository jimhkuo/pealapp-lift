package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Props, ActorSystem}
import peal.runner.actor._
import java.util.concurrent.TimeoutException
import z3.scala.Z3Context

class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0, var isSameOutput: Boolean = false, var model1Result: Map[String, String] = Map(), var model2Result: Map[String, String] = Map(), var pealInput: String = "") {

  private def milliTime(timeInNano: Long) = timeInNano match {
    case -1 => "TimeOut"
    case -2 => "NotRun"
    case _ =>  "%.2f".format(timeInNano.toDouble / 1000000)
  }

  override def toString = milliTime(modelGeneration) + "," + milliTime(eagerSynthesis) + "," + milliTime(eagerZ3) + "," + milliTime(lazySynthesis) + "," + milliTime(lazyZ3) + "," + isSameOutput + "," + model1Result + "," + model2Result + "," + pealInput
}

class ExperimentRunner(z3: Z3Context, duration: Long) {
  implicit val system = ActorSystem("exp-runner")


  def run(params: String, z3Path: String): TimingOutput = {
    val output = new TimingOutput()

    try {
      implicit val timeout = Timeout(duration, MILLISECONDS)

      val generatorRunner = system.actorOf(Props(new ModelGeneratorActor(params)))
      var start = System.nanoTime()
      val modelFuture = generatorRunner ? Run
      val model = Await.result(modelFuture, timeout.duration).asInstanceOf[String]
      var lapsedTime = System.nanoTime() - start
      output.modelGeneration = lapsedTime

      try {
        val z3InputGenerator = system.actorOf(Props(new Z3InputGeneratorActor(z3)))
        start = System.nanoTime()
        val inputFuture = z3InputGenerator ? model
        val input = Await.result(inputFuture, timeout.duration)
        lapsedTime = System.nanoTime() - start
        output.eagerSynthesis = lapsedTime

        try {
          val z3Caller = system.actorOf(Props(new Z3CallerActor(z3Path)))
          start = System.nanoTime()
          val resultFuture = z3Caller ? input
          val result = Await.result(resultFuture, timeout.duration)
          lapsedTime = System.nanoTime() - start
          output.eagerZ3 = lapsedTime
          val results1 = ResultAnalyser.execute(result.toString)
          output.model1Result = results1
        }
        catch {
          case e: TimeoutException => output.eagerZ3 = -1
        }
      }
      catch {
        case e: TimeoutException =>
          output.eagerSynthesis = -1
          output.eagerZ3 = -2
      }

      try {
        val z3LazyInputGenerator = system.actorOf(Props(new Z3LazyInputGeneratorActor(z3)))
        start = System.nanoTime()
        val inputFuture = z3LazyInputGenerator ? model
        val lazyInput = Await.result(inputFuture, timeout.duration)
        lapsedTime = System.nanoTime() - start
        output.lazySynthesis = lapsedTime

        try {
          val z3Caller = system.actorOf(Props(new Z3CallerActor(z3Path)))
          start = System.nanoTime()
          val resultFuture = z3Caller ? lazyInput
          val result = Await.result(resultFuture, timeout.duration)
          lapsedTime = System.nanoTime() - start
          output.lazyZ3 = lapsedTime
          val results2 = ResultAnalyser.execute(result.toString)
          output.model2Result = results2

          if (output.model1Result == output.model2Result) {
            output.isSameOutput = true
          }
          else {
            output.pealInput = model
          }
          output
        }
        catch {
          case e: TimeoutException => output.lazyZ3 = -1
            output
        }
      }
      catch {
        case e: TimeoutException =>
          output.lazySynthesis = -1
          output.lazyZ3 = -2
          output
      }
    }
    //    catch {
    //      case e: TimeoutException =>
    //                output.
    //        println("timed out in model generation,NotRun,NotRun,NotRun,NotRun")
    //    }
    finally {
      println()
      system.shutdown()
      return output
    }
  }
}
