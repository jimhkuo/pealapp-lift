import akka.actor._
import akka.testkit.TestActorRef
import akka.util.Timeout
import java.util.concurrent.TimeoutException
import org.junit.Test
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask

object CLEANUP

class ChildActor extends Actor {
  def receive = {
    case input: String =>
      println("received " + input)
      var i = 0
      while (true) {
        println(i)
        i += 1
      }
      sender ! "done"
  }
}

class TestActor extends Actor {
  var child: ActorRef = null

  implicit val timeout = Timeout(10 milliseconds)

  def receive = {

    case "done" =>
      context.stop(child)
      println("########################################## reply")
    case "hi" =>
      child = context.actorOf(Props[ChildActor])
      val ans = child ? "hi"
    //      sender ! "asked"
  }
}

class ScalaTest {

  implicit lazy val system = ActorSystem()
  implicit val timeout = Timeout(10 milliseconds)

  //  @Test
  def testActor() {

    val myActor = system.actorOf(Props[TestActor])
    try {
      val resultFuture = myActor ? "hi"
      val result = Await.result(resultFuture, timeout.duration)
      println(result)
    } catch {
      case e: Exception =>
        myActor ! "done"
        e.printStackTrace()
        system.stop(myActor)
        system.shutdown()
    }
    Thread.sleep(500)
  }

  @Test
  def testException() {
    val eagerInput = "TIMEOUT"
    try {
      try {
        if (eagerInput == "TIMEOUT") throw new TimeoutException("Timeout in Eager Synthesis")
      }
      catch {
        case e: Exception => throw e
      }
    } catch {
      case e: TimeoutException =>
        println(",TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT")
      case e1: RuntimeException =>
        println(",OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY")
    }
  }
}



