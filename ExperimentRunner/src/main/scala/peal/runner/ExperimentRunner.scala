package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Kill, ActorRef, Props, ActorSystem}
import peal.runner.actor._
import peal.model.RandomModelGenerator
import java.util.concurrent.TimeoutException
import java.io.File
import util.FileUtil

class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0, var isSameOutput: Boolean = false, var model1Result: Map[String, String] = Map(), var model2Result: Map[String, String] = Map(), var pealInput: String = "")

class ExperimentRunner(runMode: RunMode, system: ActorSystem, duration: Long, z3CallerMemoryBound: Long) {

  def run(n: Int, min: Int, max: Int, plus: Int, mul: Int, k: Int, th: Double, delta: Double): TimingOutput = {
    implicit val timeout = Timeout(duration, MILLISECONDS)
    val output = new TimingOutput()

    val processKiller = system.actorOf(Props[ProcessKillerActor])

    var eagerSynthesiser: ActorRef = null
    var eagerZ3Caller: ActorRef = null
    var lazySynthesiser: ActorRef = null
    var lazyZ3Caller: ActorRef = null
    val tmp = File.createTempFile("pealInput", "")

    try {
      var start = System.nanoTime()
      val model = RandomModelGenerator.generate(n, min, max, plus, mul, k, th, delta)
      var lapsedTime = System.nanoTime() - start
      output.modelGeneration = lapsedTime
      print("m")
      FileUtil.writeToFile(tmp.getAbsolutePath, model)

      if (runMode != LazyOnly) {
        eagerSynthesiser = system.actorOf(Props[EagerSynthesiserActor])
        val eagerInputFuture = ask(eagerSynthesiser, tmp)
        val eagerInput = Await.result(eagerInputFuture, timeout.duration)
        output.eagerSynthesis = eagerInput.toString.split("\n")(0).toLong
        print("e")

        eagerZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
        start = System.nanoTime()
        val eagerFuture = eagerZ3Caller ? eagerInput
        val eagerResult = Await.result(eagerFuture, timeout.duration)
        lapsedTime = System.nanoTime() - start
        output.eagerZ3 = lapsedTime
        print("z")
        output.model1Result = eagerResult.asInstanceOf[Map[String, String]]
      }

      if (runMode != EagerOnly) {
        lazySynthesiser = system.actorOf(Props[LazySynthesiserActor])
        val lazyInputFuture = lazySynthesiser ? tmp
        val lazyInput = Await.result(lazyInputFuture, timeout.duration)
        output.lazySynthesis = lazyInput.toString.split("\n")(0).toLong
        print("l")

        lazyZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
        start = System.nanoTime()
        val resultFuture = lazyZ3Caller ? lazyInput
        val result = Await.result(resultFuture, timeout.duration)
        lapsedTime = System.nanoTime() - start
        output.lazyZ3 = lapsedTime
        print("z")
        output.model2Result = result.asInstanceOf[Map[String, String]]
      }

      if (runMode != Both) {
        output.isSameOutput = true
      }
      else {
        if (!output.model1Result.isEmpty && output.model1Result == output.model2Result) {
          output.isSameOutput = true
        }
        else {
          output.pealInput = model
        }
      }
      output
    }
    catch {
      case e: TimeoutException =>
        if (eagerSynthesiser != null) {
          system.stop(eagerSynthesiser)
          processKiller ! tmp
        }
        if (eagerZ3Caller != null) system.stop(eagerZ3Caller)
        if (lazySynthesiser != null) {
          system.stop(lazySynthesiser)
          processKiller ! tmp
        }
        if (lazyZ3Caller != null) system.stop(lazyZ3Caller)
        throw e
    }
    finally {
      tmp.delete()
    }
  }
}
