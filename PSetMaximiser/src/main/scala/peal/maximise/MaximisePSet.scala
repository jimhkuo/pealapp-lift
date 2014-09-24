package peal.maximise

import peal.antlr.util.ParserHelper
import peal.domain.z3.{Unsat, Sat, SatResult}
import peal.domain._
import peal.synthesis.analysis.Satisfiable
import peal.synthesis.{ExtendedSynthesiserCore, GreaterThanThCondition}
import peal.verifier.{OutputVerifier, Z3ModelExtractor}
import peal.z3.Z3Caller

import scala.collection.JavaConversions._


case class MaximisePSet(input: String, pSet: String, accuracy: BigDecimal, pol: String = "") {

  val inputWithoutConditionAndTheRest = input.split("\n").takeWhile(l => !l.startsWith("CONDITIONS") && !l.startsWith("DOMAIN_SPECIFICS") && !l.startsWith("ANALYSES")).
    filterNot(_.trim.startsWith("%")).mkString("\n")

  val pealProgramParser = ParserHelper.getPealParser(inputWithoutConditionAndTheRest)
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

  private def runSatisfiableAnalysis(threshold: BigDecimal)(implicit doMin: Boolean): (SatResult, Map[String, Either[Rational, ThreeWayBoolean]]) = {
    val pSetName = pSet + (if (pol != "") "_" + pol else "")

    def inputWithReplacedConditionAndAnalysis = {
      inputWithoutConditionAndTheRest + "\nCONDITIONS\ncond1 = " + threshold + " < " + pSetName + "\n" +
      "DOMAIN_SPECIFICS\n" + domainSpecifics.mkString("\n") +
      "\nANALYSES\nname1 = satisfiable? cond1"
    }

    val conds = Map("cond1" -> GreaterThanThCondition(pSets(pSetName), Left(threshold)))
    val analyses = Map("name1" -> new Satisfiable("name1", "cond1"))
    val generatedZ3Code = ExtendedSynthesiserCore(pols, conds, pSets, analyses, domainSpecifics, predicateNames, variableDefaultScores, variableScores).generate()
    val z3RawOutput = Z3Caller.call(generatedZ3Code)

    OutputVerifier(inputWithReplacedConditionAndAnalysis).verifyModel(z3RawOutput, "name1")._1 match {
      case PealTrue => Z3ModelExtractor.extractIAndStatusUsingRational(z3RawOutput)("name1")
      case PealBottom => throw new RuntimeException(s"satisfiable? $threshold < $pSetName is UNKNOWN")
      case PealFalse => throw new RuntimeException(s"Certification of $threshold < $pSet failed")
    }
  }

  private def bisection(inputLow: BigDecimal, inputHigh: BigDecimal)(implicit doMin: Boolean) : String = {
    var low = inputLow
    var high = inputHigh
    while ((high - low) > accuracy) {
      val middle = (low + high) / 2
      val I = runSatisfiableAnalysis(middle)
      if (I._1 == Sat) {
        if (pol.nonEmpty) {
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
    "maximum (after calling bisection method with low = " + inputLow + " and high = " + inputHigh + ") is in half-open interval (" + low + ", " + high + "]"
  }

  def doIt(doMinFlag: Boolean = false): String = {
    implicit val doMin = doMinFlag
    val I = runSatisfiableAnalysis(0.0)
    var high, low = BigDecimal(0.0)

    if (I._1 == Sat) {
      if (pol.nonEmpty) {
        val temp = I._2(pol + "_score").left.get.value
        val J = runSatisfiableAnalysis(temp)._1
        if (J == Unsat) {
          return "exact maximum is " + temp
        }
      }

      high = low.max(2.0)
      var K = runSatisfiableAnalysis(high)
      while (K._1 == Sat) {
        if (pol.nonEmpty) {
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
