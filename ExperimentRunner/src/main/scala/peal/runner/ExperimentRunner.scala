package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{ActorRef, Props, ActorSystem}
import peal.runner.actor._
import peal.model.{MajorityVotingGenerator, RandomModelGenerator}
import java.io.File
import util.FileUtil
import scala.sys.process._
import java.util.concurrent.TimeoutException


class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0, var isSameOutput: Boolean = false, var model1Result: Map[String, String] = Map(), var model2Result: Map[String, String] = Map(), var pealInput: String = "")

class ExperimentRunner(runMode: RunMode, doDomainSpecifics: Boolean, system: ActorSystem, duration: Long, z3CallerMemoryBound: Long) {
  implicit val timeout = Timeout(duration, MILLISECONDS)

  def runRandomModel(n: Int, min: Int, max: Int, plus: Int, mul: Int, k: Int, th: Double, delta: Double): TimingOutput = {
    val model = RandomModelGenerator.generate(doDomainSpecifics, n, min, max, plus, mul, k, th, delta)
    runExperiment(model)
  }

  def runMajorityVoting(n: Int): TimingOutput = {
    val model = MajorityVotingGenerator.generateForCount(n)
    runExperiment(model)
  }

  private def runExperiment(model: String): TimingOutput = {
    val output = new TimingOutput()
    val processKiller = system.actorOf(Props[ProcessKillerActor])
    var eagerZ3Caller: ActorRef = null
    var lazyZ3Caller: ActorRef = null

    val randomModelFile = File.createTempFile("randomModel", "")
    try {
      var start = System.nanoTime()
      var lapsedTime = System.nanoTime() - start
      output.modelGeneration = lapsedTime
      print("m")
      FileUtil.writeToFile(randomModelFile.getAbsolutePath, model)

      if (runMode != LazyOnly) {
        val eagerInput = Seq("java", "-Xmx15240m", "-Xss32m", "-cp", "./Peal.jar", "peal.runner.SynthesisRunner", "explicit", randomModelFile.getAbsolutePath).!!
        if (eagerInput.trim == "TIMEOUT") {
          throw new TimeoutException("Timeout in Eager Synthesis")
        }
        output.eagerSynthesis = eagerInput.split("\n")(0).toLong
        print("e")

        val z3InputFile = File.createTempFile("eagerZ3File", "")
        FileUtil.writeToFile(z3InputFile.getAbsolutePath, eagerInput)

        try {
          eagerZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
          start = System.nanoTime()
          val eagerFuture = eagerZ3Caller ? z3InputFile
          val eagerResult = Await.result(eagerFuture, timeout.duration)
          lapsedTime = System.nanoTime() - start
          output.eagerZ3 = lapsedTime

          eagerResult match {
            case FailedExecution => throw new RuntimeException("Z3 caller aborted")
            case _ => output.model1Result = eagerResult.asInstanceOf[Map[String, String]]
          }
          print("z")
        } catch {
          case e: TimeoutException => processKiller ! z3InputFile
            throw e
        }
      }

      if (runMode != EagerOnly) {
        val lazyInput = Seq("java", "-Xmx15240m", "-Xss32m", "-cp", "./Peal.jar", "peal.runner.SynthesisRunner", "symbolic", randomModelFile.getAbsolutePath).!!
        if (lazyInput.trim == "TIMEOUT") {
          throw new TimeoutException("Timeout in Lazy Synthesis")
        }
        output.lazySynthesis = lazyInput.split("\n")(0).toLong
        print("1")

        val z3InputFile = File.createTempFile("lazyZ3File", "")
        FileUtil.writeToFile(z3InputFile.getAbsolutePath, lazyInput)

        try {
          lazyZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
          start = System.nanoTime()
          val resultFuture = lazyZ3Caller ? z3InputFile
          val lazyResult = Await.result(resultFuture, timeout.duration)
          lapsedTime = System.nanoTime() - start
          output.lazyZ3 = lapsedTime
          lazyResult match {
            case FailedExecution => throw new RuntimeException("Z3 caller aborted")
            case _ => output.model2Result = lazyResult.asInstanceOf[Map[String, String]]
          }
          print("z")
        } catch {
          case e: TimeoutException => processKiller ! z3InputFile
            throw e
        }
      }

      if (runMode != AllSynthesisers) {
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
      //      randomModelFile.delete()
    }
  }
}
