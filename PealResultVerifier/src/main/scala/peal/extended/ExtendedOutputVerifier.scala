package peal.extended

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.verifier.Z3ModelExtractor
import peal.domain._
import peal.synthesis._
import peal.synthesis.AndCondition
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.analysis.AlwaysTrue
import peal.synthesis.OrCondition
import peal.synthesis.NotCondition
import peal.synthesis.LessThanThCondition
import peal.synthesis.analysis.Different
import peal.synthesis.analysis.Implies
import peal.synthesis.analysis.Satisfiable
import peal.synthesis.analysis.Equivalent

//TODO setting bottom to false in iterations is still required
class ExtendedOutputVerifier(input: String) {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  def verifyModel(rawModel: String, analysisName: String) = {
    val I = Z3ModelExtractor.extractI(rawModel)(analysisName)
//    println("I:")
//    I.foreach(i => println(i._1 + " -> " + i._2.fold(s => s.toString(), b => b.toString)))
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
      case c: LessThanThCondition =>
        if (v == PealTrue) {
          ThreeWayBooleanObj.from(certValue(Right(c.lhs), I) <= certValue(c.rhs, I))
        } else {
          ThreeWayBooleanObj.from(certValue(c.rhs, I) < certValue(Right(c.lhs), I))
        }
      case c: GreaterThanThCondition =>
        if (v == PealTrue) {
          ThreeWayBooleanObj.from(certValue(Right(c.lhs), I) < certValue(c.rhs, I))
        } else {
          ThreeWayBooleanObj.from(certValue(c.rhs, I) <= certValue(Right(c.lhs), I))
        }
      case c: PredicateCondition => I(c.predicateName).right.getOrElse(PealBottom) === v
    }
  }

  //TODO if certValue is not BigDecimal, throw exception for now
  private def certValue(pSet: Either[BigDecimal, PolicySet], I: Map[String, Either[BigDecimal, ThreeWayBoolean]]): BigDecimal = {

    def eval(e: Multiplier): BigDecimal = {
      print("eval: " + e.toZ3Expression)
      val a = e.name match {
        case "" => e.multiplier
        case _ if I(e.name).isLeft => e.multiplier * I(e.name).fold(s => s, vf => -999)
        case _ => throw new RuntimeException("invalid eval case")
      }
      println(" result: " + a)

      a
    }

    //Decide to evaluate score here (instead of in Score) because it has access to I
    def evaluateFormula(vf: VariableFormula): BigDecimal = {
      vf.operations.foldLeft(BigDecimal.valueOf(0.toDouble))(_ + eval(_))
    }

    def extractScore(pSet: PolicySet): BigDecimal = {
      pSet match {
        case BasicPolicySet(pol, name) => extractScore(pol)
        case Pol(rules, op, score, name) =>
          if (rules.exists(r => I.getOrElse(r.q.name, Right(PealBottom)).fold(score => PealBottom, pealBool => pealBool) == PealBottom)) {
            //log
            throw new RuntimeException("Bottom reached in certValue")
          }
          else if (!rules.exists(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue)) {
            println(score.underlyingScore.fold(s => s, f => f.toZ3Expression))
            score.underlyingScore.fold(s => s, f => evaluateFormula(f))
          }
          else {
            val okRules = rules.filter(r => I(r.q.name).fold(score => PealBottom, bool => bool) == PealTrue)
            println(okRules)
//            throw new RuntimeException("op X is not done")
            ???
          }
        case _ => //Deal with other operators
//          throw new RuntimeException("other pSet operators not supported")
          ???
      }
    }

    pSet.fold(score => score, pSet => extractScore(pSet))
  }
}
