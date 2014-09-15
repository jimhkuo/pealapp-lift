package peal.specialisation

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.domain.operator._
import peal.synthesis._
import peal.synthesis.analysis._
import peal.util.ConsoleLogger
import peal.verifier.Z3ModelExtractor
import peal.verifier.util.ScoreEvaluator

import scala.collection.JavaConversions._
import scala.xml.{Node, NodeSeq}

class PolicySpecialisationMaker(input: String) {
  private val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  private val conds = pealProgramParser.conds
  private val pols = pealProgramParser.pols
  private val analyses = pealProgramParser.analyses


  def doIt(rawModel: String, analysisName: String, overrides: Set[String] = Set()): NodeSeq = {

    implicit val I = Z3ModelExtractor.extractIAndStatusUsingRational(rawModel)(analysisName)._2 ++ overrides.map(o => (o, Right(PealFalse)))
    ConsoleLogger.log1(I)

    def pullPoliciesFromScores(scores: Set[Score]): Set[String] = {
      val newPolicyNames = scores.map(_.underlyingScore.fold(b => List(), f => f.toNaturalExpression.split(Array('+', '*')).toList)).flatten
      var pSetNames: Set[String] = newPolicyNames.map(_.toString.trim).map(_.dropRight("_score".length)).filter(pols.containsKey(_)).toSet
      var nextLayerSetNames: Set[String] = pSetNames.map(pols(_)).foldLeft(Set[String]())((acc, pol) => acc ++ extractPolicySet(pol))

      while (nextLayerSetNames != pSetNames) {
        pSetNames = nextLayerSetNames
        nextLayerSetNames = pSetNames.map(pols(_)).foldLeft(Set[String]())((acc, pol) => acc ++ extractPolicySet(pol))
      }
      pSetNames
    }

    def extractPolicySet(pSet: PolicySet)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]]): Set[String] = pSet match {
      case BasicPolicySet(p, n) => extractPolicySet(p)
      case MaxPolicySet(l, r, _) => extractPolicySet(l) ++ extractPolicySet(r)
      case MinPolicySet(l, r, _) => extractPolicySet(l) ++ extractPolicySet(r)
      case PlusPolicySet(l, r, _) => extractPolicySet(l) ++ extractPolicySet(r)
      case MulPolicySet(l, r, _) => extractPolicySet(l) ++ extractPolicySet(r)
      case Pol(rules, _, defaultScore, policyName) =>
        val okScores: Set[Score] = rules.filter(r => I.get(r.q.name) != None && I.get(r.q.name) == Some(Right(PealTrue))).map(_.score).toSet

        if (okScores.isEmpty) {
          pullPoliciesFromScores(Set(defaultScore)) + policyName
        }
        else {
          pullPoliciesFromScores(okScores) + policyName
        }
    }

    def pullPolicies(cond: Condition): Set[String] = cond match {
      case LessThanThCondition(lhs, rhs) => extractPolicySet(lhs) ++ rhs.fold(b => Set(), p => extractPolicySet(p))
      case GreaterThanThCondition(lhs, rhs) => extractPolicySet(lhs) ++ rhs.fold(b => Set(), p => extractPolicySet(p))
      case OrCondition(lhs, rhs) => pullPolicies(conds(lhs)) ++ pullPolicies(conds(rhs))
      case AndCondition(lhs, rhs) => pullPolicies(conds(lhs)) ++ pullPolicies(conds(rhs))
      case NotCondition(c) => pullPolicies(conds(c))
      case PredicateCondition(p) => Set()
    }

    def pullCond(analysis: AnalysisGenerator) = analysis match {
      case AlwaysTrue(_, c) => Set(c)
      case AlwaysFalse(_, c) => Set(c)
      case Satisfiable(_, c) => Set(c)
      case Different(_, c1, c2) => Set(c1, c2)
      case Equivalent(_, c1, c2) => Set(c1, c2)
      case Implies(_, c1, c2) => Set(c1, c2)
    }

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
        case Pol(rules, operator, defaultScore, name) =>
          val okRules = rules.filter(r => I.get(r.q.name) != None && I.get(r.q.name) == Some(Right(PealTrue)))
          val undefinedRules = rules.filter(r => I.get(r.q.name) == None)
          val undefined = if (undefinedRules.nonEmpty) {
            (if (okRules.nonEmpty) " " else "") + undefinedRules.map(r => "(" + r.q.name + "? " + ScoreEvaluator.trueScore(r.score, p + "_" + r.q.name + "_U").value + ")").mkString(" ")
          }
          else {
            ""
          }

          ConsoleLogger.log(okRules)

          if (okRules.isEmpty) {
            <span>{p} = {operator} (<span style="font-weight: bold;color:green">{undefined}</span>) default <span style="font-weight: bold;color:red">
              {ScoreEvaluator.trueScore(defaultScore, p + "_default_U").value}</span><br/></span>
          }
          else {
            <span>{p} = {operator} ((<span style="font-weight: bold;color:red">[</span>{okRules.map(r => r.q.name).mkString("", " ", "")}<span style="font-weight: bold;color:red">]</span>
              <span style="font-weight: bold;color:red">{accumulateScores(operator, okRules.toSet, p)}</span>)<span style="font-weight: bold;color:green">{undefined}</span>) default
              {ScoreEvaluator.trueScore(defaultScore, p + "_default_U").value}<br/></span>
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
