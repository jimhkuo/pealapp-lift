package peal.synthesis.util

import peal.domain.Score

object Z3ScoreGenerator {

  def generate(score: Score) : String = {
    score.underlyingScore.fold(score => score.toString(), variable => variable.toZ3Expression)
  }

  def generate(score: Score, uncertaintyName: String) : String = score.scoreRange match {
    case None => generate(score)
    case _ if uncertaintyName == "" =>  generate(score)
    case _ =>  "(+ " + generate(score, "") + " " + uncertaintyName + ")"
  }
}
