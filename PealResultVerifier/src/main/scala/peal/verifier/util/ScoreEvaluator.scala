package peal.verifier.util

import peal.domain._

object ScoreEvaluator {

  def trueScore(score: Score, rangeVarName: String)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String = x => x.name): Rational = {

    def eval(e: Multiplier): Rational = e.name match {
      case s if s.contains("_score") => I(multiplierNamePurger(e)).left.get
      case s if I.contains(s) && I(s).isLeft => Rational(e.multiplier.toString()) * I(s).left.get
      case "" => Rational(e.multiplier.toString())
      case _ => Rational("0")
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
