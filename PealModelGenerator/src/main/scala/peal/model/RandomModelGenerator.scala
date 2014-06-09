package peal.model

import peal.domain.{Rule, Predicate}
import scala.collection.JavaConversions._
import scala.util.Random
import peal.domain.operator._
import scala.collection.mutable.ListBuffer
import peal.antlr.util.ParserHelper
import peal.domain.Pol

object RandomModelGenerator {

  def generate(n: String*): String = {
    generate(n(0).toInt, n(1).toInt, n(2).toInt, n(3).toInt, n(4).toInt, n(5).toInt, n(6).toDouble, n(7).toDouble)
  }

  def generate(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {
    generate(false, n, m0, m1, m2, m3, k, th, delta)
  }

  def generate(doDomainSpecific: Boolean, n: String*): String = {
    generate(doDomainSpecific, n(0).toInt, n(1).toInt, n(2).toInt, n(3).toInt, n(4).toInt, n(5).toInt, n(6).toDouble, n(7).toDouble)
  }

  def generate(doDomainSpecific: Boolean, n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {

    val predicates = (0 until k).map(i => new Predicate("q" + i))

    def createPol(op: Operator, count: Int): Pol = {
      val tempPredicates = Random.shuffle(predicates)
      val rules = (0 until count).map {
        i =>
          new Rule(tempPredicates(i), "%.4f".format(Random.nextDouble()))
      }
      new Pol(rules, op, "%.4f".format(Random.nextDouble()))
    }

    val minPolicies = Seq.fill(n)(createPol(Min, m0))
    val maxPolicies = Seq.fill(n)(createPol(Max, m1))
    val plusPolicies = Seq.fill(n)(createPol(Plus, m2))
    val mulPolicies = Seq.fill(n)(createPol(Mul, m3))

    val policyList = Random.shuffle(minPolicies ++ maxPolicies ++ plusPolicies ++ mulPolicies)
    val policyMap = ((0 until policyList.length) zip policyList).toMap
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

    val pealText = "POLICIES\n" + policies.toSeq.mkString("\n") + "\nPOLICY_SETS\n" + pSets.flatten.toSeq.map(c => c._1 + " = " + c._2).mkString("\n") + "\n\n" + reminder.toSeq.map(c => c._1 + " = " + c._2).mkString("\n") + lastBit + "CONDITIONS\n" + cond1 + "\n" + cond2 + "\n"
    val analyses = "analysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2\n"
    val domainSpecifics = if (doDomainSpecific) generateDomainSpecifics(k / 3, pealText) else ""

    pealText + domainSpecifics + "ANALYSES\n" + analyses
  }

  private def generateDomainSpecifics(p: Int, pealText: String): String = {

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
