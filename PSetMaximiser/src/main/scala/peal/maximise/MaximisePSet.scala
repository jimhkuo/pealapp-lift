package peal.maximise

import peal.antlr.util.ParserHelper
import peal.domain.Pol
import peal.synthesis.analysis.Satisfiable
import peal.synthesis.{ExtendedSynthesiserCore, GreaterThanThCondition}
import peal.verifier.Z3ModelExtractor
import peal.z3.Z3Caller

import scala.collection.JavaConversions._


case class MaximisePSet(input: String, pSet: String, accuracy: BigDecimal, pol: String = "") {
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

  def runSatisfiableAnalysis(threshold: BigDecimal) = {
    val conds: Map[String, GreaterThanThCondition] = Map("cond1" -> GreaterThanThCondition(pSets(pSet + (if (pol != "") "_" + pol else "")), Left(threshold)))
    val analyses: Map[String, Satisfiable] = Map("name1" -> new Satisfiable("name1", "cond1"))
    val z3Code = ExtendedSynthesiserCore(pols, conds, pSets, analyses, domainSpecifics, predicateNames, variableDefaultScores, variableScores).generate()
    val z3RawOutput = Z3Caller.call(z3Code)
    val I = Z3ModelExtractor.extractIUsingRational(z3RawOutput)("name1")
//    println(I)
    I
  }

  private def bisection(inputLow: BigDecimal, inputHigh: BigDecimal) : String = {
    var low = inputLow
    var high = inputHigh
    while ((high - low) > accuracy) {
      val middle = (low + high) / 2
      val I = runSatisfiableAnalysis(middle)
      if (I.nonEmpty) {
        if (pol != "") {
          val temp = I(pol + "_score").left.get.value
          val J = runSatisfiableAnalysis(temp)
          if (J.isEmpty) {
            return "exact maximum is " + temp
          }
          low = temp.max(middle)
        } else {
          low = middle
        }
      } else {
        high = middle
      }
    }
    "exact maximum (after bisection(" + inputLow + ", " + inputHigh + ")) is " + low
  }

  def doIt(): String = {
    val I = runSatisfiableAnalysis(0.0)
    var high, low = BigDecimal(0.0)

    if (I.nonEmpty) {
      if (pol != "") {
        val temp = I(pol + "_score").left.get.value
        val J = runSatisfiableAnalysis(temp)
        if (J.isEmpty) {
          return "exact maximum is " + temp
        }
      }

      high = low.max(2.0)
      var K = runSatisfiableAnalysis(high)
      while (K.nonEmpty) {
        if (pol != "") {
          val temp = K(pol + "_score").left.get.value
          val J = runSatisfiableAnalysis(temp)
          if (J.isEmpty) {
            return "exact maximum is " + temp
          }
          low = temp.max(high)
          high = 2 * low.max(high)
        }
        else {
          low = high
          high = 2 * high
        }
        K = runSatisfiableAnalysis(high)
      }

      bisection(low, high)
    }
    else {
      bisection(low, 0.0)
    }
  }
}
