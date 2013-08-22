package peal.runner

import java.util.concurrent.TimeoutException


object Main extends App {

  private val z3MemoryBound = 3000000
  private val timeout = 300000

  println("Picking up z3 from environment PATH: " + System.getenv("PATH"))

  var lastSuccess = 0
  var lastFailure = 0
  var p = 2

  while (execute(1, 1, 1, p, 1, 0.5, 0.1)) {
    lastSuccess = p
    p = p * 2
  }

  println("############################")

  lastFailure = p
  while (lastFailure - lastSuccess > 10) {
    p = (lastSuccess + lastFailure) / 2
    if (execute(1, 1, 1, p, 1, 0.5, 0.1)) {
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
    val iterations: Int = 5

    var mt = 0l
    var et = 0l
    var ezt = 0l
    var lt = 0l
    var lzt = 0l

    try {
      for (i <- 1 to iterations) {
        val output = new ExperimentRunner(timeout, z3MemoryBound).run(n, m0, m1, m2, m3, 3 * p, th, delta)
        mt += output.modelGeneration
        et += output.eagerSynthesis
        ezt += output.eagerZ3
        lt += output.lazySynthesis
        lzt += output.lazyZ3
        if (output.model1Result.size == 0) {
          println("model1 is empty")
        }
        if (!output.isSameOutput) {
          println("eager and lazy produce different result," + n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + "," + output.pealInput)
        }

      }

      println("," + n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + "," + milliTime(mt / iterations) + "," + milliTime(et / iterations) + "," + milliTime(ezt / iterations) + "," + milliTime(lt / iterations) + "," + milliTime(lzt / iterations))
      true
    } catch {
      case e: TimeoutException =>
        println("," + n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + ",TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT")
        false
      case e1: RuntimeException =>
        println("," + n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + ",OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY")
        false
    }
  }
}

