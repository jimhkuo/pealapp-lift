package peal.verifier

import peal.antlr.util.ParserHelper
import peal.util.ConsoleLogger
import scala.collection.JavaConversions._


object Z3ModelExtractor {

  def extractI(model: String) = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().map {
      case (name, z3Model) => (name, z3Model.assignments.map(assignment => (assignment.name, Z3ModelValueParser.parse(assignment.value))).toMap)
    }
  }

  def extractIUsingRational(model: String) = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().map {
      case (name, z3Model) =>
        (name, z3Model.assignments.map(assignment => (assignment.name, Z3ModelValueParser.parseToRational(assignment.value))).toMap)
    }
  }
}
