package peal.model

import peal.domain.{Pol, Rule, Predicate}
import scala.collection.JavaConversions._
import scala.util.Random
import peal.domain.operator.{Mul, Plus, Max, Min}
import scala.collection.mutable.ListBuffer

object RandomModelGenerator {

  def generate(n: String*): String = {
    generate(n(0).toInt, n(1).toInt, n(2).toInt, n(3).toInt, n(4).toInt, n(5).toInt, n(6).toDouble, n(7).toDouble)
  }

  def generate(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {
    generate(false, n, m0, m1, m2, m3, k, th, delta)
  }

  def generate(doDomainSpecific: Boolean, n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {

    var predicates = (0 until k).map(i => new Predicate("q" + i))

    val minPolicies = (0 until n).map {
      j =>
        predicates = Random.shuffle(predicates)
        val rules = (0 until m0).map {
          i =>
            new Rule(predicates(i), Random.nextDouble())
        }
        new Pol(rules, Min, Random.nextDouble())
    }
    val maxPolicies = (0 until n).map {
      j =>
        predicates = Random.shuffle(predicates)
        val rules = (0 until m1).map {
          i =>
            new Rule(predicates(i), Random.nextDouble())
        }
        new Pol(rules, Max, Random.nextDouble())
    }
    val plusPolicies = (0 until n).map {
      j =>
        predicates = Random.shuffle(predicates)
        val rules = (0 until m2).map {
          i =>
            new Rule(predicates(i), Random.nextDouble())
        }
        new Pol(rules, Plus, Random.nextDouble())
    }
    val mulPolicies = (0 until n).map {
      j =>
        predicates = Random.shuffle(predicates)
        val rules = (0 until m3).map {
          i =>
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
    val l = (math.log(x) / math.log(2)).floor.toInt
    var m = math.pow(2, l).toInt
    var layer = 0
    val lattice = ListBuffer[Seq[(Int, Int)]]()
    while (m != 1) {
      if (layer == 0) {
        val lhs = for (i <- 0 until m by 2) yield (i)
        val rhs = for (i <- 0 until m by 2) yield (i + 1)
        val pairs = lhs.zip(rhs)
        lattice.append(pairs)
        m = pairs.size
      }
      else {
        val lhs = for (i <- 0 until m by 2) yield (lattice(layer - 1)(i)._1)
        val rhs = for (i <- 0 until m by 2) yield (lattice(layer - 1)(i + 1)._2)
        val pairs = lhs.zip(rhs)
        lattice.append(pairs)
        m = pairs.size
      }

      layer += 1
    }
    val pSets = for (i <- 0 until lattice.size) yield {
      val pSet = for (j <- 0 until lattice(i).size) yield {
        if (i == 0) {
          ("p" + lattice(i)(j)._1 + "_" + lattice(i)(j)._2, "min(b" + lattice(i)(j)._1 + ",b" + lattice(i)(j)._2 + ")")
        }
        else {
          val operator = i % 2 match {
            case 0 => "min"
            case 1 => "max"
          }
          ("p" + lattice(i)(j)._1 + "_" + lattice(i)(j)._2, operator + "(p" + lattice(i)(j)._1 + "_" + lattice(i - 1)(j * 2)._2 + ",p" + lattice(i - 1)(j * 2 + 1)._1 + "_" + lattice(i)(j)._2 + ")")
        }
      }

      pSet
    }
    val end = math.pow(2, l).toInt

    val reminder = for (i <- end until x by 2) yield {
      ("p" + i + "_" + (i + 1), "min(b" + i + ", b" + (i + 1) + ")")
    }
    val top = pSets.flatten.toSeq.last._1
    var finalPolicySet = top
    var ii = -1
    var lastBit = ""
    for (r <- reminder) {

      ii += 1
      val out = ii % 2 match {
        case 0 => top + "_" + ii + " = min(" + finalPolicySet + "," + r._1 + ")\n"
        case 1 => top + "_" + ii + " = max(" + finalPolicySet + "," + r._1 + ")\n"
      }
      lastBit += out
      finalPolicySet = top + "_" + ii
    }
    if (!lastBit.isEmpty) {
      lastBit = "\n\n" + lastBit
    }
    val cond1 = "cond1 = " + "%.2f".format(th) + " < " + finalPolicySet
    val cond2 = "cond2 = " + "%.2f".format(th + delta) + " < " + finalPolicySet

    val analyses = "analysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2\n"

    //TODO save the generated peal text and put it through the antlr parser
    //pass the constsMap to the next step
    val domainSpecifics = if (doDomainSpecific) generateDomainSpecifics(k / 3) else ""

    "POLICIES\n" + policies.toSeq.mkString("\n") + "\nPOLICY_SETS\n" + pSets.flatten.toSeq.map(c => c._1 + " = " + c._2).mkString("\n") + "\n\n" + reminder.toSeq.map(c => c._1 + " = " + c._2).mkString("\n") + lastBit + "CONDITIONS\n" + cond1 + "\n" + cond2 + "\n" + domainSpecifics + "ANALYSES\n" + analyses
  }

  private def generateDomainSpecifics(p: Int): String = {
    val realDeclaration = for (i <- 0 until p) yield ("(declare-const x" + i + " Real)")
    val intDeclaration = for (i <- 0 until p) yield ("(declare-const a" + i + " Int)")
    val methodName = "(declare-sort MethodName)\n(declare-fun calledBy (MethodName) Bool)\n(assert (forall ((n MethodName) (m MethodName)) (or (= m n) (implies (calledBy m) (not (calledBy n))))))\n"
    val methodNameDeclaration = for (i <- 0 until p) yield ("(declare-const n" + i + " MethodName)")

    val firstLevel = for (i <- 0 until p) yield ("(assert (= q" + i + " (calledBy n" + Random.nextInt(p) + ")))")
    val secondLevel = for (i <- p until 2*p) yield ("(assert (= q" + i + " (< a" + Random.nextInt(p) + " (+ a" + Random.nextInt(p) + " " + Random.nextDouble() + "))))")
    val thirdLevel = for (i <- 2*p until 3*p) yield ("(assert (= q" + i + " (< x" + Random.nextInt(p) + " (* x" + Random.nextInt(p) + " " + Random.nextDouble() + "))))")

    "DOMAIN_SPECIFICS\n" + realDeclaration.mkString("", "\n", "\n") + intDeclaration.mkString("", "\n", "\n") + methodName + methodNameDeclaration.mkString("", "\n", "\n") + firstLevel.mkString("", "\n", "\n") + secondLevel.mkString("", "\n", "\n") + thirdLevel.mkString("", "\n", "\n")

  }
}
