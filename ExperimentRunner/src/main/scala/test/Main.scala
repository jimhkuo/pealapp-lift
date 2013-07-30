package test

import peal.model.RandomModelGenerator

object Main extends App{

  println("hello from ExperimentRunner")
  println(RandomModelGenerator.generate(10, 5, 4, 3, 2, 7, 0.6, 0.1))

}
