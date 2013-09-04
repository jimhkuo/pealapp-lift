package peal.eagersynthesis

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object EagerFileSynthesiser extends App {

  val inputFileName: String = args(0)
  println(generate())

  def generate(): String = {
    try {
      val input = scala.io.Source.fromFile(inputFileName).mkString

      val start = System.nanoTime()
      val outputFuture = future {
        new EagerSynthesiser().generate(input)
      }
      val output = Await.result(outputFuture, 300000 millis)
      val lapseTime = System.nanoTime() - start

      lapseTime.toString + "\n" + output
    } catch {
      case e: TimeoutException =>
        "TIMEOUT"
    }
  }
}
