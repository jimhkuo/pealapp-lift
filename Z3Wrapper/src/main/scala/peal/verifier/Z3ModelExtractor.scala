package peal.verifier

import peal.antlr.util.ParserHelper
import peal.domain.{ThreeWayBoolean, Rational}
import peal.util.ConsoleLogger
import scala.collection.JavaConversions._
import scala.collection.mutable


object Z3ModelExtractor {

  def extractI(model: String) = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    val results = z3OutputParser.results()
    ConsoleLogger.log(results)
    results.map {
      case (name, z3Model) =>
        (name, z3Model.assignments.filterNot(a => a.objectType == "MethodName" || a.value == "").map(assignment => (assignment.name, Z3ModelValueParser.parse(assignment.value))).toMap)
    }
  }

  def extractIUsingRational(model: String): mutable.Map[String, Map[String, Either[Rational, ThreeWayBoolean]]] = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().map {
      case (name, z3Model) =>
        (name, z3Model.assignments.filterNot(a => a.objectType == "MethodName" || a.value == "").map(assignment => (assignment.name, Z3ModelValueParser.parseToRational(assignment.value))).toMap)
    }
  }
}
