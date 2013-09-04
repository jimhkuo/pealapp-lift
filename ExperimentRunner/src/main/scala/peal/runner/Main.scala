package peal.runner

import java.util.concurrent.TimeoutException
import akka.actor.ActorSystem


object Main extends App {
  implicit val system = ActorSystem("system")
  private val z3MemoryBound = 6000000
  private val timeout = 300000

  println("Picking up z3 from environment PATH: " + System.getenv("PATH"))
  binarySearchOnPolicySize(LazyOnly)
  System.exit(0)

  private def binarySearchOnPolicySize(runMode: RunMode) {
    val execute: (Int) => Boolean = (x) => executeRunner(runMode, x, 1, 1, x, 1, 3 * x, 0.5, 0.1)

    var lastSuccess = 0
    var lastFailure = 0
    var p = 2

    while (execute(p)) {
      lastSuccess = p
      p = p * 2
    }

    println("############################")

    lastFailure = p
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

  private def binarySearchOnRuleSize(runMode: RunMode) {
    val execute: (Int) => Boolean = (x) => executeRunner(runMode, 1, x, 1, 1, 1, 3 * x, 0.5, 0.1)

    var lastSuccess = 0
    var lastFailure = 0
    var p = 2

    while (execute(p)) {
      lastSuccess = p
      p = p * 2
    }

    println("############################")

    lastFailure = p
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

  private def executeRunner(runMode: RunMode, n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): Boolean = {
    val iterations: Int = 5

    var mt = 0l
    var et = 0l
    var ezt = 0l
    var lt = 0l
    var lzt = 0l

    try {
      print(n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + ",")

      for (i <- 1 to iterations) {
        val output = new ExperimentRunner(runMode,system, timeout, z3MemoryBound).run(n, m0, m1, m2, m3, k, th, delta)
        mt += output.modelGeneration
        et += output.eagerSynthesis
        ezt += output.eagerZ3
        lt += output.lazySynthesis
        lzt += output.lazyZ3
        if (!output.isSameOutput) {
          if (output.model1Result.size == 0) {
            println("model1 is empty")
          }
          if (output.model2Result.size == 0) {
            println("model2 is empty")
          }
          println("eager and lazy produce different result," + n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + "," + output.pealInput)
        }
      }

      println("," + milliTime(mt / iterations) + "," + milliTime(et / iterations) + "," + milliTime(ezt / iterations) + "," + milliTime(lt / iterations) + "," + milliTime(lzt / iterations))
      true
    } catch {
      case e: TimeoutException =>
        println(",TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT," + e.getMessage)
        false
      case e1: RuntimeException =>
        println(",OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY," + e1.getMessage)
        false
    }
  }
}

