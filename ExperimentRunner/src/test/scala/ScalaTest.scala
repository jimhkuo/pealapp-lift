import akka.actor._
import akka.util.Timeout
import java.util.concurrent.TimeoutException
import org.junit.Test
import peal.runner.{TimeoutSynthesisRunner, Explicit}
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask

class TestActor extends Actor {
  def receive = {
    case "hi" =>
      throw new RuntimeException()
      sender ! "asked"
  }
}

class ScalaTest {

  implicit lazy val system = ActorSystem()
  implicit val timeout = Timeout(1000 milliseconds)

  @Test
  def testException() {
    try {
      val myActor = system.actorOf(Props[TestActor])
      val resultFuture = myActor ? "hi"
      val result = Await.result(resultFuture, timeout.duration)
      println(result)
    } catch {
      case e1: RuntimeException =>
        println("OUTOFMEMORY")
      case e: TimeoutException =>
        println("TIMEOUT")
    }
  }

  @Test
  def testObject() {
    println(Explicit.toString)
  }

  @Test
  def testFindRunningJar() {
      println(TimeoutSynthesisRunner.getClass.getProtectionDomain().getCodeSource().getLocation().getFile);

  }
}



