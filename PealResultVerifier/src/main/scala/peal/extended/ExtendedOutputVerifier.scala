package peal.extended

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.verifier.Z3ModelExtractor


class ExtendedOutputVerifier(input: String) {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()

  val conds = pealProgramParser.conds.toMap
  val analyses = pealProgramParser.analyses
  val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  def verifyModel(rawModel: String, analysisName: String) {
    val truthMapping = Z3ModelExtractor.extractI(rawModel)(analysisName)
    println(truthMapping)
  }


}
