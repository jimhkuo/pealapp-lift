package peal.runner

import akka.actor.ActorDSL._
import scala.concurrent.{ExecutionContext, Future, Await}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Props, ActorSystem}
import peal.runner.actor.{Run, ExperimentActor}

class ExperimentRunner(duration: Long) {
  implicit val system = ActorSystem("demo")

  def run {
    try {
      implicit val timeout = Timeout(duration, MILLISECONDS)

      val runner = system.actorOf(Props(new ExperimentActor("10, 5, 4, 3, 2, 7, 0.6, 0.1")))

      val future = for (i <- ask(runner, Run).mapTo[String]) yield (i)
      future.foreach("RESULT:\n" + println(_))
    }
    finally {
      system.shutdown()
    }

  }

}
