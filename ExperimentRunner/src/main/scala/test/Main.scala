package test

import akka.actor.ActorDSL._
import akka.actor.ActorSystem
import peal.model.RandomModelGenerator
import scala.concurrent.{ExecutionContext, Future, Await}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.pattern.ask
import scala.concurrent.{Future, Await}
import akka.util.Timeout

object Main extends App {
  implicit val system = ActorSystem("demo")
  try {
    implicit val timeout = Timeout(100.millis)

    val a = actor(new Act {
      become {
        case s: String ⇒
          for (i <- 0 until 1000) {
            print(".")
          }
          println()
          //        println(RandomModelGenerator.generate(s.split(Array(' ', ',')).filterNot(_ == ""): _*))
          sender ! "done"
      }
    })

    val msg = "100, 5, 4, 3, 2, 7, 0.6, 0.1"
    val future = for (i <- ask(a, msg).mapTo[String]) yield (i)
//    future.foreach("future: " + println(_))

    val result = Await.result(future, timeout.duration).asInstanceOf[String]
    println(result)
  }
  finally {

    system.shutdown()
  }


}
