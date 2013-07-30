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

class ExperimentRunner(duration: Long) {
  implicit val system = ActorSystem("demo")

  def run(params: String) {
    try {
      val z3 = new Z3Context(new Z3Config("MODEL" -> true))
      implicit val timeout = Timeout(duration, MILLISECONDS)

      val generatorRunner = system.actorOf(Props(new ModelGeneratorActor(params)))
      val z3Runner = system.actorOf(Props(new Z3CallerActor(z3)))

      val inputFuture = generatorRunner ? Run
      val input = Await.result(inputFuture, timeout.duration).asInstanceOf[String]

      println(input)

      val resultFuture = z3Runner ? input

      val result = Await.result(resultFuture, timeout.duration)

      println("result:\n" + result)


      //      val future = ask(generatorRunner, Run).mapTo[String]
      //
      //      future pipeTo z3Runner
      //      var input = ""
      //
      //      future onComplete {
      //        case Success(s) =>
      //          println("success: " + s)
      //            input = s
      //        case Failure(f) => println("failed")
      //      }
      //
      //      println(input)
      //      val result = ask(z3Runner, input)
      //      result.foreach("RESULT:\n" + println(_))

    }
    catch {
      case e: TimeoutException => println("timed out")
    }
    finally {
      system.shutdown()
    }

  }

}
