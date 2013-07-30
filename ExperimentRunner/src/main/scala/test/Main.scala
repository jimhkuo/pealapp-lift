package test

import akka.actor.{Props, ActorSystem}
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import peal.actor.ExperimentActor

object Main extends App {
  implicit val system = ActorSystem("demo")
  try {
    implicit val timeout = Timeout(100 millis)

    val runner = system.actorOf(Props(new ExperimentActor("10, 5, 4, 3, 2, 7, 0.6, 0.1")))

    val msg = "10, 5, 4, 3, 2, 7, 0.6, 0.1"
    val future = for (i <- ask(runner, msg).mapTo[String]) yield (i)
    future.foreach("RESULT:\n" + println(_))
  }
  finally {

    system.shutdown()
  }


}
