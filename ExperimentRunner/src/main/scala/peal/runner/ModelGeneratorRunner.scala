package peal.runner

import peal.model.ConstantScoreModelGenerator

object ModelGeneratorRunner extends App {

  val doDomainSpecifics = args(0).toBoolean
  val n = args(1)
  val min = args(2)
  val max = args(3)
  val plus = args(4)
  val mul = args(5)
  val k = args(6)
  val th = args(7)
  val delta = args(8)

  println(ConstantScoreModelGenerator.generate(doDomainSpecifics, n, min, max, plus, mul, k, th, delta))
}
