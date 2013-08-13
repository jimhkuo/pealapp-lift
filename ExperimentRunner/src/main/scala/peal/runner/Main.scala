package peal.runner

import z3.scala.{Z3Config, Z3Context}
import java.util.concurrent.TimeoutException


object Main extends App {

  var lastSuccess = 0
  var lastFailure = 0
  var p = 2

  try {
    while (execute(p)) {
      lastSuccess = p
      p = p * 2
      lastFailure = p
    }

    println("############################")

    while (lastFailure - lastSuccess > 10) {
      p = (lastSuccess + lastFailure) / 2
      if (execute(p)) {
        lastSuccess = p
      }
      else {
        lastFailure = p
      }
    }
  }

  private def milliTime(timeInNano: Long) = {
    "%.2f".format(timeInNano.toDouble / 1000000)
  }

  private def execute(p: Int): Boolean = {
    var z3: Z3Context = null
    val z3Path: String = if (args.size ==0 ) "/Users/jkuo/tools/z3/bin" else args(0)
    try {

      for (i <- 1 to 5) {
        z3 = new Z3Context(new Z3Config("MODEL" -> true))
        val output = new ExperimentRunner(300000).run(1, p, 1, 1, 1, 3 * p, 0.5, 0.1, z3Path)
        println("p=" + p + "," + milliTime(output.modelGeneration) + "," + milliTime(output.eagerSynthesis) + "," + milliTime(output.eagerZ3) + "," + milliTime(output.lazySynthesis) + "," + milliTime(output.lazyZ3) + "," + output.isSameOutput + "," + output.pealInput)
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

