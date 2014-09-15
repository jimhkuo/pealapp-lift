package peal.verifier.util

import peal.domain._
import peal.util.ConsoleLogger

import scala.collection.JavaConverters._

object ScoreEvaluator {

  def trueScore(score: Score, rangeVarName: String)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String = x => x.name, reportSpecialCase: String => Unit = x => ()): Rational = {

    def eval(e: Multiplier): Rational = {
      ConsoleLogger.log("eval: " + e.toNaturalExpression + " e.name is " + e.name + " e.multiplier is " + e.multiplier + " " + rangeVarName)
      val out = e.name match {
        case s if s.contains("_score") => ConsoleLogger.log1("********* ScoreEvaluator, if s.contains(\"_score\")"); Rational(e.multiplier.toString()) * I(multiplierNamePurger(e)).left.get
        case s if I.contains(s) && I(s).isLeft => ConsoleLogger.log1("********* ScoreEvaluator, if I.contains(s) && I(s).isLeft"); Rational(e.multiplier.toString()) * I(s).left.get
        case "" => ConsoleLogger.log1("********* ScoreEvaluator, \"\""); Rational(e.multiplier.toString())
        case s if e.multiplier == BigDecimal("0") => Rational("0") // if multiplier is 0, we don't care what the value of I(name) is
        //        case s if I.contains(s) && I(s).isRight => //in this case, I(e) is a Boolean value, we should never reach here, leave it for the code to blow up
        //        case s => //intentionally leave this case out for the code to blow up if it ever reaches this point
      }

      ConsoleLogger.log("eval:" + multiplierNamePurger(e) + " = " + out + ", it has multiplier " + Rational(e.multiplier.toString()))

      out
    }

    def evaluateFormula(vf: VariableFormula): Rational = {
      vf.operations.foldLeft(Rational("0"))((acc, e) => acc + eval(e))
    }

    score.scoreRange match {
      case None => score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f))
      case Some(_) => score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f)) + I(rangeVarName).fold(s => s, vf => throw new RuntimeException("illegal variable format"))
    }
  }

  def trueScoreOption(score: Score, rangeVarName: String)
                     (implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String = x => x.name, reportSpecialCase: String => Unit = x => ()): Option[Rational] = {

    def eval(e: Multiplier): Option[Rational] = {
      ConsoleLogger.log("eval: " + e.toNaturalExpression + " e.name is " + e.name + " e.multiplier is " + e.multiplier + " " + rangeVarName)
      val out = e.name match {
        case s if s.contains("_score") => ConsoleLogger.log1("********* ScoreEvaluator, if s.contains(\"_score\")");
          Some(Rational(e.multiplier.toString()) * I(multiplierNamePurger(e)).left.get)
        case s if I.contains(s) && I(s).isLeft => ConsoleLogger.log1("********* ScoreEvaluator, if I.contains(s) && I(s).isLeft");
          Some(Rational(e.multiplier.toString()) * I(s).left.get)
        case "" => ConsoleLogger.log1("********* ScoreEvaluator, \"\""); Some(Rational(e.multiplier.toString()))
        case s if e.multiplier == BigDecimal("0") => Some(Rational("0")) // if multiplier is 0, we don't care what the value of I(name) is
        case _ => None
      }

      ConsoleLogger.log("eval:" + multiplierNamePurger(e) + " = " + out + ", it has multiplier " + Rational(e.multiplier.toString()))

      out
    }

    def sum(lhs: Option[Rational], rhs: Option[Rational]): Option[Rational] = lhs match {
      case None => None
      case Some(l) => rhs match {
        case None => None
        case Some(r) => Some(l + r)
      }
    }

    def evaluateFormula(vf: VariableFormula): Option[Rational] = {
      println(vf.toNaturalExpression)
      var acc: Option[Rational] = Some(Rational("0"))
      for (e <- vf.operations) {
        acc = sum(acc, eval(e))
      }

      acc
    }

    score.scoreRange match {
      case None => score.underlyingScore.fold(s => Some(Rational(s.toString())), f => evaluateFormula(f))
      //      case Some(_) => score.underlyingScore.fold(s => Some(Rational(s.toString())), f => evaluateFormula(f)) + I(rangeVarName).fold(s => s, vf => throw new RuntimeException("illegal variable format"))
    }
  }
}
