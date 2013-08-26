package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{ActorRef, Props, ActorSystem}
import peal.runner.actor._
import peal.model.RandomModelGenerator

class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0, var isSameOutput: Boolean = false, var model1Result: Map[String, String] = Map(), var model2Result: Map[String, String] = Map(), var pealInput: String = "")

class ExperimentRunner(system: ActorSystem, duration: Long, z3CallerMemoryBound: Long) {

  def run(n: Int, min: Int, max: Int, plus: Int, mul: Int, k: Int, th: Double, delta: Double): TimingOutput = {
//    print("0")
    implicit val timeout = Timeout(duration, MILLISECONDS)
//    print("1")
    val output = new TimingOutput()
    var eagerSynthesiser: ActorRef = null
    var eagerZ3Caller: ActorRef = null
    var lazySynthesiser: ActorRef = null
    var lazyZ3Caller: ActorRef = null
//    print("2")

    try {
      var start = System.nanoTime()
      val model = RandomModelGenerator.generate(n, min, max, plus, mul, k, th, delta)
      var lapsedTime = System.nanoTime() - start
      output.modelGeneration = lapsedTime
      print("m")

//      eagerSynthesiser = system.actorOf(Props[EagerSynthesiserActor])
//      start = System.nanoTime()
//      val eagerInputFuture = ask(eagerSynthesiser, model)
//      val eagerInput = Await.result(eagerInputFuture, timeout.duration)
//      lapsedTime = System.nanoTime() - start
//      output.eagerSynthesis = lapsedTime
//      print("e")
//
//      eagerZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
//      start = System.nanoTime()
//      val eagerFuture = eagerZ3Caller ? eagerInput
//      val eagerResult = Await.result(eagerFuture, timeout.duration)
//      lapsedTime = System.nanoTime() - start
//      output.eagerZ3 = lapsedTime
//      print("z")
//      output.model1Result = eagerResult.asInstanceOf[Map[String, String]]

      lazySynthesiser = system.actorOf(Props[LazySynthesiserActor])
      start = System.nanoTime()
      val lazyInputFuture = lazySynthesiser ? model
      val lazyInput = Await.result(lazyInputFuture, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.lazySynthesis = lapsedTime
      print("l")

      lazyZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
      start = System.nanoTime()
      val resultFuture = lazyZ3Caller ? lazyInput
      val result = Await.result(resultFuture, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.lazyZ3 = lapsedTime
      print("z")
      output.model2Result = result.asInstanceOf[Map[String, String]]

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
      if (eagerSynthesiser != null) system.stop(eagerSynthesiser)
      if (eagerZ3Caller != null) system.stop(eagerZ3Caller)
      if (lazySynthesiser != null) system.stop(lazySynthesiser)
      if (lazyZ3Caller != null) system.stop(lazyZ3Caller)
      //      system.shutdown()
    }
  }
}
