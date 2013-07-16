package code.lib

import peal.synthesis.analysis._
import peal.domain.z3.{Define, Unsat, Model}
import z3.scala.Z3AST
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.analysis.Different
import peal.synthesis.analysis.Satisfiable
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

object Z3OutputAnalyser {
  def execute(analyses: Map[String, AnalysisGenerator], results: Map[String, Model], constsMap: Map[String, Z3AST]): String = {
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
              out.append("For example, when\n" + getReasons(results(a), Set(), constsMap))
            }
          case s: AlwaysFalse =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is always false")
            }
            else {
              out.append(s.cond + " is NOT always false")
              out.append("For example, when\n" + getReasons(results(a), Set(), constsMap))
            }
          case s: Satisfiable =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is NOT satisfiable")
            }
            else {
              out.append(s.cond + " is satisfiable")
              out.append("For example, when\n" + getReasons(results(a), Set(), constsMap))
            }
          case s: Different =>
            if (results(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are NOT different")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are different")
              out.append("For example, when\n" + getReasons(results(a), Set(s.lhs, s.rhs), constsMap))
            }
          case s: Equivalent =>
            if (results(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are equivalent")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are NOT equivalent")
              out.append("For example, when\n" + getReasons(results(a), Set(s.lhs, s.rhs), constsMap))
            }
        }
    }

    out.mkString("\n")
  }


  private def getReasons(model: Model, includeNames: Set[String], constsMap: Map[String, Z3AST]) = {
    val predicates = for (define: Define <- model.defines if constsMap.contains(define.name)) yield {
      define.name + " is " + define.value
    }

    val conds = for (define: Define <- model.defines if includeNames.contains(define.name)) yield {
      define.name + " is " + define.value
    }

    (predicates ++ conds).mkString("\n")
  }
}
