package peal.runner

import z3.scala.{Z3Config, Z3Context}
import java.util.concurrent.TimeoutException


object Main extends App {
  val z3 = new Z3Context(new Z3Config("MODEL" -> true))
  var p = 2
  try {
    while (execute(p)) {
      p = p * 2
    }

  } finally {
    z3.delete
  }

  private def milliTime(timeInNano: Long) = {
    "%.2f".format(timeInNano.toDouble / 1000000)
  }

  private def execute(p: Int): Boolean = {
    try {
      for (i <- 1 to 5) {
        val output = new ExperimentRunner(z3, 100).run("1, " + p + ", 1, 1, 1, " + 3 * p + ", 0.5, 0.1", "/Users/jkuo/tools/z3/bin")
        println("p=" + p + "," + milliTime(output.modelGeneration) + "," + milliTime(output.eagerSynthesis) + "," + milliTime(output.eagerZ3) + "," + milliTime(output.lazySynthesis) + "," + milliTime(output.lazyZ3) + "," + output.isSameOutput)
      }
      true
    }  catch {
      case e : TimeoutException => false
    }
  }
}

