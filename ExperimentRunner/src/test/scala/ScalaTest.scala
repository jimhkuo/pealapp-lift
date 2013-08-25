import akka.actor._
import akka.util.Timeout
import java.util.concurrent.TimeoutException
import org.junit.Test
import peal.model.RandomModelGenerator
import peal.runner.actor.{EagerSynthesiserActor, Z3CallerActor, Run, ModelGeneratorActor}
import peal.runner.ExperimentRunner
import scala.concurrent.Await
//import akka.testkit.TestActorRef
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
        myActor ! ReceiveTimeout
    }
  }

  @Test
  def testToString() {
//    implicit val timeout = Timeout(300000, MILLISECONDS)
//    val model = RandomModelGenerator.generate(1, 1, 1, 160, 1, 3 * 160, 0.5, 0.1)
//println(model)
//    val eagerSynthesiser = system.actorOf(Props[EagerSynthesiserActor])
//    val eagerInputFuture = eagerSynthesiser ? model
//    val eagerInput = Await.result(eagerInputFuture, timeout.duration)
////println(eagerInput)
//    println("input done")
//    val eagerZ3Caller = system.actorOf(Props(new Z3CallerActor(10)))
//    val eagerFuture = eagerZ3Caller ? eagerInput
//    val eagerResult = Await.result(eagerFuture, timeout.duration)
//    println(eagerResult)
  }
}
