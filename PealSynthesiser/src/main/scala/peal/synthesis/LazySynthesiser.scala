package peal.synthesis

import _root_.z3.scala.{Z3Config, Z3Context}
import scala.collection.JavaConversions._
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import peal.domain.operator.{Max, Min, Mul, Plus}
import peal.domain._
import peal.domain.BasicPolicySet
import peal.domain.MaxPolicySet
import peal.domain.MinPolicySet
import peal.domain.Pol

class LazySynthesiser(z3: Z3Context, input: String) {

//  val z3 = new Z3Context(new Z3Config("MODEL" -> true))

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

  private def findAllPolicySets(policySet: PolicySet): Set[String] = policySet match {
    case s: BasicPolicySet => Set(s.pol.asInstanceOf[Pol].name)
    case s: MaxPolicySet => findAllPolicySets(s.lhs) ++ findAllPolicySets(s.rhs)
    case s: MinPolicySet => findAllPolicySets(s.lhs) ++ findAllPolicySets(s.rhs)
  }

  private def generateConditionEnforcement(condName: String, bName: String) {
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
              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))))\n"
            }
          case cond: GreaterThanThCondition =>
            val filtered = pols(bName).rules.filter(_.score <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " + "(not (or " + filtered.map(_.q.name).mkString(" ") + "))))))"
            }
            else {
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))))\n"
            }
        }
        println("(assert (= " + condName + "_" + bName + " " + genFormula + ")")
      case Max =>
        val genFormula = conds(condName) match {
          case cond: LessThanThCondition =>
            val filtered = pols(bName).rules.filter(_.score <= cond.getTh)
            if (filtered.size > 0) {
              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " + "(not (or " + pols(bName).rules.filter(_.score <= cond.getTh).map(_.q.name).mkString(" ") + "))))))"
            }
            else {
              "(or (and (<= " + pols(bName).defaultScore + " " + cond.getTh + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))))\n"
            }
          case cond: GreaterThanThCondition =>
            val filtered = pols(bName).rules.filter(cond.getTh < _.score)
            if (filtered.size > 0) {
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ")))\n" +
                "(or " + filtered.map(_.q.name).mkString(" ") + ")))"
            }
            else {
              "(or (and (< " + cond.getTh + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))))\n"
            }
        }
        println("(assert (= " + condName + "_" + bName + " " + genFormula + ")")
      case o =>
        conds(condName) match {
          case s: GreaterThanThCondition =>
            println("(assert (= " + condName + "_" + bName +
              " (or (and (< " + s.th + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))) " +
              " (and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " +
              " (< " + s.th + " (" + o + " " + pols(bName).rules.map(bName + "_score_" + _.q.name).mkString(" ") + "))))))")
          case s: LessThanThCondition =>
            println("(assert (= " + condName + "_" + bName +
              " (or (and (<= " + " " + pols(bName).defaultScore + " " + s.th + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))) " +
              " (and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " +
              " (<= " + " (" + o + " " + pols(bName).rules.map(bName + "_score_" + _.q.name).mkString(" ") + ") " + s.th + ")))))")
        }
    }
  }

  private  def generatePolicySetAssertions(condName: String) {
    conds(condName) match {
      case s: GreaterThanThCondition => // <
        println("(assert (= " + condName + " " + genPSA("<", s.getPol) + ")")
      case s: LessThanThCondition => // >=
        println("(assert (= " + condName + " " + genPSA("<=", s.getPol) + ")")
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
  }

  def generate() {

    //generateEffectDeclarations()
    val usedB = for
    (c <- conds;
     b <- findAllPolicySets(conds(c._1).getPol)
    ) yield (b)

    pols.filter(p => usedB.toSet.contains(p._1)).filter(p => p._2.operator == Plus || p._2.operator == Mul).foreach {
      case (bName, b) =>
        val unit = if (b.operator == Plus) 0.0 else 1.0
        b.rules.foreach {
          predicate =>
            println("(declare-const " + bName + "_score_" + predicate.q.name + " Real)")
            println("(assert (implies " + predicate.q.name + " (= " + predicate.score + " " + bName + "_score_" + predicate.q.name + ")))")
            println("(assert (implies (not (= " + unit + " " + bName + "_score_" + predicate.q.name + ")) " + predicate.q.name + "))")
        }
    }

    //generateConditionDeclarations()
    conds.foreach {
      case (name, c) =>
        findAllPolicySets(conds(name).getPol).foreach {
          b =>
            println("(declare-const " + name + "_" + b + " Bool)")
            generateConditionEnforcement(name, b)
        }

        //generatePolicySetAssertions(cop, pSet)
        generatePolicySetAssertions(name)
    }
  }
}
