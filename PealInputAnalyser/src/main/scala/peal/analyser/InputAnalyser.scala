package peal.analyser

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.domain.operator._
import peal.synthesis._
import peal.synthesis.analysis._
import peal.util.ConsoleLogger
import peal.verifier.util.ScoreEvaluator
import peal.verifier.{OutputVerifier, Z3ModelExtractor}

import scala.collection.JavaConversions._
import scala.xml.{NodeSeq, Node}

class InputAnalyser(input: String) {
  private val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  private val conds = pealProgramParser.conds
  private val pols = pealProgramParser.pols
  private val analyses = pealProgramParser.analyses

  private def extractPolicySet(pSet: PolicySet): List[String] = pSet match {
    case BasicPolicySet(p, n) => extractPolicySet(p)
    case MaxPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case MinPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case PlusPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case MulPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case Pol(rs, o, s, n) => n :: Nil
  }

  private def pullPolicies(cond: Condition): List[String] = cond match {
    case LessThanThCondition(lhs, rhs) => extractPolicySet(lhs) ::: rhs.fold(b => List(), p => extractPolicySet(p))
    case GreaterThanThCondition(lhs, rhs) => extractPolicySet(lhs)::: rhs.fold(b => List(), p => extractPolicySet(p))
    //it turns out the use of either.get is ok here
    case OrCondition(lhs, rhs) => extractPolicySet(conds(lhs).getPol.get) ::: extractPolicySet(conds(rhs).getPol.get)
    case AndCondition(lhs, rhs) => extractPolicySet(conds(lhs).getPol.get) ::: extractPolicySet(conds(rhs).getPol.get)
    case NotCondition(c) => extractPolicySet(conds(c).getPol.get)
  }

  private def pullCond(analysis: AnalysisGenerator) = analysis match {
    case AlwaysTrue(_, c) => c :: Nil
    case AlwaysFalse(_, c) => c :: Nil
    case Satisfiable(_, c) => c :: Nil
    case Different(_, c1, c2) => c1 :: c2 :: Nil
    case Equivalent(_, c1, c2) => c1 :: c2 :: Nil
    case Implies(_, c1, c2) => c1 :: c2 :: Nil
  }

  def analyse(rawModel: String, analysisName: String, additional:Set[String] = Set()): NodeSeq = {

    implicit val I = Z3ModelExtractor.extractIUsingRational(rawModel)(analysisName)
    ConsoleLogger.log1(I)

    def accumulateScores(operator: Operator, rules: Set[Rule], policyName: String): BigDecimal = {
      val rational = operator match {
        case Min => rules.tail.foldLeft(ScoreEvaluator.trueScore(rules.head.score, policyName + "_" + rules.head.q.name + "_U"))((acc, r) => acc.min(ScoreEvaluator.trueScore(r.score, policyName + "_" + r.q.name + "_U")))
        case Max => rules.tail.foldLeft(ScoreEvaluator.trueScore(rules.head.score, policyName + "_" + rules.head.q.name + "_U"))((acc, r) => acc.max(ScoreEvaluator.trueScore(r.score, policyName + "_" + r.q.name + "_U")))
        case Plus => rules.tail.foldLeft(ScoreEvaluator.trueScore(rules.head.score, policyName + "_" + rules.head.q.name + "_U"))((acc, r) => acc + ScoreEvaluator.trueScore(r.score, policyName + "_" + r.q.name + "_U"))
        case Mul => rules.tail.foldLeft(ScoreEvaluator.trueScore(rules.head.score, policyName + "_" + rules.head.q.name + "_U"))((acc, r) => acc * ScoreEvaluator.trueScore(r.score, policyName + "_" + r.q.name + "_U"))
      }

      rational.value
    }

    def specialisePolicy(p: String): Node = {
      pols(p) match {
        case Pol(rs, o, s, name) =>
          val okRules = rs.filter(r => I.get(r.q.name) != None && I.get(r.q.name) == Some(Right(PealTrue)))
          val undefinedRules = rs.filter(r => I.get(r.q.name) == None)
          val undefined = if (undefinedRules.nonEmpty) {
            (if (okRules.nonEmpty) " " else "") + undefinedRules.map(r => "(" + r.q.name + "? " + ScoreEvaluator.trueScore(r.score, p + "_" + r.q.name + "_U").value + ")").mkString(" ")
          }
          else {
            ""
          }

          ConsoleLogger.log1(okRules)

          if (okRules.isEmpty) {
            <span>{p} = {o} ({undefined}) default <span style="font-weight: bold;color:red">{ScoreEvaluator.trueScore(s, p + "_default_U").value}</span><br/></span>
          }
          else {
            <span>{p} = {o} (([{okRules.map(r => r.q.name).mkString("", " ", "")}] <span style="font-weight: bold;color:red">{accumulateScores(o, okRules.toSet, p)}</span>){undefined}) default {ScoreEvaluator.trueScore(s, p + "_default_U").value}<br/></span>
          }
      }
    }

    analyses.foreach(ConsoleLogger.log2(_))

    val condNames = pullCond(analyses(analysisName))
    ConsoleLogger.log2(condNames)
    val bs = for {
      c <- condNames
      b <- pullPolicies(conds(c))
    } yield b

    val policies = bs.toSet
    ConsoleLogger.log2(policies)

    policies.map(p => specialisePolicy(p)).toList.sortWith(_.text < _.text)
  }

}
