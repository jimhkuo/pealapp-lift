package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import scala.collection.JavaConversions._
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import peal.domain.operator.{Mul, Plus}
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
      "b3 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "b4 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "pSet2 = min(pSet1, b3)\n" +
      "pSet3 = max(pSet2, pSet1)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet3 <= 0.5\n" +
      "ANALYSES\n" +
      "name1 = always_true? cond1\n"

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

    //generateEffectDeclarations()
    pols.filter(p => p._2.operator == Plus || p._2.operator == Mul).foreach {
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
      println(name + ": " + findAllPolicySets(conds(name).getPol))
      //      println("(declare-const " + )
    }
  }


}
