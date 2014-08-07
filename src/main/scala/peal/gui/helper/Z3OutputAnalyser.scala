package peal.gui.helper

import code.lib.{DecimalFormat, RationalFormat, DnOption, MutableNodeSeq}
import peal.antlr.util.ParserHelper
import peal.domain.z3._
import peal.domain.{PealBottom, PealFalse, PealTrue, Rational}
import peal.expression.ConditionExpressionBuilder
import peal.specialisation.PolicySpecialisationMaker
import peal.synthesis.Condition
import peal.synthesis.analysis._
import peal.verifier.{OutputVerifier, Z3ModelValueParser}

import scala.collection.JavaConversions._
import scala.xml.NodeSeq


object Z3OutputAnalyser {

  def execute(analyses: Map[String, AnalysisGenerator], conditions: Map[String, Condition],  constsMap: Map[String, PealAst], inputPolicies: String, z3RawOutput: String)(implicit ov: OutputVerifier): NodeSeq = {
    val style = "font-family: Monaco, Menlo, Consolas, \"Courier New\", monospace;display: block;padding: 9.5px;margin: 0 0 10px;font-size: 13px;line-height: 1.428571429;color: #333;word-break: break-all;word-wrap: break-word;background-color: #f5f5f5;border: 1px solid #ccc;border-radius: 4px;"

    val z3OutputParser = ParserHelper.getZ3OutputParser(z3RawOutput)
    val z3OutputModels: Map[String, Model] = z3OutputParser.results().toMap

    val alwaysTrueConditions = z3OutputModels.filter(m => m._1.endsWith("_vct") && m._2.isUnSat).map(_._1.dropRight("_vct".length)).toSeq.sorted
    val alwaysFalseConditions = z3OutputModels.filter(m => m._1.endsWith("_vcf") && m._2.isUnSat).map(_._1.dropRight("_vcf".length)).toSeq.sorted

    val vacuityCheckBlock = z3OutputModels.keySet.exists(k => k.endsWith("_vct") || k.endsWith("_vcf")) match {
      case true => <div style={style}>
        <h4>Vacuity check on all conditions declared in CONDITIONS section above</h4>
        Conditions that are always true:
        {alwaysTrueConditions.mkString(", ")}<br/>
        Conditions that are always false:
        {alwaysFalseConditions.mkString(", ")}
      </div>
      case false => <span/>
    }

    var entireAnalysis: NodeSeq = vacuityCheckBlock

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    sortedAnalyses.foreach {
      analysisName =>
        val section = MutableNodeSeq()
        section.append(<h4>Result of analysis [{analyses(analysisName).analysisName}]</h4>)
        analyses(analysisName) match {
          case s: AlwaysTrue =>
            section.append(s.cond + " is " + ConditionExpressionBuilder.build(s.cond)(conditions))
            section.append(<br/>)
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.cond + " is always true")
            } else if (z3OutputModels(analysisName).satResult == Unknown) {
              section.append("Z3 could not determine whether " + s.cond + " is always true or not.")
            }
            else {
              section.append(s.cond + " is NOT always true, for example, in the scenario in which:")
              section.append(getReasons(z3OutputModels(analysisName), Set(), Set("always_true_", "cond"), constsMap))
            }
          case s: AlwaysFalse =>
            section.append(s.cond + " is " + ConditionExpressionBuilder.build(s.cond)(conditions))
            section.append(<br/>)
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.cond + " is always false")
            }
            else if (z3OutputModels(analysisName).satResult == Unknown) {
              section.append("Z3 could not determine whether " + s.cond + " is always false or not.")
            }
            else {
              section.append(s.cond + " is NOT always false, for example, in the scenario in which:")
              section.append(getReasons(z3OutputModels(analysisName), Set(), Set("always_false_", "cond"), constsMap))
            }
          case s: Satisfiable =>
            section.append(s.cond + " is " + ConditionExpressionBuilder.build(s.cond)(conditions))
            section.append(<br/>)
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.cond + " is NOT satisfiable")
            }
            else if (z3OutputModels(analysisName).satResult == Unknown) {
              section.append("Z3 could not determine whether " + s.cond + " is satisfiable or not.")
            }
            else {
              section.append(s.cond + " is satisfiable, for example, in the scenario in which:")
              section.append(getReasons(z3OutputModels(analysisName), Set(), Set("satisfiable_", "cond"), constsMap))
            }
          case s: Different =>
            section.append(s.lhs + " is " + ConditionExpressionBuilder.build(s.lhs)(conditions) + " and " +
              s.rhs + " is " + ConditionExpressionBuilder.build(s.rhs)(conditions))
            section.append(<br/>)
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.lhs + " and " + s.rhs + " are NOT different")
            }
            else if (z3OutputModels(analysisName).satResult == Unknown) {
              section.append("Z3 could not determine whether " + s.lhs + " and " + s.rhs + " are different or not.")
            }
            else {
              section.append(s.lhs + " and " + s.rhs + " are different, for example, in the scenario in which:")
              section.append(getReasons(z3OutputModels(analysisName), Set(s.lhs, s.rhs), Set("different_", "cond"), constsMap))
            }
          case s: Equivalent =>
            section.append(s.lhs + " is " + ConditionExpressionBuilder.build(s.lhs)(conditions) + " and " +
              s.rhs + " is " + ConditionExpressionBuilder.build(s.rhs)(conditions))
            section.append(<br/>)
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.lhs + " and " + s.rhs + " are equivalent")
            } else if (z3OutputModels(analysisName).satResult == Unknown) {
              section.append("Z3 could not determine whether " + s.lhs + " and " + s.rhs + " are equivalent or not.")
            }
            else {
              section.append(s.lhs + " and " + s.rhs + " are NOT equivalent, for example, in the scenario in which:")
              section.append(getReasons(z3OutputModels(analysisName), Set(s.lhs, s.rhs), Set("equivalent_", "cond"), constsMap))
            }
          case s: Implies =>
            section.append(s.lhs + " is " + ConditionExpressionBuilder.build(s.lhs)(conditions) + " and " +
              s.rhs + " is " + ConditionExpressionBuilder.build(s.rhs)(conditions))
            section.append(<br/>)
            if (z3OutputModels(analysisName).satResult == Unsat) {
              section.append(s.lhs + " implies " + s.rhs)
            }
            else if (z3OutputModels(analysisName).satResult == Unknown) {
              section.append("Z3 could not determine whether " + s.lhs + " implies " + s.rhs + " or not.")
            }
            else {
              section.append(s.lhs + " does not imply " + s.rhs + ", for example, in the scenario in which:")
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

          section.append("Certification of analysis [" + analysisName + "] " + result + ".")
          if (verifiedModel._2.nonEmpty) section.append("Additional predicates set to false in this certification process are: " + verifiedModel._2)
          if (verifiedModel._4.nonEmpty) section.append("Variables not defined in the Z3 model but are assumed to be 0 in this certification process are: " + verifiedModel._4)
          section.append(<br/>)
          section.append("Policy scores statically inferred in this certification process:")
          verifiedModel._3.map(m => m._1 + " has score " + m._2.fold(r => displayScore(r), b => b)).toSeq.sorted.foreach(section.append)
          section.append(<br/>)
          if (verifiedModel._2.nonEmpty) {
            section.append("Policies in analysis [" + analysisName + "] specialised with respect to the above scenario, extended with false predicates from ")
            section.append("" + verifiedModel._2 + ":")
          }
          else section.append("Policies in analysis [" + analysisName + "] specialised with respect to the above scenario:")
          section.append(new PolicySpecialisationMaker(inputPolicies).doIt(z3RawOutput, analysisName, verifiedModel._2))
        }
        else {
          section.append("\nOutput of analysis [" + analysisName + "] is " + z3OutputModels(analysisName).satResult + ": so no certification performed and no specialized policies reported.")
        }
        entireAnalysis = entireAnalysis ++ <p style={style}>
          {section.nodes}
        </p>
    }
    entireAnalysis
  }

  private def displayScore(r: Rational) = DnOption.get match {
//    case RationalFormat => if (r.denominator != BigDecimal("1")) r.numerator + "/" + r.denominator else r.value.toString()
//    case DecimalFormat => r.value.toString()
//    case _ => if (r.denominator != BigDecimal("1")) r.numerator + "/" + r.denominator + " = " + r.value + "" else r.value.toString()
    case _ => r.value.toString()
  }

  private def getNaturalValue(originalString: String) = {

    def printValue(r: Rational): String = DnOption.get match {
      case RationalFormat => originalString
      case DecimalFormat => r.value.toString()
      case _ => if (r.denominator != BigDecimal("1")) originalString + " = " + r.value + "" else r.value.toString()
    }

    try {
      Z3ModelValueParser.parseToRational(originalString).fold(r => printValue(r), b => b.toString)
    } catch {
      case e: Throwable => originalString
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
