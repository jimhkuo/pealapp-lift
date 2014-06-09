package peal.model

import peal.domain.Predicate
import scala.collection.JavaConversions._
import scala.util.Random
import peal.domain.operator._
import scala.collection.mutable.ListBuffer
import peal.antlr.util.ParserHelper

object RandomModelGenerator extends ModelGenerator {

  def generate(n: String*): String = {
    generate(n(0).toInt, n(1).toInt, n(2).toInt, n(3).toInt, n(4).toInt, n(5).toInt, n(6).toDouble, n(7).toDouble)
  }

  def generate(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {
    generate(false, n, m0, m1, m2, m3, k, th, delta)
  }

  def generate(doDomainSpecific: Boolean, n: String*): String = {
    generate(doDomainSpecific, n(0).toInt, n(1).toInt, n(2).toInt, n(3).toInt, n(4).toInt, n(5).toInt, n(6).toDouble, n(7).toDouble)
  }

}
