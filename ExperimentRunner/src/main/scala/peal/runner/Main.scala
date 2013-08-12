package peal.runner

import z3.scala.{Z3Config, Z3Context}


object Main extends App {
  val z3 = new Z3Context(new Z3Config("MODEL" -> true))
  try {
    val p = 2
    println(new ExperimentRunner(z3, 300000).run("1, " + p + ", 1, 1, 1, " + 3 * p + ", 0.5, 0.1", "/Users/jkuo/tools/z3/bin"))
  } finally {
    z3.delete
  }
}

