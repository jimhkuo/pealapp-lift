package peal.model

import peal.domain._
import peal.domain.operator._
import scala.util.Random
import scala.collection.JavaConversions._
import peal.antlr.util.ParserHelper
import peal.domain.Pol
import peal.model.util.LatticeCreator


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

  def createRandomScorePolicies(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int): String = {
    createPolicies(n, m0, m1, m2, m3, k, generateRandomScore)
  }

  def createConstantScorePolicies(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int): String = {
    createPolicies(n, m0, m1, m2, m3, k, generateConstantScore)
  }

  def createPolicySetMatrixWithMinMax(n: Int) = {
    def op(i: Int): String = i % 2 match {
      case 0 => "min"
      case 1 => "max"
    }

    createPolicySetMatrix(n, op)
  }

  def createPolicySetMatrixWithAllFourOperators(n: Int) = {
    def op(i: Int): String = i % 4 match {
      case 0 => "min"
      case 1 => "max"
      case 2 => "+"
      case 3 => "*"
    }

    createPolicySetMatrix(n, op)
  }

  private def createPolicySetMatrix(n: Int, op: Int => String) = {
    val lattice = LatticeCreator.createLattice(n)

    for {
      i <- 0 until lattice.size
      j <- 0 until lattice(i).size
    } yield {
      if (i == 0) {
        ("p" + lattice(i)(j)._1 + "_" + lattice(i)(j)._2, "min(b" + lattice(i)(j)._1 + ",b" + lattice(i)(j)._2 + ")")
      }
      else {
        ("p" + lattice(i)(j)._1 + "_" + lattice(i)(j)._2, op(i) + "(p" + lattice(i)(j)._1 + "_" + lattice(i - 1)(j * 2)._2 + ",p" + lattice(i - 1)(j * 2 + 1)._1 + "_" + lattice(i)(j)._2 + ")")
      }
    }
  }

  def lastSetsWithMinMax(n: Int, top: String) = {
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

  def lastSetsWithAllFourOperators(n: Int, top: String) = {
    val l = (math.log(n * 4) / math.log(2)).floor.toInt
    val end = math.pow(2, l).toInt
    val remainder = for (i <- end until n * 4 by 2) yield {
      i % 4 match {
        case 0 => ("p" + i + "_" + (i + 1), "min(b" + i + ", b" + (i + 1) + ")")
        case 1 => ("p" + i + "_" + (i + 1), "max(b" + i + ", b" + (i + 1) + ")")
        case 2 => ("p" + i + "_" + (i + 1), "+(b" + i + ", b" + (i + 1) + ")")
        case 3 => ("p" + i + "_" + (i + 1), "*(b" + i + ", b" + (i + 1) + ")")
      }
    }
    if (remainder.isEmpty) {
      (top, "")
    }
    else {
      def op(i: Int) = i % 4 match {
        case 0 => "min"
        case 1 => "max"
        case 2 => "+"
        case 3 => "*"
      }

      val lastPairs = remainder.zipWithIndex.foldLeft(List[(String, String)]((top, "")))((acc, r) => acc :+(top + "_" + r._2, top + "_" + r._2 + " = " + op(r._2) + "(" + acc.last._1 + "," + r._1._1 + ")\n")).drop(1)
      val lastBits = lastPairs.map(_._2).mkString("\n", "", "")

      (lastPairs.last._1, remainder.toSeq.map(c => c._1 + " = " + c._2).mkString("\n") + lastBits)
    }
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

  private def generateConstantScore: Score = {
    new Score(Left(BigDecimal.valueOf(Random.nextDouble()).setScale(4, BigDecimal.RoundingMode.HALF_UP)), None)
  }

  private def generateRandomScore: Score = {
    def randomVarName: String = {
      "v" + Random.alphanumeric.take(1).mkString.toLowerCase
    }
    def randomNum: BigDecimal = {
      BigDecimal.valueOf(Random.nextDouble()).setScale(4, BigDecimal.RoundingMode.HALF_UP)
    }
    def randomRange: Option[ScoreRange] = Random.nextInt(10) match {
      case 9 => Some(new ScoreRange(-randomNum, randomNum))
      case _ => None
    }
    Random.nextInt(5) match {
//      case 9 => new Score(Right(VariableFormula(randomVarName)), randomRange)
      case 4 => new Score(Right(VariableFormula(Multiplier(Random.nextInt(4), randomVarName))), randomRange)
      case _ => new Score(Left(randomNum), randomRange)
    }
  }

  private def createPolicies(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, generatedScore: => Score): String = {
    val predicates = (0 until k).map(i => new Predicate("q" + i))

    def createPol(op: Operator, count: Int): Pol = {
      val tempPredicates = Random.shuffle(predicates)
      val rules = (0 until count).map(i => new Rule(tempPredicates(i), generatedScore))
      new Pol(rules, op, generatedScore)
    }

    val minPolicies = Seq.fill(n)(createPol(Min, m0))
    val maxPolicies = Seq.fill(n)(createPol(Max, m1))
    val plusPolicies = Seq.fill(n)(createPol(Plus, m2))
    val mulPolicies = Seq.fill(n)(createPol(Mul, m3))

    val policyList = Random.shuffle(minPolicies ++ maxPolicies ++ plusPolicies ++ mulPolicies)
    val policyMap = (0 until policyList.length).map(i => (i, policyList(i))).toMap
    val policies = for (s <- policyMap.keys.toSeq.sortWith(_ < _)) yield {
      "b" + s + " = " + policyMap(s).toNaturalExpression
    }
    "POLICIES\n" + policies.mkString("\n")
  }
}
