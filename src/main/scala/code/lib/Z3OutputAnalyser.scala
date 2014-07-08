package code.lib

import peal.analyser.InputAnalyser
import peal.antlr.util.ParserHelper
import peal.domain.z3._
import peal.domain.{PealBottom, PealFalse, PealTrue}
import peal.synthesis.analysis.{AlwaysFalse, AlwaysTrue, Different, Satisfiable, _}
import peal.verifier.OutputVerifier

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

object Z3OutputAnalyser {
  def execute(analyses: Map[String, AnalysisGenerator], constsMap: Map[String, PealAst], inputPolicies: String, z3RawOutput: String)(implicit ov: OutputVerifier): List[String] = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(z3RawOutput)
    val z3OutputModels = z3OutputParser.results().toMap
    val out = ListBuffer[String]()

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    sortedAnalyses.foreach {
      a =>
        val buffer = new StringBuilder()
        buffer.append("Result of analysis [" + analyses(a).analysisName + "]\n")
        analyses(a) match {
          case s: AlwaysTrue =>
            if (z3OutputModels(a).satResult == Unsat) {
              buffer.append(s.cond + " is always true\n")
            }
            else {
              buffer.append(s.cond + " is NOT always true\n")
              buffer.append("For example, when\n" + getReasons(z3OutputModels(a), Set(), Set("always_true_", "cond"), constsMap))
            }
          case s: AlwaysFalse =>
            if (z3OutputModels(a).satResult == Unsat) {
              buffer.append(s.cond + " is always false")
            }
            else {
              buffer.append(s.cond + " is NOT always false")
              buffer.append("For example, when\n" + getReasons(z3OutputModels(a), Set(), Set("always_false_", "cond"), constsMap))
            }
          case s: Satisfiable =>
            if (z3OutputModels(a).satResult == Unsat) {
              buffer.append(s.cond + " is NOT satisfiable")
            }
            else {
              buffer.append(s.cond + " is satisfiable")
              buffer.append("For example, when\n" + getReasons(z3OutputModels(a), Set(), Set("satisfiable_", "cond"), constsMap))
            }
          case s: Different =>
            if (z3OutputModels(a).satResult == Unsat) {
              buffer.append(s.lhs + " and " + s.rhs + " are NOT different")
            }
            else {
              buffer.append(s.lhs + " and " + s.rhs + " are different")
              buffer.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("different_", "cond"), constsMap))
            }
          case s: Equivalent =>
            if (z3OutputModels(a).satResult == Unsat) {
              buffer.append(s.lhs + " and " + s.rhs + " are equivalent")
            }
            else {
              buffer.append(s.lhs + " and " + s.rhs + " are NOT equivalent")
              buffer.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("equivalent_", "cond"), constsMap))
            }
          case s: Implies =>
            if (z3OutputModels(a).satResult == Unsat) {
              buffer.append(s.lhs + " implies " + s.rhs)
            }
            else {
              buffer.append(s.lhs + " does not imply " + s.rhs)
              buffer.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("implies_", "cond"), constsMap))
            }
        }

        buffer.append("\n\n")
        val cert = if (z3OutputModels(a).satResult == Sat) {
          val verifiedModel = ov.verifyModel(z3RawOutput, a)
          val result = verifiedModel._1 match {
            case PealTrue => "succeeded"
            case PealFalse => "failed"
            case PealBottom => "was inconclusive"
          }

          "Certification of analysis [" + a + "] " + result +
            ". Additional predicates set to false in this certification process are " + verifiedModel._2 + "\n\n" +
            "Policies in analysis [" + a + "] specialised with respect to the model extended with false predicates from Set():\n" + new InputAnalyser(inputPolicies).analyse(z3RawOutput, a)
        }
        else {
          "Analysis \"" + a + "\" is UNSAT"
        }

        buffer.append(cert)
        out.append(buffer.toString())
    }
    out.toList
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
