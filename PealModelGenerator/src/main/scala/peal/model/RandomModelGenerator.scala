package peal.model

import peal.domain.{Pol, Rule, Predicate}
import scala.collection.JavaConversions._
import scala.util.Random
import peal.domain.operator.Min

object RandomModelGenerator {

  def generate(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {
    var predicates = for (i <- 0 until k) yield (new Predicate("q" + i))
    println(predicates)

    predicates = Random.shuffle(predicates)

    println(predicates)



    val minPolicies = for (j <- 0 until n) yield {

      val rules = for (i <- 0 until m0) yield {
        new Rule(predicates(i), Random.nextDouble())
      }

      new Pol(rules, Min, Random.nextDouble())
    }

    println(minPolicies.toList)
    ""
  }

}
