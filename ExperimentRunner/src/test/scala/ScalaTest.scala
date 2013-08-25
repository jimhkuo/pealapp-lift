import akka.actor._
import akka.testkit.{TestKit, TestActorRef}
import akka.util.Timeout
import java.util.concurrent.TimeoutException
import org.junit.{After, Test}
import peal.model.RandomModelGenerator
import peal.runner.actor.{Run, ModelGeneratorActor}
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
  implicit val timeout = Timeout(100000 milliseconds)

  @After
  def shutdown() {
    system.shutdown()
  }

  @Test
  def testActor() {

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
    val myActor = TestActorRef(new ModelGeneratorActor(1, 1, 1, 136, 1, 3 * 136, 0.5, 0.1))

    val modelFuture = myActor ? Run
    val model = Await.result(modelFuture, timeout.duration).asInstanceOf[String]

    println(model)
  }
}



