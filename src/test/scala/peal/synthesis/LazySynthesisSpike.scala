package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import scala.collection.JavaConversions._
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import peal.domain.operator.{Max, Min, Mul, Plus}
import peal.domain._
import peal.domain.BasicPolicySet
import peal.domain.MaxPolicySet
import peal.domain.MinPolicySet
import peal.domain.Pol

class LazySynthesisSpike extends ShouldMatchersForJUnit {

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  //  public Map<String, Pol> pols = new HashMap<String, Pol>();
  //  public Map<String, Condition> conds = new HashMap<String, Condition>();
  //  public Map<String, AnalysisGenerator> analyses = new HashMap<String, AnalysisGenerator>();
  //  public Map<String, PolicySet> pSets = new HashMap<String, PolicySet>();

  @Test
  def testGenerate() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b3 = * ((q7 0.1) (q8 0.2) (q9 0.2)) default 0\n" +
      "b4 = + ((q10 0.1) (q11 0.2) (q12 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(pSet1, b3)\n" +
      "pSet3 = max(pSet2, pSet1)\n" +
      "pSet4 = b4\n" +
      "CONDITIONS\n" +
      "cond1 = 0.5 < pSet3\n" +
      "cond2 = pSet2 <= 0.4\n" +
      "ANALYSES\n" +
      "name1 = always_true? cond1\n"

    println(input)

    val pealProgramParser = getParser(input)
    pealProgramParser.program()

    val pols = pealProgramParser.pols
    val conds = pealProgramParser.conds
    val pSets = pealProgramParser.pSets

    def findAllPolicySets(policySet: PolicySet): Set[String] = policySet match {
      case s: BasicPolicySet => Set(s.pol.asInstanceOf[Pol].name)
      case s: MaxPolicySet => findAllPolicySets(s.lhs) ++ findAllPolicySets(s.rhs)
      case s: MinPolicySet => findAllPolicySets(s.lhs) ++ findAllPolicySets(s.rhs)
    }

    def generateConditionEnforcement(condName: String, bName: String) {
      pols(bName).operator match {
        case Min => println("(assert (= " + condName + "_" + bName + " genMinFormula(" + bName + "))")
        case Max => println("(assert (= " + condName + "_" + bName + " genMaxFormula(" + bName + "))")
        case o =>

          conds(condName) match {
            case s: GreaterThanThCondition =>
              println("(assert (= " + condName + "_" + bName +
                " (or (and (< " + s.th + " " + pols(bName).defaultScore + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))) " +
                " (and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " +
                " (< " + s.th + " (" + o + " " + pols(bName).rules.map(bName + "_" + _.q.name).mkString(" ") + "))))))")
            case s: LessThanThCondition =>
              println("(assert (= " + condName + "_" + bName +
                " (or (and (<= " + " " + pols(bName).defaultScore + " " + s.th + ") (not (or " + pols(bName).rules.map(_.q.name).mkString(" ") + "))) " +
                " (and (or " + pols(bName).rules.map(_.q.name).mkString(" ") + ") " +
                " (<= " + " (" + o + " " + pols(bName).rules.map(bName + "_" + _.q.name).mkString(" ") + ") " + s.th + ")))))")
          }

      }
    }

    def generatePolicySetAssertions(condName: String) {
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

    //generateEffectDeclarations()

    val usedB = for
    (c <- conds;
     b <- findAllPolicySets(conds(c._1).getPol)
     ) yield (b)

    pols.filter(p => usedB.toSet.contains(p._1)).filter(p => p._2.operator == Plus || p._2.operator == Mul).foreach {
      case (name, b) =>
        val unit = if (b.operator == Plus) 0.0 else 1.0
        b.rules.foreach {
          predicate =>
            println("(declare-const " + name + "_" + predicate.q.name + " Real)")
            println("(assert (implies " + predicate.q.name + " (= " + predicate.score + " " + name + "_" + predicate.q.name + ")))")
            println("(assert (implies (not (= " + unit + " " + name + "_" + predicate.q.name + ")) " + predicate.q.name + "))")
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
