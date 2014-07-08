package code.lib

import peal.analyser.InputAnalyser
import peal.antlr.util.ParserHelper
import peal.domain.z3._
import peal.domain.{PealBottom, PealFalse, PealTrue}
import peal.synthesis.analysis.{AlwaysFalse, AlwaysTrue, Different, Satisfiable, _}
import peal.verifier.{Z3ModelValueParser, OutputVerifier}

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import scala.xml.{Node, NodeSeq}


object Z3OutputAnalyser {
  trait NodeAppender {
    def appendNode(node: Node) : NodeSeq => NodeSeq
  }

  implicit object NodeAppenderObj extends NodeAppender {
    override def appendNode(node: Node) = (nodes : NodeSeq) => {
      nodes :+ node
    }
  }

  implicit def fops(ns: NodeSeq) = new {
    val witness = implicitly[NodeAppender]

    final def append(node: Node): NodeSeq = {
      witness.appendNode(node)(ns)
    }
  }

  object MutableNodeSeq {
    def apply(nodes : NodeSeq) = new MutableNodeSeq(nodes)
    def apply() = new MutableNodeSeq(NodeSeq.Empty)
  }

  class MutableNodeSeq(var nodes: NodeSeq) {
    def append(node: Node) {
      nodes = nodes.append(node)
    }
    def append(s: String) {
      nodes = nodes.append(<span>{s}<br/></span>)
    }
    override def toString = nodes.toString()
  }

  def execute(analyses: Map[String, AnalysisGenerator], constsMap: Map[String, PealAst], inputPolicies: String, z3RawOutput: String)(implicit ov: OutputVerifier): NodeSeq = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(z3RawOutput)
    val z3OutputModels = z3OutputParser.results().toMap
    var entireAnalysis = NodeSeq.Empty

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    sortedAnalyses.foreach {
      a =>
        val section = MutableNodeSeq()
        section.append(<h5>Result of analysis [{analyses(a).analysisName}]</h5>)
        analyses(a) match {
          case s: AlwaysTrue =>
            if (z3OutputModels(a).satResult == Unsat) {
              section.append(s.cond + " is always true")
            }
            else {
              section.append(s.cond + " is NOT always true")
              section.append("For example, when")
              section.append(getReasons(z3OutputModels(a), Set(), Set("always_true_", "cond"), constsMap))
            }
          case s: AlwaysFalse =>
            if (z3OutputModels(a).satResult == Unsat) {
              section.append(s.cond + " is always false")
            }
            else {
              section.append(s.cond + " is NOT always false")
              section.append("For example, when")
              section.append(getReasons(z3OutputModels(a), Set(), Set("always_false_", "cond"), constsMap))
            }
//          case s: Satisfiable =>
//            if (z3OutputModels(a).satResult == Unsat) {
//              section.append(s.cond + " is NOT satisfiable\n")
//            }
//            else {
//              section.append(s.cond + " is satisfiable\n")
//              section.append("For example, when\n" + getReasons(z3OutputModels(a), Set(), Set("satisfiable_", "cond"), constsMap))
//            }
//          case s: Different =>
//            if (z3OutputModels(a).satResult == Unsat) {
//              section.append(s.lhs + " and " + s.rhs + " are NOT different\n")
//            }
//            else {
//              section.append(s.lhs + " and " + s.rhs + " are different\n")
//              section.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("different_", "cond"), constsMap))
//            }
//          case s: Equivalent =>
//            if (z3OutputModels(a).satResult == Unsat) {
//              section.append(s.lhs + " and " + s.rhs + " are equivalent\n")
//            }
//            else {
//              section.append(s.lhs + " and " + s.rhs + " are NOT equivalent\n")
//              section.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("equivalent_", "cond"), constsMap))
//            }
//          case s: Implies =>
//            if (z3OutputModels(a).satResult == Unsat) {
//              section.append(s.lhs + " implies " + s.rhs + "\n")
//            }
//            else {
//              section.append(s.lhs + " does not imply " + s.rhs + "\n")
//              section.append("For example, when\n" + getReasons(z3OutputModels(a), Set(s.lhs, s.rhs), Set("implies_", "cond"), constsMap))
//            }
        }

        val cert = if (z3OutputModels(a).satResult == Sat) {
          section.append(<br/>)
          section.append(<br/>)
          val verifiedModel = ov.verifyModel(z3RawOutput, a)
          val result = verifiedModel._1 match {
            case PealTrue => "succeeded"
            case PealFalse => "failed"
            case PealBottom => "was inconclusive"
          }

          "Certification of analysis [" + a + "] " + result +
            ". Additional predicates set to false in this certification process are " + verifiedModel._2 + "\n\n" +
            "Policies in analysis [" + a + "] specialised with respect to the model extended with false predicates from Set():\n\n" + new InputAnalyser(inputPolicies).analyse(z3RawOutput, a)
        }
        else {
          "\nOutput of analysis [" + a + "] is UNSAT: so no certification performed and no specialized policies reported."
        }

        section.append(<span>{cert}</span>)
        entireAnalysis = entireAnalysis ++ <p>{section.nodes}</p>
    }
    entireAnalysis
  }

  private def getNaturalValue(value: String) = {
    Z3ModelValueParser.parseToRational(value).fold(r => r.value + " (" + value + ")", b => b.toString)
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

    (predicates ++ conds ++ additionals).mkString("\n")
  }
}
