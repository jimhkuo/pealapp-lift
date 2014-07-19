package peal.synthesis

import scala.collection.JavaConversions._
import peal.domain.operator._
import peal.domain._
import peal.antlr.util.ParserHelper
import peal.domain.BasicPolicySet
import peal.domain.MinPolicySet
import peal.domain.MaxPolicySet
import peal.domain.Pol
import scala.Some

class LazySynthesiser(input: String) extends Synthesiser{

  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val pols = pealProgramParser.pols
  val conds = pealProgramParser.conds
  val pSets = pealProgramParser.pSets
  val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules)
  val predicateNames = allRules.map(r => r.q.name).toSet
  val nonConstantDefaultScores = pols.foldLeft(Set[String]())((acc, tuple) => {
    tuple._2 match {
      case p: Pol =>
        def addVariables(set: Set[String]) = p.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
        addVariables(acc)
      case _ => acc
    }
  })
  val nonConstantScores = allRules.foldLeft(Set[String]())((acc, rule) => {
    def addVariables(set: Set[String]) = rule.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
    addVariables(acc)
  })
  val analyses = pealProgramParser.analyses.toMap

  private def findAllPolicySets(policySet: Option[PolicySet]): Set[String] = {
    policySet.fold(Set[String]())(_ match {
      case t: BasicPolicySet => Set(t.underlyingPolicyName)
      case t: MaxPolicySet => findAllPolicySets(Some(t.lhs)) ++ findAllPolicySets(Some(t.rhs))
      case t: MinPolicySet => findAllPolicySets(Some(t.lhs)) ++ findAllPolicySets(Some(t.rhs))
    })
  }

  private def generateConditionEnforcement(condName: String, bName: String): String = {
    val buffer = new StringBuilder
    pols(bName).operator match {
      case Min =>
        val genFormula = conds(condName) match {
            //if we support pSet < pSet, then cond type is no longer associated with cond name
          case cond: LessThanThCondition =>
            val filtered = pols(bName).rules.filter(_.numberScore <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (<= " + pols(bName).scoreString + " " + cond.getTh + ") (not " + constructOr(pols(bName).rules.map(_.q.name))  + "))\n" +
                "(or " + filtered.map(_.q.name).mkString(" ") + ")))"
            }
            else {
              "(or (and (<= " + pols(bName).scoreString + " " + cond.getTh + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + "))\n" +
                "false))"

            }
          case cond: GreaterThanThCondition =>
            val filtered = pols(bName).rules.filter(_.numberScore <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (< " + cond.getTh + " " + pols(bName).scoreString + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + "))\n" +
                "(and " + constructOr(pols(bName).rules.map(_.q.name)) + " " + "(not (or " + filtered.map(_.q.name).mkString(" ") + ")))))"
            }
            else {
              "(or (and (< " + cond.getTh + " " + pols(bName).scoreString + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + "))\n" +
                "(and " + constructOr(pols(bName).rules.map(_.q.name)) + " (not false))))"
            }
        }
        buffer.append("(assert (= " + condName + "_" + bName + " " + genFormula + ")\n")
      case Max =>
        val genFormula = conds(condName) match {
          case cond: LessThanThCondition =>
            val filtered = pols(bName).rules.filter(_.numberScore <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (<= " + pols(bName).scoreString + " " + cond.getTh + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + "))\n" +
                "(and " + constructOr(pols(bName).rules.map(_.q.name)) + " (not " + "(or " + filtered.map(_.q.name).mkString(" ") + ")" + "))))"
            }
            else {
              "(or (and (<= " + pols(bName).scoreString + " " + cond.getTh + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + "))\n" +
                "(and " + constructOr(pols(bName).rules.map(_.q.name)) + " (not false))))"
            }
          case cond: GreaterThanThCondition =>
            val filtered = pols(bName).rules.filter(cond.getTh < _.numberScore)
            if (filtered.size > 0) {
              "(or (and (< " + cond.getTh + " " + pols(bName).scoreString + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + "))\n" +
                "(or " + filtered.map(_.q.name).mkString(" ") + ")))"
            }
            else {
              "(or (and (< " + cond.getTh + " " + pols(bName).scoreString + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + "))\n" +
                "false))"
            }
        }
        buffer.append("(assert (= " + condName + "_" + bName + " " + genFormula + ")\n")
      case plusOrMul =>
        conds(condName) match {
          case cond: GreaterThanThCondition =>
            buffer.append("(assert (= " + condName + "_" + bName +
              " (or (and (< " + cond.getTh + " " + pols(bName).scoreString + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + ")) " +
              " (and " + constructOr(pols(bName).rules.map(_.q.name)) + " " +
              " (< " + cond.getTh + " " + ruleAssert(plusOrMul, bName) + ")))))\n")
          case cond: LessThanThCondition =>
            buffer.append("(assert (= " + condName + "_" + bName +
              " (or (and (<= " + pols(bName).scoreString + " " + cond.getTh + ") (not " + constructOr(pols(bName).rules.map(_.q.name)) + ")) " +
              " (and " + constructOr(pols(bName).rules.map(_.q.name)) + " " +
              " (<= " + ruleAssert(plusOrMul, bName) + " " + cond.getTh + ")))))\n")
        }
    }
    buffer.toString()
  }

  private def constructOr(seq: Seq[String]) = seq.size match {
    case 0 => "false"
    case _ => "(or " + seq.mkString(" ") + ")"
  }

  private def ruleAssert(op: Operator, bName: String) = op match {
    case Plus => pols(bName).rules.size match {
      case 0 => "0.0"
      case _ => "(+ " + pols(bName).rules.map(bName + "_score_" + _.q.name).mkString(" ") + ")"
    }
    case Mul => pols(bName).rules.size match {
      case 0 => "1.0"
      case _ => "(* " + pols(bName).rules.map(bName + "_score_" + _.q.name).mkString(" ") + ")"
    }
  }

  private def generatePolicySetAssertions(condName: String): String = {
    val buffer = new StringBuilder
    conds(condName) match {
      case condition: GreaterThanThCondition => buffer.append("(assert (= " + condName + " " + genPSA("<", condition.getPol.get) + "))\n")
      case condition: LessThanThCondition => buffer.append("(assert (= " + condName + " " + genPSA("<=", condition.getPol.get) + "))\n")
      case condition: NotCondition => buffer.append("(assert (= " + condName + " " + condition.synthesis(null) + "))\n")
      case condition: AndCondition => buffer.append("(assert (= " + condName + " " + condition.synthesis(null) + "))\n")
      case condition: OrCondition => buffer.append("(assert (= " + condName + " " + condition.synthesis(null) + "))\n")
      case condition: TrueCondition => buffer.append("(assert (= " + condName + " " + condition.synthesis(null) + "))\n")
      case condition: FalseCondition => buffer.append("(assert (= " + condName + " " + condition.synthesis(null) + "))\n")
    }

    def genPSA(operator: String, pSet: PolicySet): String = pSet match {
      case s: MaxPolicySet => operator match {
        case "<" => "(or " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
        case "<=" => "(and " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
      }
      case s: MinPolicySet => operator match {
        case "<" => "(and " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
        case "<=" => "(or " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
      }
      case s: BasicPolicySet => condName + "_" + s.underlyingPolicyName
    }

    buffer.toString()
  }

  def generate(): String = {
    val buffer = new StringBuilder
    val usedB = for
    (c <- conds;
     b <- findAllPolicySets(conds(c._1).getPol)
    ) yield b

    val declarations = for (name <- predicateNames) yield "(declare-const " + name + " Bool)\n"
    val variableDeclarations = for (name <- nonConstantScores) yield "(declare-const " + name + " Real)\n"
    val nonConstantScoreDeclarations = for (name <- nonConstantDefaultScores) yield "(declare-const " + name + " Real)\n"
    val condDeclarations = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"

    pols.filter(p => usedB.toSet.contains(p._1)).filter(p => p._2.operator == Plus || p._2.operator == Mul).foreach {
      case (bName, b) =>
        val unit = if (b.operator == Plus) 0.0 else 1.0
        b.rules.foreach {
          predicate =>
            buffer.append("(declare-const " + bName + "_score_" + predicate.q.name + " Real)\n")
            buffer.append("(assert (implies " + predicate.q.name + " (= " + predicate.score.underlyingScore.fold(score => score.toString(), variable => variable.toZ3Expression) + " " + bName + "_score_" + predicate.q.name + ")))\n")
            buffer.append("(assert (implies (not (= " + unit + " " + bName + "_score_" + predicate.q.name + ")) " + predicate.q.name + "))\n")
        }
    }

    conds.keys.foreach {
      case name =>
        findAllPolicySets(conds(name).getPol).foreach {
          b =>
            buffer.append("(declare-const " + name + "_" + b + " Bool)\n")
            buffer.append(generateConditionEnforcement(name, b))
        }

        buffer.append(generatePolicySetAssertions(name))
    }

    val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1)

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {
      "(echo \"Result of analysis [" + analyses(analysis).analysisName + "]:\")\n" + analyses(analysis).z3SMTInput
    }

    declarations.mkString("") + variableDeclarations.mkString("") + nonConstantScoreDeclarations.mkString("") + condDeclarations.mkString("") + buffer.toString() + domainSpecifics.mkString("", "\n", "\n") + generatedAnalyses.mkString("")
  }
}
