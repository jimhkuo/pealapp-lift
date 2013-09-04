package peal.lazysynthesis

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object LazyFileSynthesiser extends App {

  val inputFileName: String = args(0)
  println(generate())

  def generate(): String = {
    try {
      val input = scala.io.Source.fromFile(inputFileName).mkString

      val outputFuture = future {
        val start = System.nanoTime()
        val out = new LazySynthesiser(input).generate()
        val lapseTime = System.nanoTime() - start
        lapseTime.toString + "\n" + out
      }

      Await.result(outputFuture, 300000 millis)
    } catch {
      case e: TimeoutException =>
        "TIMEOUT"
    }
  }
}
