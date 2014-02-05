package peal.synthesis

import peal.domain.Score

object Z3ScoreGenerator {

  def generate(score: Score) = score.scoreRange match {
    case None => score.underlyingScore.fold(score => score.toString(), variable => variable.toZ3Expression)
    case _ =>  "not done"
  }

}
