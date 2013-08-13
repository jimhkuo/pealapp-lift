package peal.lazysynthesis

import _root_.z3.scala.Z3Context
import scala.collection.JavaConversions._
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import peal.domain.operator.{Max, Min, Mul, Plus}
import peal.domain._
import peal.domain.BasicPolicySet
import peal.domain.MaxPolicySet
import peal.domain.MinPolicySet
import peal.domain.Pol
import peal.synthesis.{GreaterThanThCondition, LessThanThCondition}

class LazySynthesiser(z3: Z3Context, input: String) {

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  val pealProgramParser = getParser(input)
  pealProgramParser.program()

  val pols = pealProgramParser.pols
  val conds = pealProgramParser.conds
  val pSets = pealProgramParser.pSets
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct
  val constsMap = predicateNames.toSeq.distinct.map(t => (t, z3.mkBoolConst(t))).toMap
  val analyses = pealProgramParser.analyses.toMap


  private def findAllPolicySets(policySet: PolicySet): Set[String] = policySet match {
    case s: BasicPolicySet => Set(s.pol.asInstanceOf[Pol].name)
    case s: MaxPolicySet => findAllPolicySets(s.lhs) ++ findAllPolicySets(s.rhs)
    case s: MinPolicySet => findAllPolicySets(s.lhs) ++ findAllPolicySets(s.rhs)
  }

  private def generateConditionEnforcement(condName: String, bName: String): String = {
    val buffer = new StringBuilder
    pols(bName).operator match {
      case Min =>
        val genFormula = conds(condName) match {
          case cond: LessThanThCondition =>
            val filtered = pols(bName).rules.filter(_.score <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(or " + filtered.map(_.q.name).mkString(" ") + ")))"
            }
            else {
//              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))))"
              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "false))"

            }
          case cond: GreaterThanThCondition =>
            val filtered = pols(bName).rules.filter(_.score <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " + "(not (or " + filtered.map(_.q.name).mkString(" ") + ")))))"
            }
            else {
//              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))))"
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " + "(not false))))"

            }
        }
        buffer.append("(assert (= " + condName + "_" + bName + " " + genFormula + ")\n")
      case Max =>
        val genFormula = conds(condName) match {
          case cond: LessThanThCondition =>
            val filtered = pols(bName).rules.filter(_.score <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " + "(not (or " + pols(bName).rules.filter(_.score <= cond.getTh).map(_.q.name).mkString(" ") + ")))))"
            }
            else {
//              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))))"
              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " + "(not false))))"

            }
          case cond: GreaterThanThCondition =>
            val filtered = pols(bName).rules.filter(cond.getTh < _.score)
            if (filtered.size > 0) {
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(or " + filtered.map(_.q.name).mkString(" ") + ")))"
            }
            else {
//              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))))"
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "false))"
            }
        }
        buffer.append("(assert (= " + condName + "_" + bName + " " + genFormula + ")\n")
      case o =>
        conds(condName) match {
          case s: GreaterThanThCondition =>
            buffer.append("(assert (= " + condName + "_" + bName +
              " (or (and (< " + s.th + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))) " +
              " (and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " +
              " (< " + s.th + " (" + o + " " + pols(bName).rules.map(bName + "_score_" + _.q.name).mkString(" ") + "))))))\n")
          case s: LessThanThCondition =>
            buffer.append("(assert (= " + condName + "_" + bName +
              " (or (and (<= " + " " + pols(bName).defaultScore + " " + s.th + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))) " +
              " (and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " +
              " (<= " + " (" + o + " " + pols(bName).rules.map(bName + "_score_" + _.q.name).mkString(" ") + ") " + s.th + ")))))\n")
        }
    }
    buffer.toString()
  }

  private  def generatePolicySetAssertions(condName: String) : String = {
    val buffer = new StringBuilder
    conds(condName) match {
      case s: GreaterThanThCondition => // <
        buffer.append("(assert (= " + condName + " " + genPSA("<", s.getPol) + "))\n")
      case s: LessThanThCondition => // >=
        buffer.append("(assert (= " + condName + " " + genPSA("<=", s.getPol) + "))\n")
    }

    def genPSA(operator: String, pSet: PolicySet): String = operator match {
      case "<" =>
        pSet match {
          case s: MaxPolicySet => "(or " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
          case s: MinPolicySet => "(and " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
          case s: BasicPolicySet => condName + "_" + s.pol.asInstanceOf[Pol].name
        }
      case "<=" =>
        pSet match {
          case s: MinPolicySet => "(or " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
          case s: MaxPolicySet => "(and " + genPSA(operator, s.lhs) + " " + genPSA(operator, s.rhs) + ")"
          case s: BasicPolicySet => condName + "_" + s.pol.asInstanceOf[Pol].name
        }
    }

    buffer.toString()
  }

  def generate(): String = {

    val buffer = new StringBuilder

    //generateEffectDeclarations()
    val usedB = for
    (c <- conds;
     b <- findAllPolicySets(conds(c._1).getPol)
    ) yield (b)


    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"

//    pols.filter(p => usedB.toSet.contains(p._1)).foreach {
//      case (bName, b) =>
//        b.rules.foreach {
//          predicate =>
//            buffer.append("(declare-const " + predicate.q.name + " Bool)\n")
//        }
//    }

    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"

    pols.filter(p => usedB.toSet.contains(p._1)).filter(p => p._2.operator == Plus || p._2.operator == Mul).foreach {
      case (bName, b) =>
        val unit = if (b.operator == Plus) 0.0 else 1.0
        b.rules.foreach {
          predicate =>
            buffer.append("(declare-const " + bName + "_score_" + predicate.q.name + " Real)\n")
            buffer.append("(assert (implies " + predicate.q.name + " (= " + predicate.score + " " + bName + "_score_" + predicate.q.name + ")))\n")
            buffer.append("(assert (implies (not (= " + unit + " " + bName + "_score_" + predicate.q.name + ")) " + predicate.q.name + "))\n")
        }
    }

    //generateConditionDeclarations()
    conds.foreach {
      case (name, c) =>
        findAllPolicySets(conds(name).getPol).foreach {
          b =>
            buffer.append("(declare-const " + name + "_" + b + " Bool)\n")
            buffer.append(generateConditionEnforcement(name, b))
        }

        //generatePolicySetAssertions(cop, pSet)
        buffer.append(generatePolicySetAssertions(name))
    }

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {
      "(echo \"Result of analysis [" + analyses(analysis).analysisName + "]:\")\n" + analyses(analysis).z3SMTInput
    }

    declarations.mkString("") + declarations1.mkString("") + buffer.toString() + generatedAnalyses.mkString("")
  }
}
