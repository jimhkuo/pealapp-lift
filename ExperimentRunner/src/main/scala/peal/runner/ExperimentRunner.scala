package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{ActorRef, Props, ActorSystem}
import peal.runner.actor._

class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0, var isSameOutput: Boolean = false, var model1Result: Map[String, String] = Map(), var model2Result: Map[String, String] = Map(), var pealInput: String = "")

class ExperimentRunner(duration: Long, z3CallerMemoryBound: Long) {

  def run(n: Int, min: Int, max: Int, plus: Int, mul: Int, k: Int, th: Double, delta: Double): TimingOutput = {
    print("0")
    implicit val system = ActorSystem("system-" + System.nanoTime())
    implicit val timeout = Timeout(duration, MILLISECONDS)
    print("1")
    val output = new TimingOutput()
    var generatorRunner: ActorRef = null
    var eagerSynthesiser: ActorRef = null
    var eagerZ3Caller: ActorRef = null
    var lazySynthesiser: ActorRef = null
    var lazyZ3Caller: ActorRef = null
    print("2")

    try {
      generatorRunner = system.actorOf(Props(new ModelGeneratorActor(n, min, max, plus, mul, k, th, delta)), name = "generator-" + this.toString)
      var start = System.nanoTime()
      val modelFuture = generatorRunner ? Run
      print("3")
      val model = Await.result(modelFuture, timeout.duration).asInstanceOf[String]
      print("4")
      var lapsedTime = System.nanoTime() - start
      output.modelGeneration = lapsedTime
      print("m")

      eagerSynthesiser = system.actorOf(Props[EagerSynthesiserActor], name = "eager-" + this.toString)
      start = System.nanoTime()
      val eagerInputFuture = ask(eagerSynthesiser, model)
      val eagerInput = Await.result(eagerInputFuture, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.eagerSynthesis = lapsedTime
      print("e")

      eagerZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)), name = "eagerZ3-" + this.toString)
      start = System.nanoTime()
      val eagerFuture = eagerZ3Caller ? eagerInput
      val eagerResult = Await.result(eagerFuture, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.eagerZ3 = lapsedTime
      print("z")
      val results1 = ResultAnalyser.execute(eagerResult.toString)
      output.model1Result = results1

      lazySynthesiser = system.actorOf(Props[LazySynthesiserActor], name = "lazy-" + this.toString)
      start = System.nanoTime()
      val lazyInputFuture = lazySynthesiser ? model
      val lazyInput = Await.result(lazyInputFuture, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.lazySynthesis = lapsedTime
      print("l")

      lazyZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)), name = "lazyZ3-" + this.toString)
      start = System.nanoTime()
      val resultFuture = lazyZ3Caller ? lazyInput
      val result = Await.result(resultFuture, timeout.duration)
      lapsedTime = System.nanoTime() - start
      output.lazyZ3 = lapsedTime
      print("z")
      val results2 = ResultAnalyser.execute(result.toString)
      output.model2Result = results2

      //            output.isSameOutput = true
      if (!output.model1Result.isEmpty && output.model1Result == output.model2Result) {
        output.isSameOutput = true
      }
      else {
        output.pealInput = model
      }
      output
    }
    finally {
      //      if (generatorRunner != null) system.stop(generatorRunner)
      //      if (eagerSynthesiser != null) system.stop(eagerSynthesiser)
      //      if (eagerZ3Caller != null) system.stop(eagerZ3Caller)
      //      if (lazySynthesiser != null) system.stop(lazySynthesiser)
      //      if (lazyZ3Caller != null) system.stop(lazyZ3Caller)
      system.shutdown()
    }
  }
}
