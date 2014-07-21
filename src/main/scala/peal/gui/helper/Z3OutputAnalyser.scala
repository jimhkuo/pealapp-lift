package peal.gui.helper

import code.lib.MutableNodeSeq
import peal.antlr.util.ParserHelper
import peal.domain.z3._
import peal.domain.{PealBottom, PealFalse, PealTrue, Rational}
import peal.specialisation.PolicySpecialisationMaker
import peal.synthesis.analysis._
import peal.verifier.{OutputVerifier, Z3ModelValueParser}

import scala.collection.JavaConversions._
import scala.xml.NodeSeq


object Z3OutputAnalyser {

  def execute(analyses: Map[String, AnalysisGenerator], constsMap: Map[String, PealAst], inputPolicies: String, z3RawOutput: String)(implicit ov: OutputVerifier): NodeSeq = {
    val style = "font-family: Monaco, Menlo, Consolas, \"Courier New\", monospace;display: block;padding: 9.5px;margin: 0 0 10px;font-size: 13px;line-height: 1.428571429;color: #333;word-break: break-all;word-wrap: break-word;background-color: #f5f5f5;border: 1px solid #ccc;border-radius: 4px;"

    //TODO do vacuity (always_true?, always_false?) UNSAT check on all conds
    //show above analysis results
    //title: Result of vacuity analyses of all declared conditions
    //Conditions that are always true: UNSAT
    //Conditions that are always false: UNSAT
    //Conditions that may be true: UNKNOWN
    //Conditions that may be false: UNKNOWN
    val z3OutputParser = ParserHelper.getZ3OutputParser(z3RawOutput)
    val z3OutputModels = z3OutputParser.results().toMap

    val alwaysTrueConditions: Iterable[String] = analyses.values.filter(a => a.analysisName.endsWith("_vct") && z3OutputModels(a.analysisName).isUnSat).map{case AlwaysTrue(_, cond) => cond}
    val alwaysFalseConditions: Iterable[String] = analyses.values.filter(a => a.analysisName.endsWith("_vcf") && z3OutputModels(a.analysisName).isUnSat).map{case AlwaysTrue(_, cond) => cond}

    var entireAnalysis : NodeSeq = <p style={style}>
      <h4>Vacuity check on all conditions</h4>
      Conditions that are always true: {alwaysTrueConditions.mkString(",")}<br/>
      Conditions that are always false: {alwaysFalseConditions.mkString(",")}</p>

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    sortedAnalyses.foreach {
      analysisName =>
        val section = MutableNodeSeq()
        section.append(<h4>Result of analysis [
          {analyses(analysisName).analysisName}
          ]</h4>)
        analyses(analysisName) match {
          case s: AlwaysTrue =>
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.cond + " is always true")
            }
            else {
              section.append(s.cond + " is NOT always true, for example, when:")
              section.append(getReasons(z3OutputModels(analysisName), Set(), Set("always_true_", "cond"), constsMap))
            }
          case s: AlwaysFalse =>
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.cond + " is always false")
            }
            else {
              section.append(s.cond + " is NOT always false, for example, when:")
              section.append(getReasons(z3OutputModels(analysisName), Set(), Set("always_false_", "cond"), constsMap))
            }
          case s: Satisfiable =>
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.cond + " is NOT satisfiable")
            }
            else {
              section.append(s.cond + " is satisfiable, for example, when:")
              section.append(getReasons(z3OutputModels(analysisName), Set(), Set("satisfiable_", "cond"), constsMap))
            }
          case s: Different =>
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.lhs + " and " + s.rhs + " are NOT different")
            }
            else {
              section.append(s.lhs + " and " + s.rhs + " are different, for example, when:")
              section.append(getReasons(z3OutputModels(analysisName), Set(s.lhs, s.rhs), Set("different_", "cond"), constsMap))
            }
          case s: Equivalent =>
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.lhs + " and " + s.rhs + " are equivalent")
            }
            else {
              section.append(s.lhs + " and " + s.rhs + " are NOT equivalent, for example, when:")
              section.append(getReasons(z3OutputModels(analysisName), Set(s.lhs, s.rhs), Set("equivalent_", "cond"), constsMap))
            }
          case s: Implies =>
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.lhs + " implies " + s.rhs)
            }
            else {
              section.append(s.lhs + " does not imply " + s.rhs + ", for example, when:")
              section.append(getReasons(z3OutputModels(analysisName), Set(s.lhs, s.rhs), Set("implies_", "cond"), constsMap))
            }
        }

        val cert = if (z3OutputModels(analysisName).satResult == Sat) {
          section.append(<br/>)
          val verifiedModel = ov.verifyModel(z3RawOutput, analysisName)
          val result = verifiedModel._1 match {
            case PealTrue => "succeeded"
            case PealFalse => "failed"
            case PealBottom => "was inconclusive"
          }

          section.append("Certification of analysis [" + analysisName + "] " + result + ". Additional predicates set to false in this certification process are " + verifiedModel._2)
          section.append("Policies checked:")
          verifiedModel._3.foreach(m => section.append(m._1 + " is " + m._2.fold(r => r.value, b => b)))
          section.append("\nPolicies in analysis [" + analysisName + "] specialised with respect to the model extended with false predicates from Set():")
          section.append(<br/>)
          section.append(new PolicySpecialisationMaker(inputPolicies).doIt(z3RawOutput, analysisName, verifiedModel._2))
        }
        else {
          section.append("\nOutput of analysis [" + analysisName + "] is UNSAT: so no certification performed and no specialized policies reported.")
        }
        entireAnalysis = entireAnalysis ++ <p style={style}>
          {section.nodes}
        </p>
    }
    entireAnalysis
  }

  private def getNaturalValue(value: String) = {
    def printValue(r: Rational): String = {
      if (r.denominator != BigDecimal("1")) r.value + " (" + value + ")"
      else r.value.toString()
    }
    try {
      Z3ModelValueParser.parseToRational(value).fold(r => printValue(r), b => b.toString)
    } catch {
      case e: Throwable => value
    }
  }

  private def getReasons(model: Model, includeNames: Set[String], excludeNames: Set[String], constsMap: Map[String, PealAst]) = {
    val assignments = model.assignments.filterNot(_.value == "")
    val predicates = for (define: Assignment <- assignments if constsMap.contains(define.name)) yield {
      define.name + " is " + getNaturalValue(define.value)
    }

    val conds = for (define: Assignment <- assignments if includeNames.contains(define.name)) yield {
      define.name + " is " + getNaturalValue(define.value)
    }

    val additionals = for (define: Assignment <- assignments if !includeNames.contains(define.name) && !constsMap.contains(define.name) && excludeNames.filter(define.name.startsWith(_)).isEmpty) yield {
      define.name + " is " + getNaturalValue(define.value)
    }

    (predicates ++ conds ++ additionals).toList
  }
}
