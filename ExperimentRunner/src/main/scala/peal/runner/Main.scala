package peal.runner

import z3.scala.{Z3Config, Z3Context}


object Main extends App {
  val z3 = new Z3Context(new Z3Config("MODEL" -> true))
  try {
    new ExperimentRunner(z3, 1000).run("10, 5, 4, 3, 2, 7, 0.6, 0.1", "/Users/jkuo/tools/z3/bin")
  } finally {
    z3.delete
  }
}

