package peal.explicit

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._

object ExplicitOutputProcessor {

  def assignmentExtractor(model: String) = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().toMap
  }
}
