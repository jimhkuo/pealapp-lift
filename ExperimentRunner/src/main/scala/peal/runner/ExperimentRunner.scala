package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{ActorRef, Props, ActorSystem}
import peal.runner.actor._
import peal.model.RandomModelGenerator
import java.io.File
import util.FileUtil
import scala.sys.process._
import java.util.concurrent.TimeoutException


class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0, var isSameOutput: Boolean = false, var model1Result: Map[String, String] = Map(), var model2Result: Map[String, String] = Map(), var pealInput: String = "")

class ExperimentRunner(runMode: RunMode, system: ActorSystem, duration: Long, z3CallerMemoryBound: Long) {

  def run(n: Int, min: Int, max: Int, plus: Int, mul: Int, k: Int, th: Double, delta: Double): TimingOutput = {
    implicit val timeout = Timeout(duration, MILLISECONDS)
    val output = new TimingOutput()

    var eagerZ3Caller: ActorRef = null
    var lazyZ3Caller: ActorRef = null
    val tempPealInputFile = File.createTempFile("pealInput", "")

    try {
      var start = System.nanoTime()
      val model = RandomModelGenerator.generate(n, min, max, plus, mul, k, th, delta)
      var lapsedTime = System.nanoTime() - start
      output.modelGeneration = lapsedTime
      print("m")
      FileUtil.writeToFile(tempPealInputFile.getAbsolutePath, model)

      if (runMode != LazyOnly) {
        val eagerInput = Seq("java", "-Xmx10240m", "-Xss32m", "-cp", "./Peal.jar", "peal.eagersynthesis.EagerFileSynthesiser", tempPealInputFile.getAbsolutePath).!!
        if (eagerInput == "TIMEOUT") throw new TimeoutException("Timeout in Eager Synthesis")
        output.eagerSynthesis = eagerInput.split("\n")(0).toLong
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
        val lazyInput = Seq("java", "-Xmx10240m", "-Xss32m", "-cp", "./Peal.jar", "peal.lazysynthesis.LazyFileSynthesiser", tempPealInputFile.getAbsolutePath).!!
        if (lazyInput == "TIMEOUT") throw new TimeoutException("Timeout in Lazy Synthesis")
        output.lazySynthesis = lazyInput.split("\n")(0).toLong
        print("1")

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
      case e: Exception =>
        if (eagerZ3Caller != null) system.stop(eagerZ3Caller)
        if (lazyZ3Caller != null) system.stop(lazyZ3Caller)
        throw e
    }
    finally {
      tempPealInputFile.delete()
    }
  }
}
