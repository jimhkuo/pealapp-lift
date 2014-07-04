package peal.explicit

import peal.antlr.util.ParserHelper
import peal.domain._
import peal.verifier.Z3ModelExtractor

import scala.collection.JavaConversions._


class ExplicitAnalyser(input: String) {

  private val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  private val conds = pealProgramParser.conds.toMap
  private val analyses = pealProgramParser.analyses
  private val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct

  def analyse(rawModel: String, analysisName: String): String = {
    val I = Z3ModelExtractor.extractI(rawModel)(analysisName)
    //call explicit verifier
    //pull its remapped set, together with I to create the value map
    //then pull all policies used in the analysis, reconstruct policy descriptions
    ???
  }

}
