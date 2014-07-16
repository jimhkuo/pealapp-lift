package peal.helper

import peal.synthesis.{EagerSynthesiser, ExtendedSynthesiser}

import scala.util.Try

object PealCometHelper {

  def performExplicitSynthesis(policies: String): Try[String] = Try{
    new EagerSynthesiser(policies).generate()
  }
}
