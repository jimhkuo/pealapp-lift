package peal.runner

import java.util.concurrent.TimeoutException
import akka.actor.ActorSystem


object Main extends App {
  implicit val system = ActorSystem("system")
  private val z3MemoryBound = 20000000
  private val timeout = 300000
  private val doDomainSpecifics = false

  println("Picking up z3 from environment PATH: " + System.getenv("PATH"))
  binarySearch(Explicit)
  System.exit(0)

  private def binarySearch(runModes: RunMode*) {
    val execute: (Int) => Boolean = (x) => executeRunner(1, 1, x, 1, 1, 3 * x, 0.5, 0.1, runModes: _*)

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

  private def binarySearchOnMajorityVoting(runModes: RunMode*) {
    val execute: (Int) => Boolean = (x) => executeRunner(x, 0, 0, 0, 0, 0, 0, 0, runModes: _*)

    var lastSuccess = 0
    var lastFailure = 0
    var p = 2

    while (execute(p)) {
      lastSuccess = p
      p = p + 1
    }
  }

  private def milliTime(timeInNano: Long) = {
    "%.2f".format(timeInNano.toDouble / 1000000)
  }

  private def executeRunner(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double, runModes: RunMode*): Boolean = {
    val iterations: Int = 5

    var mt = 0l
    var et = 0l
    var ezt = 0l
    var lt = 0l
    var lzt = 0l
    var nt = 0l
    var nzt = 0l
    var xt = 0l
    var xzt = 0l
    var v1 = 0l
    var v2 = 0l
    var v3 = 0l

    try {
      print(n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + ",")

      for (i <- 1 to iterations) {
//        val output = new ExperimentRunner(doDomainSpecifics, system, timeout, z3MemoryBound, runModes: _*).runRandomModel(n, m0, m1, m2, m3, k, th, delta)
        val output = new VerificationExperimentRunner(doDomainSpecifics, system, timeout, z3MemoryBound).runRandomModel(n, m0, m1, m2, m3, k, th, delta)
        mt += output.modelGeneration
        et += output.eagerSynthesis
        ezt += output.eagerZ3
        lt += output.lazySynthesis
        lzt += output.lazyZ3
        nt += output.newSynthesis
        nzt += output.newZ3
        xt += output.extendedSynthesis
        xzt += output.extendedZ3

        if (!output.isSameOutput) {
          println(output.modelResults)
          println("syntheses produce different results," + n + "-" + m0 + "-" + m1 + "-" + m2 + "-" + m3 + "-" + th + "-" + delta + "," + timeout + "," + output.failedPealInput)
        }
      }

      println("," + milliTime(mt / iterations) + "," + milliTime(et / iterations) + "," + milliTime(ezt / iterations) + "," + milliTime(lt / iterations) + "," + milliTime(lzt / iterations) + "," + milliTime(nt / iterations) + "," + milliTime(nzt / iterations)+ "," + milliTime(xt / iterations) + "," + milliTime(xzt / iterations))
      true
    } catch {
      case e: TimeoutException =>
        println(",TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT,TIMEOUT," + e.getMessage)
        false
      case e1: RuntimeException =>
        e1.printStackTrace()
        println(",OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY,OUTOFMEMORY," + e1.getMessage)
        false
    }
  }
}

