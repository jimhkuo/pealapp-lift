import akka.actor.{Props, ActorSystem, Actor}
import akka.util.Timeout
import java.util.concurrent.TimeoutException
import org.junit.Test
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask

object CLEANUP

class TestActor extends Actor {
  def receive = {
    case input: String => println("received " + input)
      for (i <- 0 to 1000) {
        print(".")
      }
      sender ! "reply"
    case CLEANUP =>
      println("\nclean up")
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
    println("\n" + result)
    } catch {
      case e: TimeoutException =>
        e.printStackTrace()
        myActor ! CLEANUP
    }
  }
}
