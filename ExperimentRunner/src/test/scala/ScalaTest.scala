import akka.actor._
import akka.testkit.{TestKit, TestActorRef}
import akka.util.Timeout
import java.util.concurrent.TimeoutException
import org.junit.{After, Test}
import peal.model.RandomModelGenerator
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
    case ReceiveTimeout =>
      println("\nclean up")
  }
}

class ScalaTest {

  implicit lazy val system = ActorSystem()

  @After
  def shutdown() {
    system.shutdown()
  }

  @Test
  def testActor() {
    implicit val timeout = Timeout(10 milliseconds)

    val myActor = TestActorRef(new TestActor)
    try {
      val resultFuture = myActor ? "hi"
      val result = Await.result(resultFuture, timeout.duration)
      println("\n" + result)
    } catch {
      case e: TimeoutException =>
        e.printStackTrace()
        myActor ! ReceiveTimeout
    }
  }

  @Test
  def testModel() {
    println(RandomModelGenerator.generate(1, 1, 1, 136, 1, 3 * 136, 0.5, 0.1))
  }
}



