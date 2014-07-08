package code.lib

import peal.analyser.InputAnalyser
import peal.antlr.util.ParserHelper
import peal.domain.{PealBottom, PealFalse, PealTrue}
import peal.synthesis.analysis._
import peal.domain.z3._
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.analysis.Different
import peal.synthesis.analysis.Satisfiable
import peal.verifier.OutputVerifier
import peal.verifier.extended.ExtendedOutputVerifier
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

object Z3OutputAnalyser {
  def execute(analyses: Map[String, AnalysisGenerator], constsMap: Map[String, PealAst], inputPolicies: String, z3RawOutput: String)(implicit ov: OutputVerifier): String = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(z3RawOutput)
    val z3OutputModels = z3OutputParser.results().toMap

    val out = ListBuffer[String]()

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)

    sortedAnalyses.foreach {
      a =>
        out.append("\nResult of analysis [" + analyses(a).analysisName + "]")
        analyses(a) match {
          case s: AlwaysTrue =>
            if (z3OutputModels(a).satResult == Unsat) {
              out.append(s.cond + " is always true")
            }
            else {
              out.append(s.cond + " is NOT always true")
              out.append("For example, when\n" + getReasons(z3OutputModels(a), Set(), Set("always_true_", "cond"), constsMap))
            }
          case s: AlwaysFalse =>
            if (z3OutputModels(a).satResult == Unsat) {
              out.append(s.cond + " is always false")
            }
            else {
              out.append(s.cond + " is NOT always false")
              out.append("For example, when\n" + getReasons(z3OutputModels(a), Set(), Set("always_false_", "cond"), constsMap))
            }
          case s: Satisfiable =>
            if (z3OutputModels(a).satResult == Unsat) {
              out.append(s.cond + " is NOT satisfiable")
            }
            else {
              out.append(s.cond + " is satisfiable")
              out.append("For example, when\n" + getReasons(z3OutputModels(a), Set(), Set("satisfiable_", "cond"), constsMap))
            }
          case s: Different =>
            if (z3OutputModels(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are NOT different")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are different")
              out.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("different_", "cond"), constsMap))
            }
          case s: Equivalent =>
            if (z3OutputModels(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are equivalent")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are NOT equivalent")
              out.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("equivalent_", "cond"), constsMap))
            }
          case s: Implies =>
            if (z3OutputModels(a).satResult == Unsat) {
              out.append(s.lhs + " implies " + s.rhs)
            }
            else {
              out.append(s.lhs + " does not imply " + s.rhs)
              out.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("implies_", "cond"), constsMap))
            }
        }
    }

    val analysesResults = for (analysis <- sortedAnalyses) yield {

      if (z3OutputModels(analysis).satResult == Sat) {
        val verifiedModel = ov.verifyModel(z3RawOutput, analysis)
        val result = verifiedModel._1 match {
          case PealTrue => "succeeded"
          case PealFalse => "failed"
          case PealBottom => "was inconclusive"
        }

        "Certification of analysis [" + analysis + "] " + result +
          ". Additional predicates set to false in this certification process are " + verifiedModel._2 + "\n\n" +
          "Analysis \"" + analysis + "\" " + new InputAnalyser(inputPolicies).analyse(z3RawOutput, analysis)
      }
      else {
        "Analysis \"" + analysis + "\" is UNSAT"
      }
    }
    val verificationResults = "Independent certification of correctness of Z3 model computed for satisfiable analyses:\n\n" + analysesResults.mkString

    out.mkString("\n") + verificationResults
  }


  private def getReasons(model: Model, includeNames: Set[String], excludeNames: Set[String], constsMap: Map[String, PealAst]) = {
    val assignments = model.assignments.filterNot(_.value == "")
    val predicates = for (define: Assignment <- assignments if constsMap.contains(define.name)) yield {
      define.name + " is " + define.value
    }

    val conds = for (define: Assignment <- assignments if includeNames.contains(define.name)) yield {
      define.name + " is " + define.value
    }

    val additionals = for (define: Assignment <- assignments if !includeNames.contains(define.name) && !constsMap.contains(define.name) && excludeNames.filter(define.name.startsWith(_)).isEmpty) yield {
      define.name + " is " + define.value
    }

    (predicates ++ conds ++ additionals).mkString("\n")
  }
}
