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


class TimingOutput(var modelGeneration: Long = 0, var eagerSynthesis: Long = 0, var eagerZ3: Long = 0, var lazySynthesis: Long = 0, var lazyZ3: Long = 0,
                   var isSameOutput: Boolean = false,
                   var model1Result: Map[String, String] = Map(),
                   var model2Result: Map[String, String] = Map(),
                   var pealInput: String = "")

class ExperimentRunner(doDomainSpecifics: Boolean, system: ActorSystem, duration: Long, z3CallerMemoryBound: Long, runModes: RunMode*) {
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

    val randomModelFile = File.createTempFile("randomModel", "")
    try {
      FileUtil.writeToFile(randomModelFile.getAbsolutePath, model)
      print("m")

      def runSysthesiser(mode: String, tag: String) {
        val eagerInput = Seq("java", "-Xmx15240m", "-Xss32m", "-cp", "./Peal.jar", "peal.runner.SynthesisRunner", mode, randomModelFile.getAbsolutePath).!!
        if (eagerInput.trim == "TIMEOUT") {
          throw new TimeoutException("Timeout in " + mode + " Synthesis")
        }

        mode match {
          case "explicit" => output.eagerSynthesis = eagerInput.split("\n")(0).toLong
          case "symbolic" => output.lazySynthesis = eagerInput.split("\n")(0).toLong
        }

        print(tag)

        val z3InputFile = File.createTempFile("z3File", "")
        FileUtil.writeToFile(z3InputFile.getAbsolutePath, eagerInput)

        try {
          eagerZ3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))
          val start = System.nanoTime()
          val eagerFuture = eagerZ3Caller ? z3InputFile
          val eagerResult = Await.result(eagerFuture, timeout.duration)
          val lapsedTime = System.nanoTime() - start
          mode match {
            case "explicit" => output.eagerZ3 = lapsedTime
            case "symbolic" => output.lazyZ3 = lapsedTime
          }

          eagerResult match {
            case FailedExecution => throw new RuntimeException("Z3 caller aborted")
            case _ => mode match {
              case "explicit" => output.model1Result = eagerResult.asInstanceOf[Map[String, String]]
              case "symbolic" => output.model2Result = eagerResult.asInstanceOf[Map[String, String]]
            }
          }
          print("z")
        } catch {
          case e: TimeoutException => processKiller ! z3InputFile
            throw e
        }
      }

      if (runModes.contains(Explicit)) {
        runSysthesiser("explicit", "e")
      }

      if (runModes.contains(Symbolic)) {
        runSysthesiser("symbolic", "l")
      }

      if (runModes.size == 1) {
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
        throw e
    }
    finally {
      //      randomModelFile.delete()
    }
  }
}
