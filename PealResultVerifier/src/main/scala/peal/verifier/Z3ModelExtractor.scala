package peal.verifier

import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._
import peal.domain.ThreeWayBooleanObj

object Z3ModelExtractor {

  def extractI(model: String) = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().map {
      case (name, z3Model) => (name, z3Model.assignments.map(assignment => (assignment.name, ThreeWayBooleanObj.from(assignment.value))).toMap)
    }
  }
}
