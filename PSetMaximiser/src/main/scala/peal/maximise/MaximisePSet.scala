package peal.maximise

import peal.antlr.util.ParserHelper
import peal.domain.Pol
import peal.domain.z3.Model
import peal.synthesis.{ExtendedSynthesiserCore, GreaterThanThCondition}
import peal.synthesis.analysis.Satisfiable
import peal.verifier.Z3ModelExtractor
import peal.z3.Z3Caller
import scala.collection.JavaConversions._


case class MaximisePSet(input : String, pSet: String, accuracy: BigDecimal, pol:String = "") {
  val pealProgramParser = ParserHelper.getPealParser(input)
  pealProgramParser.program()
  val pols = pealProgramParser.pols.toMap
  val allRules = pols.values.flatMap(pol => pol.rules)
  val predicateNames: Set[String] = allRules.map(r => r.q.name).toSet
  val variableDefaultScores: Set[String] = pols.foldLeft(Set[String]())((acc, tuple) => {
    tuple._2 match {
      case p: Pol =>
        def addVariables(set: Set[String]) = p.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
        addVariables(acc)
      case _ => acc
    }
  })
  val variableScores: Set[String] = allRules.foldLeft(Set[String]())((acc, rule) => {
    def addVariables(set: Set[String]) = rule.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
    addVariables(acc)
  })
  val pSets = pealProgramParser.pSets.toMap
  val domainSpecifics: Array[String] = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1).filterNot(_.trim.startsWith("%"))

  def isSatisfiable(threshold: BigDecimal) = {
    val conds: Map[String, GreaterThanThCondition] = Map("cond1" -> GreaterThanThCondition(pSets(pSet + (if (pol != "") "_" + pol else "")), Left(threshold)))
    val analyses: Map[String, Satisfiable] = Map("name1" -> new Satisfiable("name1", "cond1"))
    val z3Code = ExtendedSynthesiserCore(pols, conds, pSets, analyses, domainSpecifics, predicateNames, variableDefaultScores, variableScores).generate()
    val z3RawOutput = Z3Caller.call(z3Code)
    val I = Z3ModelExtractor.extractIUsingRational(z3RawOutput)("name1")
    println(I)

    I.nonEmpty
  }

  def doIt() = {
//    if (isSatisfiable(0.0)) {
//      if (pol != "") {
//        val temp =
//      }
//    }
  }

}
