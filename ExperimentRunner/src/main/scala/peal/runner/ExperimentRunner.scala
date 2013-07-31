package peal.runner

import akka.actor.ActorDSL._
import scala.concurrent.{ExecutionContext, Future, Await}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Props, ActorSystem}
import akka.pattern.pipe
import peal.runner.actor.{Z3InputGeneratorActor, Z3CallerActor, Run, ModelGeneratorActor}
import scala.util.{Failure, Success}
import java.util.concurrent.TimeoutException
import z3.scala.{Z3Config, Z3Context}

class ExperimentRunner(z3: Z3Context, duration: Long) {
  implicit val system = ActorSystem("exp-runner")

  def run(params: String, z3Path: String) {
    try {
      implicit val timeout = Timeout(duration, MILLISECONDS)

      val generatorRunner = system.actorOf(Props(new ModelGeneratorActor(params)))
      val modelFuture = generatorRunner ? Run
      val model = Await.result(modelFuture, timeout.duration).asInstanceOf[String]

      try {
        val z3InputGenerator = system.actorOf(Props(new Z3InputGeneratorActor(z3)))
        val inputFuture = z3InputGenerator ? model
        val input = Await.result(inputFuture, timeout.duration)

        try {
          val z3Caller = system.actorOf(Props(new Z3CallerActor(z3Path)))
          val resultFuture = z3Caller ? input
          val result = Await.result(resultFuture, timeout.duration)

          println("result:\n" + result)
        }
        catch {
          case e: TimeoutException => println("timed out in z3")
        }
      }
      catch {
        case e: TimeoutException => println("timed out in synthesis")
      }
    }
    catch {
      case e: TimeoutException => println("timed out in model generation")
    }
    finally {
      system.shutdown()
    }
  }
}
