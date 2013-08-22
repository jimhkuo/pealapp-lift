package peal.runner

import scala.concurrent.{ExecutionContext, Await}
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Props, ActorSystem}
import peal.runner.actor._
import java.util.concurrent.TimeoutException
import ExecutionContext.Implicits.global

class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0, var isSameOutput: Boolean = false, var model1Result: Map[String, String] = Map(), var model2Result: Map[String, String] = Map(), var pealInput: String = "")

object TimedOut

class ExperimentRunner(duration: Long, z3CallerMemoryBound: Long) {
  implicit val system = ActorSystem("exp-runner")

  def run(n: Int, min: Int, max: Int, plus: Int, mul: Int, k: Int, th: Double, delta: Double): TimingOutput = {
    val output = new TimingOutput()
    val eagerSynthesiser = system.actorOf(Props[EagerSynthesiserActor])
    val eagerZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
    try {
      implicit val timeout = Timeout(duration, MILLISECONDS)

      val generatorRunner = system.actorOf(Props(new ModelGeneratorActor(n, min, max, plus, mul, k, th, delta)))
      var start = System.nanoTime()
      val modelFuture = generatorRunner ? Run
      val model = Await.result(modelFuture, timeout.duration).asInstanceOf[String]
      var lapsedTime = System.nanoTime() - start
      output.modelGeneration = lapsedTime
      print("m")

      start = System.nanoTime()
      val inputFuture1 = ask(eagerSynthesiser, model) recover {
        case e: TimeoutException => eagerSynthesiser ! TimedOut
      }
      val input = Await.result(inputFuture1, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.eagerSynthesis = lapsedTime
      print("e")

      start = System.nanoTime()
      val resultFuture1 = eagerZ3Caller ? input
      val result1 = Await.result(resultFuture1, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.eagerZ3 = lapsedTime
      print("z")
      val results1 = ResultAnalyser.execute(result1.toString)
      output.model1Result = results1

      //      val z3Lazy = new Z3Context(new Z3Config("MODEL" -> true))
      //      val z3LazyInputGenerator = system.actorOf(Props(new Z3LazyInputGeneratorActor(z3Lazy)))
      //      start = System.nanoTime()
      //      val inputFuture = z3LazyInputGenerator ? model
      //      val lazyInput = Await.result(inputFuture, timeout.duration)
      //      lapsedTime = System.nanoTime() - start
      //      output.lazySynthesis = lapsedTime
      //      z3Lazy.delete()
      //      print("l")
      //
      //      val lazyZ3Caller = system.actorOf(Props(new Z3CallerActor(z3Path, z3CallerMemoryBound)))
      //      start = System.nanoTime()
      //      val resultFuture = lazyZ3Caller ? lazyInput
      //      val result = Await.result(resultFuture, timeout.duration)
      //      lapsedTime = System.nanoTime() - start
      //      output.lazyZ3 = lapsedTime
      //      print("z")
      //      val results2 = ResultAnalyser.execute(result.toString)
      //      output.model2Result = results2

      output.isSameOutput = true
      //      if (!output.model1Result.isEmpty && output.model1Result == output.model2Result) {
      //        output.isSameOutput = true
      //      }
      //      else {
      //        output.pealInput = model
      //      }
      output
    }
    finally {
//      system.stop(eagerSynthesiser)
      system.stop(eagerZ3Caller)
      system.shutdown()
    }
  }
}
