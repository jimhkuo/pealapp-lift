package peal.verifier.util

import peal.domain._

object ScoreEvaluator {

  def trueScore(score: Score, rangeVarName: String)(implicit I: Map[String, Either[Rational, ThreeWayBoolean]], multiplierNamePurger: Multiplier => String = x => x.name): Rational = {

    def eval(e: Multiplier): Rational = {
      //TODO WIP
      if (e.name.contains("_score")) {
        val realName = multiplierNamePurger(e)
        println("realName is " + realName)
        I(realName).left.get //TODO sort this out
      }
      else if (e.name == "" || I.contains(e.name)) {
        e.name match {
          case "" => Rational(e.multiplier.toString())
          case _ if I(e.name).isLeft => Rational(e.multiplier.toString()) * I(e.name).fold(s => s, vf => throw new RuntimeException("illegal variable format"))
          case _ => throw new RuntimeException("Invalid eval case")
        }
      } else {
        println("0 assumed")
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
