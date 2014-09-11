package peal.maximise

import peal.antlr.util.ParserHelper
import peal.domain.z3.{Unsat, Sat, SatResult}
import peal.domain.{PealTrue, ThreeWayBoolean, Rational, Pol}
import peal.synthesis.analysis.Satisfiable
import peal.synthesis.{ExtendedSynthesiserCore, GreaterThanThCondition}
import peal.verifier.{OutputVerifier, Z3ModelExtractor}
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

  def runSatisfiableAnalysis(threshold: BigDecimal): (SatResult, Map[String, Either[Rational, ThreeWayBoolean]]) = {

    def inputWithConditionAndAnalysis = {
      val inputWithoutDomainSpecific = input.split("\n").takeWhile(!_.startsWith("DOMAIN_SPECIFICS")).dropWhile(_.startsWith("DOMAIN_SPECIFICS")).filterNot(_.trim.startsWith("%"))
      inputWithoutDomainSpecific.mkString("\n") + "\nCONDITIONS\ncond1 = " + threshold + " < " + pSet + (if (pol != "") "_" + pol else "") + "\n" +
      "DOMAIN_SPECIFICS\n" + domainSpecifics.mkString("\n") +
      "\nANALYSES\nname1 = satisfiable? cond1"
    }

    val conds: Map[String, GreaterThanThCondition] = Map("cond1" -> GreaterThanThCondition(pSets(pSet + (if (pol != "") "_" + pol else "")), Left(threshold)))
    val analyses: Map[String, Satisfiable] = Map("name1" -> new Satisfiable("name1", "cond1"))
    val z3Code = ExtendedSynthesiserCore(pols, conds, pSets, analyses, domainSpecifics, predicateNames, variableDefaultScores, variableScores).generate()
    val z3RawOutput = Z3Caller.call(z3Code)

    OutputVerifier(inputWithConditionAndAnalysis).verifyModel(z3RawOutput, "name1")._1 match {
      case PealTrue => Z3ModelExtractor.extractIUsingRational(z3RawOutput)("name1")
      case _ => throw new RuntimeException("Certification in maximize_pset failed for " + conds("cond1") )
    }
  }

  private def bisection(inputLow: BigDecimal, inputHigh: BigDecimal) : String = {
    var low = inputLow
    var high = inputHigh
    while ((high - low) > accuracy) {
      val middle = (low + high) / 2
      val I = runSatisfiableAnalysis(middle)
      if (I._1 == Sat) {
        if (pol != "") {
          val temp = I._2(pol + "_score").left.get.value
          val J = runSatisfiableAnalysis(temp)._1
          if (J == Unsat) {
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
    "maximum (after bisection(" + inputLow + ", " + inputHigh + ")) is (" + low + ", " + high + "]"
  }

  def doIt(): String = {
    val I = runSatisfiableAnalysis(0.0)
    var high, low = BigDecimal(0.0)

    if (I._1 == Sat) {
      if (pol != "") {
        val temp = I._2(pol + "_score").left.get.value
        val J = runSatisfiableAnalysis(temp)._1
        if (J == Unsat) {
          return "exact maximum is " + temp
        }
      }

      high = low.max(2.0)
      var K = runSatisfiableAnalysis(high)
      while (K._1 == Sat) {
        if (pol != "") {
          val temp = K._2(pol + "_score").left.get.value
          val J = runSatisfiableAnalysis(temp)._1
          if (J == Unsat) {
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
