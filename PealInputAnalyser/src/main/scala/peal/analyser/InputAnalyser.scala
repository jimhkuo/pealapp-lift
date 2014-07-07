package peal.analyser

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.domain.operator._
import peal.synthesis._
import peal.synthesis.analysis._
import peal.util.ConsoleLogger
import peal.verifier.{OutputVerifier, Z3ModelExtractor}

import scala.collection.JavaConversions._


class InputAnalyser(input: String)(implicit outputVerifier : OutputVerifier) {

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
    case Pol(rs, o, s, n) => n :: Nil
  }

  //TODO This currently only works for explicit synthesis outputs, need changing for extended synthesis
  private def pullPolicies(cond: Condition) : List[String] = cond match {
      //need to take care of rhs
    case LessThanThCondition(lhs, rhs) => extractPolicySet(lhs)
    case GreaterThanThCondition(lhs, rhs) => extractPolicySet(lhs)
      //can't use either.get
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

  def analyse(rawModel: String, analysisName: String): String = {

    val I = Z3ModelExtractor.extractI(rawModel)(analysisName)
    ConsoleLogger.log1(I)
    val (ans, reMapped) = outputVerifier.verifyModel(rawModel, analysisName)

    //TODO this also used hardcoded behaviour
    def accumulateScores(operator: Operator, rules: Set[Rule]) = operator match {
        //TODO need to evaluate scores even when scores are formulas
      case Min => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc.min(r.numberScore))
      case Max => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc.max(r.numberScore))
      case Plus => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc + r.numberScore)
      case Mul => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc * r.numberScore)
    }

    def specialisePolicy(p: String): String = {
      pols(p) match {
        case Pol(rs, o, s, name) =>
          val okRules = rs.filter(r => I.get(r.q.name) != None && I.get(r.q.name) == Some(Right(PealTrue)))
          val undefinedRules = rs.filter(r => I.get(r.q.name) == None)
          val undefined = if (undefinedRules.nonEmpty) {
            (if (okRules.nonEmpty) " " else "") + undefinedRules.map(r => "(" + r.q.name + "? " + r.numberScore + ")").mkString(" ")
          }
          else {
            ""
          }

          ConsoleLogger.log1(okRules)

          if (okRules.isEmpty) {
            o + " (" + undefined + ") default " + s.toString.trim
          }
          else {
            s"$o (([${okRules.map(r => r.q.name).mkString("", " ", "")}] ${accumulateScores(o, okRules.toSet)})${undefined}) default ${s.toString.trim}"
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

    "uses " + condNames.mkString("", ", ", "") + "\n" + policies.map(p => p + " = " + specialisePolicy(p)).mkString("\n")
  }

}
