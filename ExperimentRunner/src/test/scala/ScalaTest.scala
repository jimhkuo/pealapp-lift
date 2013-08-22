import akka.actor.{Props, ActorSystem, ReceiveTimeout, Actor}
import akka.util.Timeout
import java.util.concurrent.TimeoutException
import scala.concurrent.ExecutionContext.Implicits.global
import org.junit.Test
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask

object TO

class TestActor extends Actor {
  def receive = {
    case input: String => println("received " + input)
      for (i <- 0 to 1000) {
        print(".")
      }
      sender ! "reply"
    case TO =>
      println("timeout")
  }
}


class ScalaTest {
  implicit val system = ActorSystem("exp-runner")

  @Test
  def testActor() {
    implicit val timeout = Timeout(10 milliseconds)

    val myActor = system.actorOf(Props[TestActor])
    try {
    val resultFuture = myActor ? "hi"
    val result = Await.result(resultFuture, timeout.duration)
    println(result)
    } catch {
      case e: TimeoutException =>
        e.printStackTrace()
        myActor ! TO
    }


  }

}
