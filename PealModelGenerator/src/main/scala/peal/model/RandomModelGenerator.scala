package peal.model

import peal.domain.{Pol, Rule, Predicate}
import scala.collection.JavaConversions._
import scala.util.Random
import peal.domain.operator.{Mul, Plus, Max, Min}
import scala.collection.mutable.ListBuffer

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

    val policyList = Random.shuffle(minPolicies ++ maxPolicies ++ plusPolicies ++ mulPolicies)

    var i = -1
    val policyMap = policyList.map {
      b =>
        i += 1
        (i, b)
    }.toMap

    val policies = for (s <- policyMap.keys.toSeq.sortWith(_ < _)) yield {
      "b" + s + " = " + policyMap(s).toString
    }

    val x = n * 4

    var m = math.pow(2, math.sqrt(x).toInt).toInt

    println(m)

    val lattice = ListBuffer[Seq[(Int, Int)]]()
    while (m != 1) {
      val lhs = for (i <- 0 until m by 2) yield (i)
      val rhs = for (i <- 1 until m by 2) yield (i)
      val pairs = lhs.zip(rhs)
      lattice.append(pairs)

      m = pairs.size
    }

    val pSets = for (i <- 0 until lattice.size) yield {
        val pSet = for (j <- 0 until lattice(i).size) yield {
          if (i == 0) {
            "p" + lattice(i)(j)._1 + "_" + lattice(i)(j)._2 + " = min(b" + lattice(i)(j)._1 + ",b" + lattice(i)(j)._2 + ")"
          }
          else { //if (j % 2 == 0) {
            "p" + lattice(i-1)(j*2)._1 + "_" + lattice(i-1)((j*2)+1)._2 + " = "
          }
        }

      pSet.mkString("\n")
    }

    policies.toSeq.mkString("\n") + "\n" + pSets.mkString("\n")  + "\n" + lattice.mkString("\n")
  }
}
