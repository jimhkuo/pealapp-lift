package peal.runner

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import akka.actor.{Props, ActorSystem}
import peal.runner.actor._
import java.util.concurrent.TimeoutException
import z3.scala.Z3Context

class ExperimentRunner(z3: Z3Context, duration: Long) {
  implicit val system = ActorSystem("exp-runner")

  private def milliTime(timeInNano: Long) = {
    "%.2f".format(timeInNano.toDouble/1000000)
  }

  def run(params: String, z3Path: String) {
    try {
      implicit val timeout = Timeout(duration, MILLISECONDS)

      val generatorRunner = system.actorOf(Props(new ModelGeneratorActor(params)))
      var start = System.nanoTime()
      val modelFuture = generatorRunner ? Run
      val model = Await.result(modelFuture, timeout.duration).asInstanceOf[String]
      var lapsedTime = System.nanoTime() - start
      print(milliTime(lapsedTime) + ",")

      try {
        val z3InputGenerator = system.actorOf(Props(new Z3InputGeneratorActor(z3)))
        start = System.nanoTime()
        val inputFuture = z3InputGenerator ? model
        val input = Await.result(inputFuture, timeout.duration)
        lapsedTime = System.nanoTime() - start
        print(milliTime(lapsedTime) + ",")

//        println(input)

        try {
          val z3Caller = system.actorOf(Props(new Z3CallerActor(z3Path)))
          start = System.nanoTime()
          val resultFuture = z3Caller ? input
          val result = Await.result(resultFuture, timeout.duration)
          lapsedTime = System.nanoTime() - start
          print(milliTime(lapsedTime) + ",")

//          println("result:\n" + result)
        }
        catch {
          case e: TimeoutException => print("timed out in z3,")
        }
      }
      catch {
        case e: TimeoutException => print("timed out in synthesis,NotRun,")
      }

      try {
        val z3LazyInputGenerator = system.actorOf(Props(new Z3LazyInputGeneratorActor(z3)))
        start = System.nanoTime()
        val inputFuture = z3LazyInputGenerator ? model
        val lazyInput = Await.result(inputFuture, timeout.duration)
        lapsedTime = System.nanoTime() - start
        print(milliTime(lapsedTime) + ",")

        println(lazyInput)
        try {
          val z3Caller = system.actorOf(Props(new Z3CallerActor(z3Path)))
          start = System.nanoTime()
          val resultFuture = z3Caller ? lazyInput
          val result = Await.result(resultFuture, timeout.duration)
          lapsedTime = System.nanoTime() - start
          print(milliTime(lapsedTime))

          println("result:\n" + result)
        }
        catch {
          case e: TimeoutException => println("timed out in lazy z3")
        }
      }
      catch {
        case e: TimeoutException => println("timed out in lazy synthesis,NotRun")
      }
    }
    catch {
      case e: TimeoutException => println("timed out in model generation,NotRun,NotRun,NotRun,NotRun")
    }
    finally {
      println()
      system.shutdown()
    }
  }
}
