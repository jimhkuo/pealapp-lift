import akka.actor._
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.junit.Test
import peal.runner.actor.{Run, ModelGeneratorActor}
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

  def receive = {
    case "done" =>
      context.stop(child)
      println("########################################## reply")
    case "hi" =>
      child = context.actorOf(Props[ChildActor])
      child ! "hi"
//      sender ! "asked"
  }
}

class ScalaTest {

  implicit lazy val system = ActorSystem()
  implicit val timeout = Timeout(10 milliseconds)

//  @Test
//  def testActor() {
//
//    val myActor = system.actorOf(Props[TestActor])
//    try {
//      val resultFuture = myActor ? "hi"
//      val result = Await.result(resultFuture, timeout.duration)
//      println(result)
//    } catch {
//      case e: Exception =>
//        myActor ! "done"
//        e.printStackTrace()
//        system.stop(myActor)
//        system.shutdown()
//    }
//    Thread.sleep(500)
//  }

  @Test
  def testModel() {
    val myActor = TestActorRef(new ModelGeneratorActor(1, 1, 1, 136, 1, 3 * 136, 0.5, 0.1))

    val modelFuture = myActor ? Run
    val model = Await.result(modelFuture, timeout.duration).asInstanceOf[String]

    println(model)
  }
}



