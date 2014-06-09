package peal.model

import peal.domain.{Rule, Predicate}
import peal.domain.operator._
import scala.util.Random
import scala.collection.JavaConversions._
import peal.domain.Pol
import peal.antlr.util.ParserHelper
import scala.collection.mutable.ListBuffer


trait RandomModelGenerator {

  def generate(doDomainSpecific: Boolean, n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String

  def generate(n: String*): String = {
    generate(n(0).toInt, n(1).toInt, n(2).toInt, n(3).toInt, n(4).toInt, n(5).toInt, n(6).toDouble, n(7).toDouble)
  }

  def generate(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {
    generate(false, n, m0, m1, m2, m3, k, th, delta)
  }

  def generate(doDomainSpecific: Boolean, n: String*): String = {
    generate(doDomainSpecific, n(0).toInt, n(1).toInt, n(2).toInt, n(3).toInt, n(4).toInt, n(5).toInt, n(6).toDouble, n(7).toDouble)
  }

  private def generateConstantScore: String = {
    "%.4f".format(Random.nextDouble())
  }

  def createRangedScorePolicies(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int): String = {
    createPolicies(n, m0, m1, m2, m3, k, generateConstantScore)
  }

  def createConstantScorePolicies(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int): String = {
    createPolicies(n, m0, m1, m2, m3, k, generateConstantScore)
  }

  private def createPolicies(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, f: => String): String = {
    val predicates = (0 until k).map(i => new Predicate("q" + i))

    def createPol(op: Operator, count: Int): Pol = {
      val tempPredicates = Random.shuffle(predicates)
      val rules = (0 until count).map(i => new Rule(tempPredicates(i), generateConstantScore))
      new Pol(rules, op, generateConstantScore)
    }

    val minPolicies = Seq.fill(n)(createPol(Min, m0))
    val maxPolicies = Seq.fill(n)(createPol(Max, m1))
    val plusPolicies = Seq.fill(n)(createPol(Plus, m2))
    val mulPolicies = Seq.fill(n)(createPol(Mul, m3))

    val policyList = Random.shuffle(minPolicies ++ maxPolicies ++ plusPolicies ++ mulPolicies)
    val policyMap = (0 until policyList.length).map(i => (i, policyList(i))).toMap
    val policies = for (s <- policyMap.keys.toSeq.sortWith(_ < _)) yield {
      "b" + s + " = " + policyMap(s).toString
    }
    "POLICIES\n" + policies.mkString("\n")
  }

  def createPolicySetMatrix(n: Int) = {

    val x = n * 4
    val l = (math.log(x) / math.log(2)).floor.toInt
    var m = math.pow(2, l).toInt
    var layer = 0
    val lattice = ListBuffer[Seq[(Int, Int)]]()
    while (m != 1) {
      if (layer == 0) {
        val lhs = for (i <- 0 until m by 2) yield i
        val rhs = for (i <- 0 until m by 2) yield i + 1
        val pairs = lhs.zip(rhs)
        lattice.append(pairs)
        m = pairs.size
      }
      else {
        val lhs = for (i <- 0 until m by 2) yield lattice(layer - 1)(i)._1
        val rhs = for (i <- 0 until m by 2) yield lattice(layer - 1)(i + 1)._2
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
    pSets.flatten.toSeq
  }

  def lastSets(n: Int, top: String) = {
    val l = (math.log(n * 4) / math.log(2)).floor.toInt
    val end = math.pow(2, l).toInt
    val remainder = for (i <- end until n * 4 by 2) yield {
      ("p" + i + "_" + (i + 1), "min(b" + i + ", b" + (i + 1) + ")")
    }
    var finalPolicySet = top
    var ii = -1
    var lastBit = ""
    for (r <- remainder) {

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

    (finalPolicySet, remainder.toSeq.map(c => c._1 + " = " + c._2).mkString("\n") + lastBit)
  }

  def createConditions(finalPSet: String, th: Double, delta: Double): String = {
    val cond1 = "cond1 = " + "%.2f".format(th) + " < " + finalPSet
    val cond2 = "cond2 = " + "%.2f".format(th + delta) + " < " + finalPSet
    "CONDITIONS\n" + cond1 + "\n" + cond2 + "\n"
  }

  def generateDomainSpecifics(p: Int, pealText: String): String = {

    val parser = ParserHelper.getPealParser(pealText)
    parser.program()
    val predicates = parser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSet

    val realDeclaration = for (i <- 0 until p) yield "(declare-const x" + i + " Real)"
    val intDeclaration = for (i <- 0 until p) yield "(declare-const a" + i + " Int)"
    val methodName = "(declare-sort MethodName)\n(declare-fun calledBy (MethodName) Bool)\n(assert (forall ((n MethodName) (m MethodName)) (or (= m n) (implies (calledBy m) (not (calledBy n))))))\n"
    val methodNameDeclaration = for (i <- 0 until p) yield "(declare-const n" + i + " MethodName)"

    val firstLevel = for (i <- 0 until p if predicates.contains("q" + i)) yield "(assert (= q" + i + " (calledBy n" + Random.nextInt(p) + ")))"
    val secondLevel = for (i <- p until 2 * p if predicates.contains("q" + i)) yield "(assert (= q" + i + " (< a" + Random.nextInt(p) + " (+ a" + Random.nextInt(p) + " " + "%.4f".format(Random.nextDouble()) + "))))"
    val thirdLevel = for (i <- 2 * p until 3 * p if predicates.contains("q" + i)) yield "(assert (= q" + i + " (< x" + Random.nextInt(p) + " (* x" + Random.nextInt(p) + " " + "%.4f".format(Random.nextDouble()) + "))))"

    "DOMAIN_SPECIFICS\n" + realDeclaration.mkString("", "\n", "\n") + intDeclaration.mkString("", "\n", "\n") + methodName + methodNameDeclaration.mkString("", "\n", "\n") + firstLevel.mkString("", "\n", "\n") + secondLevel.mkString("", "\n", "\n") + thirdLevel.mkString("", "\n", "\n")
  }

}
