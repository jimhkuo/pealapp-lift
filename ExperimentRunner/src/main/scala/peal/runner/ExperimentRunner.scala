package peal.runner

import akka.actor.ActorDSL._
import scala.concurrent.{ExecutionContext, Future, Await}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Props, ActorSystem}
import akka.pattern.pipe
import peal.runner.actor.{Z3CallerActor, Run, ModelGeneratorActor}
import scala.util.{Failure, Success}
import java.util.concurrent.TimeoutException
import z3.scala.{Z3Config, Z3Context}

class ExperimentRunner(z3 : Z3Context, duration: Long) {
  implicit val system = ActorSystem("exp-runner")

  def run(params: String) {
    try {
      implicit val timeout = Timeout(duration, MILLISECONDS)

      val generatorRunner = system.actorOf(Props(new ModelGeneratorActor(params)))
      val z3Runner = system.actorOf(Props(new Z3CallerActor(z3)))
      val inputFuture = generatorRunner ? Run
      val input = Await.result(inputFuture, timeout.duration).asInstanceOf[String]
      val resultFuture = z3Runner ? input
      val result = Await.result(resultFuture, timeout.duration)

      println("result:\n" + result)
    }
    catch {
      case e: TimeoutException => println("timed out")
    }
    finally {
      system.shutdown()
    }
  }
}
