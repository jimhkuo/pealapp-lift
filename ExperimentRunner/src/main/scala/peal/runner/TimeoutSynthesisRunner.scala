package peal.runner

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import peal.synthesis.{NewSynthesiser, LazySynthesiser, EagerSynthesiser}

object TimeoutSynthesisRunner extends App {

  val inputFileName: String = args(1)
  println(generate())

  def generate(): String = {
    try {
      val input = scala.io.Source.fromFile(inputFileName).mkString

      val synthesiser = args(0) match {
        case "explicit" =>  new EagerSynthesiser(input)
        case "symbolic" =>  new LazySynthesiser(input)
        case "new" =>  new NewSynthesiser(input)
      }

      val outputFuture = future {
        val start = System.nanoTime()
        val out = synthesiser.generate()
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
