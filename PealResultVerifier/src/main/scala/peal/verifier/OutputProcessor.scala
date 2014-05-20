package peal.verifier

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._

object OutputProcessor {

  def assignmentExtractor(model: String) = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().toMap
  }
}
