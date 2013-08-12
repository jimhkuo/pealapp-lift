package peal.runner

import z3.scala.{Z3Config, Z3Context}


object Main extends App {
  val z3 = new Z3Context(new Z3Config("MODEL" -> true))
  try {
    val p = 2
    println("%.2f".format(execute(p).toDouble / 1000000))

  } finally {
    z3.delete
  }

  private def execute(p: Int) : Long = {
    var total = 0l
    for (i <- 1 to 5) {
      val output = new ExperimentRunner(z3, 300000).run("1, " + p + ", 1, 1, 1, " + 3 * p + ", 0.5, 0.1", "/Users/jkuo/tools/z3/bin")
      total += output.eagerZ3
      println(output)
    }
    total / 5
  }
}

