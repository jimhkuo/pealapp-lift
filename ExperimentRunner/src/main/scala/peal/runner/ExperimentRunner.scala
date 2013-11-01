package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{ActorRef, Props, ActorSystem}
import peal.runner.actor._
import peal.model.MajorityVotingGenerator
import java.io.File
import util.FileUtil
import scala.sys.process._
import java.util.concurrent.TimeoutException
import scala.collection.mutable.ListBuffer


class TimingOutput(var modelGeneration: Long = 0,
                   var eagerSynthesis: Long = 0,
                   var eagerZ3: Long = 0,
                   var lazySynthesis: Long = 0,
                   var lazyZ3: Long = 0,
                   var newSynthesis: Long = 0,
                   var newZ3: Long = 0,
                   var isSameOutput: Boolean = false,
                   var modelResults: ListBuffer[Map[String, String]] = ListBuffer(),
                   var pealInput: String = "")

class ExperimentRunner(doDomainSpecifics: Boolean, system: ActorSystem, duration: Long, z3CallerMemoryBound: Long, runModes: RunMode*) {
  implicit val timeout = Timeout(duration, MILLISECONDS)

  def runMajorityVoting(n: Int): TimingOutput = {
    val model = MajorityVotingGenerator.generateForCount(n)
    runExperiment(model)
  }

  def runRandomModel(n: Int, min: Int, max: Int, plus: Int, mul: Int, k: Int, th: Double, delta: Double): TimingOutput = {
    val model = Seq("java", "-Xmx5120m", "-Xss32m", "-cp", "./Peal.jar", "peal.runner.ModelGeneratorRunner", doDomainSpecifics.toString, n.toString, min.toString, max.toString, plus.toString, mul.toString, k.toString, th.toString, delta.toString).!!
    runExperiment(model)
  }

  private def runExperiment(model: String): TimingOutput = {
    val output = new TimingOutput()
    val processKiller = system.actorOf(Props[ProcessKillerActor])
    val randomModelFile = File.createTempFile("randomModel", "")

    try {
      FileUtil.writeToFile(randomModelFile.getAbsolutePath, model)
      print("m")

      def runSysthesiser(mode: String) {
        val z3Input = Seq("java", "-Xmx25600m", "-Xss32m", "-cp", "./Peal.jar", "peal.runner.TimeoutSynthesisRunner", mode, randomModelFile.getAbsolutePath).!!
        if (z3Input.trim == "TIMEOUT") {
          throw new TimeoutException("Timeout in " + mode + " Synthesis")
        }

        mode match {
          case "explicit" => output.eagerSynthesis = z3Input.split("\n")(0).toLong; print("e")
          case "symbolic" => output.lazySynthesis = z3Input.split("\n")(0).toLong; print("l")
          case "new" => output.newSynthesis = z3Input.split("\n")(0).toLong; print("n")
        }

        val z3InputFile = File.createTempFile("z3File", "")
        FileUtil.writeToFile(z3InputFile.getAbsolutePath, z3Input)

        val z3Caller = system.actorOf(Props(new Z3CallerActor(z3CallerMemoryBound)))

        try {
          val start = System.nanoTime()
          val future = z3Caller ? z3InputFile
          val result = Await.result(future, timeout.duration)
          val lapsedTime = System.nanoTime() - start
          mode match {
            case "explicit" => output.eagerZ3 = lapsedTime
            case "symbolic" => output.lazyZ3 = lapsedTime
            case "new" => output.newZ3 = lapsedTime
          }

          result match {
            case FailedExecution => throw new RuntimeException("Z3 caller aborted")
            case _ => output.modelResults.append(result.asInstanceOf[Map[String, String]])
          }

          print("z")
        } catch {
          case e: TimeoutException => processKiller ! z3InputFile
            throw e
        } finally {
          system.stop(z3Caller)
        }
      }

      if (runModes.contains(Explicit)) {
        runSysthesiser("explicit")
      }

      if (runModes.contains(Symbolic)) {
        runSysthesiser("symbolic")
      }

      if (runModes.contains(NewSynthesis)) {
        runSysthesiser("new")
      }

      if (runModes.size == 1 || allEqual(output.modelResults.toList)) {
        output.isSameOutput = true
      }
      else {
        output.pealInput = model
      }
      output
    }
    finally {
      randomModelFile.delete()
    }
  }

  private def allEqual(list: List[Any]): Boolean = list match {
    case x1 :: x2 :: xs => (x1 == x2) && allEqual(x2 :: xs)
    case x1 :: Nil => true
  }
}
