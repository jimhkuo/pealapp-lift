package peal.runner

import z3.scala.{Z3Config, Z3Context}
import java.util.concurrent.TimeoutException


object Main extends App {

  var lastSuccess = 0
  var lastFailure = 0
  var p = 2

  while (execute(1, p, p, p, p, 0.5, 0.1)) {
    lastSuccess = p
    p = p * 2
  }

  println("############################")

  lastFailure = p
  while (lastFailure - lastSuccess > 10) {
    p = (lastSuccess + lastFailure) / 2
    if (execute(1, p, p, p, p, 0.5, 0.1)) {
      lastSuccess = p
    }
    else {
      lastFailure = p
    }
  }

  System.exit(0)

  private def milliTime(timeInNano: Long) = {
    "%.2f".format(timeInNano.toDouble / 1000000)
  }

  private def execute(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, th: Double, delta: Double): Boolean = {
    var z3: Z3Context = null
    val z3Path: String = if (args.size == 0) "/Users/jkuo/tools/z3/bin" else args(0)
    try {

      for (i <- 1 to 5) {
        z3 = new Z3Context(new Z3Config("MODEL" -> true))
        val output = new ExperimentRunner(50000).run(n, m0, m1, m2, m3, 3 * p, th, delta, z3Path)
        println(n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + milliTime(output.modelGeneration) + "," + milliTime(output.eagerSynthesis) + "," + milliTime(output.eagerZ3) + "," + milliTime(output.lazySynthesis) + "," + milliTime(output.lazyZ3) + "," + output.isSameOutput.toString.toUpperCase + "," + output.model1Result + "," + output.pealInput)
        z3.delete
        z3 = null
      }
      true
    } catch {
      case e: TimeoutException =>
        false
    }
    finally {
      if (z3 != null) z3.delete
    }
  }
}

