package peal.verifier

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.domain.ThreeWayBooleanObj

object Z3ModelExtractor {

  def extractAssignments(model: String) = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().toMap.map {
      case (name, model) => (name, model.assignments.map(d => (d.name, ThreeWayBooleanObj.from(d.value))).toMap)
    }
  }
}
