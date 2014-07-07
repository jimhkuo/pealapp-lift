package peal.verifier

import peal.domain.ThreeWayBoolean

trait OutputVerifier {
  def verifyModel(rawModel: String, analysisName: String): (ThreeWayBoolean, Set[String])
}
