package code.lib

import peal.synthesis.analysis._
import peal.domain.z3.{PealAst, Assignment, Unsat, Model}
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.analysis.Different
import peal.synthesis.analysis.Satisfiable
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

object Z3OutputAnalyser {
  def execute(analyses: Map[String, AnalysisGenerator], results: Map[String, Model], constsMap: Map[String, PealAst]): String = {
    val out = ListBuffer[String]()

    analyses.keys.toSeq.sortWith(_ < _).foreach {
      a =>
        out.append("\nResult of analysis [" + analyses(a).analysisName + "]")
        analyses(a) match {
          case s: AlwaysTrue =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is always true")
            }
            else {
              out.append(s.cond + " is NOT always true")
              out.append("For example, when\n" + getReasons(results(a), Set(), Set("always_true_", "cond"), constsMap))
            }
          case s: AlwaysFalse =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is always false")
            }
            else {
              out.append(s.cond + " is NOT always false")
              out.append("For example, when\n" + getReasons(results(a), Set(), Set("always_false_", "cond"), constsMap))
            }
          case s: Satisfiable =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is NOT satisfiable")
            }
            else {
              out.append(s.cond + " is satisfiable")
              out.append("For example, when\n" + getReasons(results(a), Set(), Set("satisfiable_", "cond"), constsMap))
            }
          case s: Different =>
            if (results(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are NOT different")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are different")
              out.append("For example, when\n" + getReasons(results(a), Set(s.lhs, s.rhs), Set("different_", "cond"), constsMap))
            }
          case s: Equivalent =>
            if (results(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are equivalent")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are NOT equivalent")
              out.append("For example, when\n" + getReasons(results(a), Set(s.lhs, s.rhs), Set("equivalent_", "cond"), constsMap))
            }
          case s: Implies =>
            if (results(a).satResult == Unsat) {
              out.append(s.lhs + " implies " + s.rhs)
            }
            else {
              out.append(s.lhs + " does not imply " + s.rhs)
              out.append("For example, when\n" + getReasons(results(a), Set(s.lhs, s.rhs), Set("implies_", "cond"), constsMap))
            }
        }
    }

    out.mkString("\n")
  }


  private def getReasons(model: Model, includeNames: Set[String], excludeNames: Set[String], constsMap: Map[String, PealAst]) = {
    val predicates = for (define: Assignment <- model.assignments if constsMap.contains(define.name)) yield {
      define.name + " is " + define.value
    }

    val conds = for (define: Assignment <- model.assignments if includeNames.contains(define.name)) yield {
      define.name + " is " + define.value
    }

    val additionals = for (define: Assignment <- model.assignments if !includeNames.contains(define.name) && !constsMap.contains(define.name) && excludeNames.filter(define.name.startsWith(_)).isEmpty) yield {
      define.name + " is " + define.value
    }

//    (predicates ++ conds).mkString("\n")
    (predicates ++ conds ++ additionals).mkString("\n")
  }
}
