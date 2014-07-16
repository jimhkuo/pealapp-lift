package peal.helper

import peal.synthesis.{EagerSynthesiser, ExtendedSynthesiser}

import scala.util.Try

object PealCometHelper {

  def performExplicitSynthesis(policies: String): Try[String] = Try {
    new EagerSynthesiser(policies).generate()
  }

  def performExtendedSynthesis(policies: String): Try[String] = Try {
    new ExtendedSynthesiser(policies).generate()
  }
}
