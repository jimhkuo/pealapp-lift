package peal.model

import peal.domain.{Pol, Rule, Predicate}
import scala.collection.JavaConversions._
import scala.util.Random
import peal.domain.operator.{Mul, Plus, Max, Min}

object RandomModelGenerator {

  def generate(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {
    var predicates = for (i <- 0 until k) yield (new Predicate("q" + i))

    val minPolicies = for (j <- 0 until n) yield {
      predicates = Random.shuffle(predicates)
      val rules = for (i <- 0 until m0) yield {
        new Rule(predicates(i), Random.nextDouble())
      }
      new Pol(rules, Min, Random.nextDouble())
    }

    val maxPolicies = for (j <- 0 until n) yield {
      predicates = Random.shuffle(predicates)
      val rules = for (i <- 0 until m1) yield {
        new Rule(predicates(i), Random.nextDouble())
      }
      new Pol(rules, Max, Random.nextDouble())
    }

    val plusPolicies = for (j <- 0 until n) yield {
      predicates = Random.shuffle(predicates)
      val rules = for (i <- 0 until m2) yield {
        new Rule(predicates(i), Random.nextDouble())
      }
      new Pol(rules, Plus, Random.nextDouble())
    }

    val mulPolicies = for (j <- 0 until n) yield {
      predicates = Random.shuffle(predicates)
      val rules = for (i <- 0 until m3) yield {
        new Rule(predicates(i), Random.nextDouble())
      }
      new Pol(rules, Mul, Random.nextDouble())
    }

    val policies = Random.shuffle(minPolicies ++ maxPolicies ++ plusPolicies ++ mulPolicies)

    var i = -1
    val policiesMap = policies.map {
      b =>
        i += 1
        (i, b)
    }.toMap

    val out = for (s <- policiesMap.keys.toSeq.sortWith(_ < _)) yield {
      "b" + s + " = " + policiesMap(s).toString
    }

    out.toSeq.mkString("\n")
  }
}
