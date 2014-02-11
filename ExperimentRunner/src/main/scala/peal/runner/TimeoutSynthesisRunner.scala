package peal.runner

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import peal.synthesis.{ExtendedSynthesiser, NewSynthesiser, LazySynthesiser, EagerSynthesiser}

object TimeoutSynthesisRunner extends App {

  println(generate())

  def generate(): String = {
    try {
      val input = scala.io.Source.fromFile(args(1)).mkString

      val synthesiser = args(0) match {
        case "explicit" =>  new EagerSynthesiser(input)
        case "symbolic" =>  new LazySynthesiser(input)
        case "new" =>  new NewSynthesiser(input)
        case "extended" =>  new ExtendedSynthesiser(input)
      }

      val outputFuture = future {
        val start = System.nanoTime()
        val result = synthesiser.generate()
        val lapseTime = System.nanoTime() - start
        lapseTime.toString + "\n" + result
      }

      Await.result(outputFuture, 300000 millis)
    } catch {
      case e: TimeoutException =>
        "TIMEOUT"
    }
  }
}
