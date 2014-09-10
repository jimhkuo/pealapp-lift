package peal.verifier.util

import peal.domain._
import peal.util.ConsoleLogger

object ScoreEvaluator {

  def trueScore(score: Score, rangeVarName: String)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String = x => x.name, reportSpecialCase: String => Unit = x => ()): Rational = {

    def eval(e: Multiplier): Rational = {
      ConsoleLogger.log1("eval: " + e.toNaturalExpression + " e.name is " + e.name + " e.multiplier is " + e.multiplier)
      val out = e.name match {
        case s if s.contains("_score") =>
          ConsoleLogger.log1("********* ScoreEvaluator, if s.contains(\"_score\")")
          //The following * lines create duplicate 0 symptom
          Rational(e.multiplier.toString()) * I(multiplierNamePurger(e)).left.get
        case s if I.contains(s) && I(s).isLeft =>
          ConsoleLogger.log1("********* ScoreEvaluator, if I.contains(s) && I(s).isLeft")
          Rational(e.multiplier.toString()) * I(s).left.get
        case "" =>
          ConsoleLogger.log1("********* ScoreEvaluator, \"\"")
          Rational(e.multiplier.toString())
        case s if e.multiplier == BigDecimal("0") => Rational("0")
//        case s =>
//          ConsoleLogger.log("********* ScoreEvaluator, 0 assumed " + s + ", e is " + e.multiplier)
          //report when this happens
//          reportSpecialCase(s)
//          Rational("0")
      }

      ConsoleLogger.log1("eval:" + multiplierNamePurger(e) + " = " + out + ", it has multiplier " + Rational(e.multiplier.toString()))

      out
    }

    def evaluateFormula(vf: VariableFormula): Rational = {
      vf.operations.foldLeft(Rational("0"))((l, r) => l + eval(r))
    }

    score.scoreRange match {
      case None => score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f))
      case Some(_) => score.underlyingScore.fold(s => Rational(s.toString()), f => evaluateFormula(f)) + I(rangeVarName).fold(s => s, vf => throw new RuntimeException("illegal variable format"))
    }
  }
}
