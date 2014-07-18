package peal.verifier.util

import peal.domain._

object ScoreEvaluator {

  def trueScore(score: Score, rangeVarName: String)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]]): Rational = {

    def eval(e: Multiplier): Rational = {
      //TODO access I here for possible policy
      if (e.name == "" || I.contains(e.name)) {
        e.name match {
          case "" => Rational(e.multiplier.toString())
          case _ if I(e.name).isLeft => Rational(e.multiplier.toString()) * I(e.name).fold(s => s, vf => throw new RuntimeException("illegal variable format"))
          case _ => throw new RuntimeException("Invalid eval case")
        }
      } else {
        Rational("0")
      }
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
