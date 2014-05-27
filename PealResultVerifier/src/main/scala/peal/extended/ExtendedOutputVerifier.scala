package peal.extended

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.verifier.Z3ModelExtractor
import peal.domain.{PealTrue, PealFalse, ThreeWayBoolean}
import peal.synthesis.analysis._
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.analysis.Different
import peal.synthesis.analysis.Satisfiable
import peal.synthesis.analysis.Equivalent
import peal.synthesis.{OrCondition, AndCondition, NotCondition, Condition}


class ExtendedOutputVerifier(input: String) {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  def verifyModel(rawModel: String, analysisName: String) {
    val I = Z3ModelExtractor.extractI(rawModel)(analysisName)
    println(I)
    doAnalysis(analysisName, I)
  }

  def doAnalysis(analysisName: String, truthMapping: Map[String, Either[BigDecimal, ThreeWayBoolean]]): ThreeWayBoolean = {
    analyses(analysisName) match {
      case AlwaysTrue(_, condName) =>
        if (truthMapping(condName) == Right(PealFalse)) {
          return cert(conds(condName), truthMapping, truthMapping(condName).right.get)
        }
        throw new RuntimeException(condName + " should be false but is not in " + analysisName)
      case AlwaysFalse(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return cert(conds(condName), truthMapping, truthMapping(condName).right.get)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Satisfiable(_, condName) =>
        if (truthMapping(condName) == Right(PealTrue)) {
          return cert(conds(condName), truthMapping, truthMapping(condName).right.get)
        }
        throw new RuntimeException(condName + " should be true but is not in " + analysisName)
      case Different(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return cert(conds(lhs), truthMapping, truthMapping(lhs).right.get) && cert(conds(rhs), truthMapping, truthMapping(rhs).right.get)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Equivalent(_, lhs, rhs) =>
        if (truthMapping.get(lhs) != truthMapping.get(rhs)) {
          return cert(conds(lhs), truthMapping, truthMapping(lhs).right.get) && cert(conds(rhs), truthMapping, truthMapping(rhs).right.get)
        }
        throw new RuntimeException(lhs + " and " + rhs + " should be different but are not in " + analysisName)
      case Implies(_, lhs, rhs) =>
        if (truthMapping(lhs) == Right(PealTrue) && truthMapping(rhs) == Right(PealFalse)) {
          return cert(conds(lhs), truthMapping, truthMapping(lhs).right.get) && cert(conds(rhs), truthMapping, truthMapping(rhs).right.get)
        }
        throw new RuntimeException(lhs + " should be true and " + rhs + " should be false, but are not in " + analysisName)
      case _ =>
        throw new RuntimeException("shouldn't get here, no matching analysis found")
    }

    throw new RuntimeException("shouldn't get here, no supported analysis specified")
  }

  def cert(cond: Condition, I: Map[String, Either[BigDecimal, ThreeWayBoolean]], v: ThreeWayBoolean): ThreeWayBoolean = {
    cond match {
      case NotCondition(c) => cert(conds(c), I, !v)
      case AndCondition(lhs, rhs) =>
        if (v == PealTrue) {
          cert(conds(lhs), I, v) && cert(conds(rhs), I, v)
        }
        else {
          cert(conds(lhs), I, v) || cert(conds(rhs), I, v)
        }
      case OrCondition(lhs, rhs) =>
        if (v == PealTrue) {
          cert(conds(lhs), I, v) || cert(conds(rhs), I, v)
        }
        else {
          cert(conds(lhs), I, v) && cert(conds(rhs), I, v)
        }
      //TODO two more cases
    }
  }
}
