package peal.verifier

import peal.antlr.util.ParserHelper
import peal.domain.z3.SatResult
import peal.domain.{Rational, ThreeWayBoolean}

import scala.collection.JavaConversions._
import scala.collection.mutable


object Z3ModelExtractor {

  def extractIUsingRational(model: String): mutable.Map[String, (SatResult, Map[String, Either[Rational, ThreeWayBoolean]])] = {
    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    z3OutputParser.results().map {
      case (name, z3Model) =>
        (name, (z3Model.satResult, z3Model.assignments.filterNot(a => a.objectType == "MethodName" || a.value == "").map(assignment => (assignment.name, Z3ModelValueParser.parseToRational(assignment.value))).toMap))
    }
  }
}
