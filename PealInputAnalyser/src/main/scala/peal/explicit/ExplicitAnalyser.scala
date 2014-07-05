package peal.explicit

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.domain.operator._
import peal.synthesis._
import peal.synthesis.analysis._
import peal.util.ConsoleLogger
import peal.verifier.Z3ModelExtractor

import scala.collection.JavaConversions._


class ExplicitAnalyser(input: String) {

  private val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  private val conds = pealProgramParser.conds.toMap
  private val pols = pealProgramParser.pols.toMap
  private val analyses = pealProgramParser.analyses
  private val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  private def extractPolicySet(pSet: PolicySet): List[String] = pSet match {
    case BasicPolicySet(p, n) => extractPolicySet(p)
    case MaxPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case MinPolicySet(l, r, _) => extractPolicySet(l) ::: extractPolicySet(r)
    case Pol(rs, o, s, n) => n :: Nil
  }

  //This only works for explicit synthesis outputs
  private def pullPolicies(cond: Condition): List[String] = cond match {
    case LessThanThCondition(lhs, rhs) => extractPolicySet(lhs)
    case GreaterThanThCondition(lhs, rhs) => extractPolicySet(lhs)
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
    val (ans, reMapped) = new ExplicitOutputVerifier(input).verifyModel(rawModel, analysisName)

    def accumulateScores(operator: Operator, rules: Set[Rule]) = operator match {
      case Min => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc.min(r.numberScore))
      case Max => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc.max(r.numberScore))
      case Plus => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc + r.numberScore)
      case Mul => rules.tail.foldLeft(rules.head.numberScore)((acc, r) => acc * r.numberScore)
    }

    def unfoldPolicy(p: String): String = {
      pols(p) match {
        case Pol(rs, o, s, name) =>
          val okRules = rs.filter(r => I.get(r.q.name) != None && I.get(r.q.name) == Some(Right(PealTrue)))
          val ok = okRules.map(r => r.q.name).mkString("", " ", "")
          val undefinedRules = rs.filter(r => I.get(r.q.name) == None)
          val undefined = undefinedRules.map(r => "(" + r.q.name + "? " + r.numberScore + ")").mkString(" ", " ", "")

          ConsoleLogger.log1(okRules)
          ConsoleLogger.log1(undefinedRules)

          s"$o (([$ok] ${accumulateScores(o, okRules.toSet)})$undefined) default ${s.toString.trim}"

        //          if (okRules.isEmpty) o + " () default " + s.toString.trim
        //          else {
        //            val undefined = if (undefinedRules.nonEmpty) {
        //              undefinedRules.map(r => "(" + r.q.name + "? " + r.numberScore + ")").mkString(" ", " ", "")
        //            }
        //            else {
        //              ""
        //            }
        //            s"$o (([${okRules.map(r => r.q.name).mkString("", " ", "")}] ${accumulateScores(o, okRules.toSet)})${undefined}) default ${s.toString.trim}"
        //          }
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

    //construct the internals of policies here
    "uses " + condNames.mkString("", ", ", "") + "\n" + policies.map(p => p + " = " + unfoldPolicy(p)).mkString("\n")
  }

}
