package peal.antlr

import org.junit.rules.ExpectedException
import org.junit.{Rule, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.synthesis.GreaterThanThCondition
import scala.collection.JavaConversions._
import peal.synthesis.analysis.AlwaysTrue
import peal.util.Z3ModelMatcher
import peal.domain.z3.{PealAst, Term}
import peal.antlr.util.ParserHelper
import peal.domain.Pol


class PealProgramParserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test
  def testUnclaredThrowExceptionGreaterThanNum() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = 0 < pSet1\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet1 is not declared but is used on line \"cond1 = 0 < pSet1\"")
  }

  @Test
  def testUnclaredThrowExceptionGreaterThanpSet() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet < pSet1\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet1 is not declared but is used on line \"cond1 = pSet < pSet1\"")
  }

  @Test
  def testUnclaredThrowExceptionGreaterThanpSet2() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet1 < pSet\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet1 is not declared but is used on line \"cond1 = pSet1 < pSet\"")
  }

  @Test
  def testUnclaredThrowExceptionGreaterThanpSetCatchLhsFirst() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet1 < pSet2\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet1 is not declared but is used on line \"cond1 = pSet1 < pSet2\"")
  }

  @Test
  def testUnclaredThrowExceptionLessThanNum() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet1 <= 0\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet1 is not declared but is used on line \"cond1 = pSet1 <= 0\"")
  }

  @Test
  def testUnclaredThrowExceptionLessThanpSet() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet1 <= pSet\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet1 is not declared but is used on line \"cond1 = pSet1 <= pSet\"")
  }

  @Test
  def testUnclaredThrowExceptionLessThanpSet2() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet <= pSet1\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet1 is not declared but is used on line \"cond1 = pSet <= pSet1\"")
  }

  @Test
  def testUnclaredThrowExceptionLessThanpSetLhsFirst() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet2 <= pSet1\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("PolicySet pSet2 is not declared but is used on line \"cond1 = pSet2 <= pSet1\"")
  }

  @Test
  def testUnclaredThrowExceptionNotCondition() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet <= 0\n" +
        "cond3 = !cond2\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond2 is not declared but is used on line \"cond3 = !cond2\"")
  }

  @Test
  def testUnclaredThrowExceptionAndLhs() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet <= 0\n" +
        "cond3 = cond2 && cond1\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond2 is not declared but is used on line \"cond3 = cond2 && cond1\"")
  }

  @Test
  def testUnclaredThrowExceptionAndRhs() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet <= 0\n" +
        "cond3 = cond1 && cond2\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond2 is not declared but is used on line \"cond3 = cond1 && cond2\"")
  }

  @Test
  def testUnclaredThrowExceptionOrLhs() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet <= 0\n" +
        "cond3 = cond2 || cond1\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond2 is not declared but is used on line \"cond3 = cond2 || cond1\"")
  }

  @Test
  def testUnclaredThrowExceptionOrRhs() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = pSet <= 0\n" +
        "cond3 = cond1 || cond2\n" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond2 is not declared but is used on line \"cond3 = cond1 || cond2\"")
  }

  //TODO not done
  @Test
  def testUnclaredThrowExceptionPredCondition() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q5" +
        "ANALYSES\nanalysis1 = always_true? cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Predicate q5 has not appeared in policies prior to this point but is in cond1 = q5")
  }

  @Test
  def testUnclaredThrowExceptionATAnalysis() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q0\n" +
        "ANALYSES\n" +
        "analysis1 = always_true? cond"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond is not declared but is used on line \"analysis1 = always_true? cond\"")
  }

  @Test
  def testUnclaredThrowExceptionAFAnalysis() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q0\n" +
        "ANALYSES\n" +
        "analysis1 = always_false? cond"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond is not declared but is used on line \"analysis1 = always_false? cond\"")
  }

  @Test
  def testUnclaredThrowExceptionSatAnalysis() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q0\n" +
        "ANALYSES\n" +
        "analysis1 = satisfiable? cond"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond is not declared but is used on line \"analysis1 = satisfiable? cond\"")
  }

  @Test
  def testUnclaredThrowExceptionEquivalentAnalysis() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q0\n" +
        "ANALYSES\n" +
        "analysis1 = equivalent? cond cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond is not declared but is used on line \"analysis1 = equivalent? cond cond1\"")
  }

  @Test
  def testUnclaredThrowExceptionEquivalentAnalysis1() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q0\n" +
        "ANALYSES\n" +
        "analysis1 = equivalent? cond1 cond"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond is not declared but is used on line \"analysis1 = equivalent? cond1 cond\"")
  }

  @Test
  def testUnclaredThrowExceptionDifferentAnalysis() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q0\n" +
        "ANALYSES\n" +
        "analysis1 = different? cond cond1"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond is not declared but is used on line \"analysis1 = different? cond cond1\"")
  }

  @Test
  def testUnclaredThrowExceptionDifferentAnalysis1() {
    val ex = intercept[RuntimeException] {
      val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\n" +
        "cond1 = q0\n" +
        "ANALYSES\n" +
        "analysis1 = different? cond1 cond"
      val pealProgramParser = ParserHelper.getPealParser(input)
      pealProgramParser.program()
    }
    ex.getMessage should be ("Condition cond is not declared but is used on line \"analysis1 = different? cond1 cond\"")
  }

  @Test
  def testCanParseComplicatedInput() {
    val input = "POLICIES\nb1 = + ((q0 0.1)(q1 0.1)(q2 0.1)(q3 0.1)(q4 0.1)) default 0\nPOLICY_SETS\npSet = b1\nCONDITIONS\ncond1 = 0 < pSet\ncond2 = 0.2 < pSet\ncond3 = 0.4 < pSet\ncond4 = 0.6 < pSet\ncond5 = 0.8 < pSet\ncond6 = 1 < pSet\nDOMAIN_SPECIFICS\n(declare-sort User)\n(declare-fun seniorTo (User User) Bool)\n(declare-fun salary (User) Real) \n(declare-const bob User)\n(assert (forall ((u1 User) (u2 User) (u3 User)) (implies (and (seniorTo u1 u2) (seniorTo u2 u3)) (seniorTo u1 u3))))\n(assert (forall ((u User)) (not (seniorTo u u))))\n" +
      "(assert (forall ((u1 User) (u2 User)) (implies (and (seniorTo u1 u2) (seniorTo u2 u1)) (= u1 u2))))\n" +
      "(assert (forall ((u1 User) (u2 User)) (xor (seniorTo u1 u2) (= (salary u1) (/ 2.5 (salary u2))))))\n" +
      "ANALYSES\nanalysis1 = always_true? cond1"
    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

  }

  @Test
  def testCanIngoreComments() {
    val input =
      "POLICIES\nb1 = + ((q1 x) (q2 0.9)) default 1\n" +
        "b2 = + ((q3 x) (q4 0.8)) default 1\n" +
        "%b1 = + ((q3 x) (q2 0.7)) default 1\n" +
        "%b1 = + ((q3 x) (q2 0.6)) default 1\n" +
        "POLICY_SETS\npSet = + (b1,b2)\n" +
        "CONDITIONS\ncond = q4"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules).toSeq
    allRules(0).score.underlyingScore.right.get.toZ3Expression should be("x")
    allRules(1).score.underlyingScore.left.get should be(BigDecimal(0.9))
  }

  @Test
  def testConditionCanBePredicate() {
    val input =
      "POLICIES\nb1 = + ((q1 x) (q2 0.9)) default 1\n" +
        "b2 = + ((q3 x) (q4 0.9)) default 1\n" +
        "POLICY_SETS\npSet = + (b1,b2)\n" +
        "CONDITIONS\ncond = q4"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules).toSeq
    allRules(0).score.underlyingScore.right.get.toZ3Expression should be("x")
    allRules(1).score.underlyingScore.left.get should be(BigDecimal(0.9))
  }

  @Test
  def testCanAggregratePolicySetUsingPlus() {
    val input =
      "POLICIES\nb1 = + ((q1 x) (q2 0.9)) default 1\n" +
        "b2 = + ((q3 x) (q4 0.9)) default 1\n" +
        "POLICY_SETS\npSet = + (b1,b2)\n" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules).toSeq
    allRules(0).score.underlyingScore.right.get.toZ3Expression should be("x")
    allRules(1).score.underlyingScore.left.get should be(BigDecimal(0.9))
  }

  @Test
  def testCanAggregratePolicySetUsingMul() {
    val input =
      "POLICIES\nb1 = + ((q1 x) (q2 0.9)) default 1\n" +
        "b2 = + ((q3 x) (q4 0.9)) default 1\n" +
        "POLICY_SETS\npSet = * (b1,b2)\n" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules).toSeq
    allRules(0).score.underlyingScore.right.get.toZ3Expression should be("x")
    allRules(1).score.underlyingScore.left.get should be(BigDecimal(0.9))
  }

  @Test
  def testCanDealWithNewScoreType() {
    val input =
      "POLICIES\nb1 = + ((q1 x [-0.1,0.2]) (q2 0.9 [-0.1,0.2])) default 1\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules).toSeq
    allRules(0).score.underlyingScore.right.get.toZ3Expression should be("x")
    allRules(0).score.scoreRange.get.minValue should be(BigDecimal(-0.1))
    allRules(0).score.scoreRange.get.maxValue should be(BigDecimal(0.2))
    allRules(1).score.underlyingScore.left.get should be(BigDecimal(0.9))
    allRules(1).score.scoreRange.get.minValue should be(BigDecimal(-0.1))
    allRules(1).score.scoreRange.get.maxValue should be(BigDecimal(0.2))
  }

  @Test
  def testCanDealWithNewScoreTypeForDefault() {
    val input =
      "POLICIES\nb1 = + ((q1 x) (q2 0.9)) default 1 [-0.2, 0.3]\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    pealProgramParser.pols("b1").score.scoreRange.get.minValue should be(BigDecimal(-0.2))
    pealProgramParser.pols("b1").score.scoreRange.get.maxValue should be(BigDecimal(0.3))
  }

  @Test
  def testNonConstantDefaultScores() {
    val input =
      "POLICIES\nb1 = + ((q1 x) (q2 0.5) (q3 0.4)) default x\n" +
        "b2 = min ((q1 x) (q2 0.5) (q3 0.4)) default y\n" +
        "b3 = * ((q1 x) (q2 0.5) (q3 0.4)) default 1.1*x\n" +
        "b4 = max ((q1 x) (q2 0.5) (q3 0.4)) default z * 2.9\n" +
        "POLICY_SETS\npSet = max(b1, b2)\n" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()
    val pols = pealProgramParser.pols

    val nonConstantDefaultScores = pols.foldLeft(Set[String]())((acc, tuple) => {
      tuple._2 match {
        case p: Pol =>
          def addVariables(set: Set[String]) = p.score.underlyingScore.fold(score => set, variable => set ++ variable.names)
          addVariables(acc)
        case _ => acc
      }
    })

    nonConstantDefaultScores should be(Set("x", "y", "z"))
  }

  @Test
  def testCanNonConstantFromRules() {
    val input =
      "POLICIES\nb1 = + ((q1 x) (q2 0.5) (q3 0.4)) default 1\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgramParser = ParserHelper.getPealParser(input)
    pealProgramParser.program()

    val allRules = pealProgramParser.pols.values().flatMap(pol => pol.rules)
    val predicateNames = allRules.foldLeft(Set[String]())((acc, rule) => {
      def addVariables(set: Set[String]) = rule.score.underlyingScore.fold(left => set, right => set ++ right.names)
      addVariables(acc + rule.q.name)
    })

    predicateNames should be(Set("x", "q1", "q2", "q3"))
  }

  @Test
  def testCanHandleNegativeScores() {
    val input =
      "POLICIES\nb1 = min ((q1 -0.2) (q2 -0.4) (q3 -0.9)) default 1\n" +
        "POLICY_SETS\npSet = b1\n" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules(0).numberScore should be(BigDecimal.valueOf(-0.2))
    pols("b1").rules(1).numberScore should be(BigDecimal.valueOf(-0.4))
    pols("b1").rules(2).numberScore should be(BigDecimal.valueOf(-0.9))
  }

  @Test(expected = classOf[RuntimeException])
  def testInValidInput() {
    val input = "POLICIES\nco" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "POLICY_SETS\npSet = max(b1, b2)"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
  }

  @Test
  def testCanDealWithTags() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "DOMAIN_SPECIFICS\n" +
      "(declare-const x Real)\n" +
      "(declare-const y Real)\n" +
      "(assert (= q1 (< x (y+1)))\n" +
      "(assert (= q3 (= x (y + 3.5)))\n" +
      "(assert (= q1 (< x (+ y 1))))\n" +
      "ANALYSES\n" +
      "name1 = always_true? cond1\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond1").synthesis(consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false))) ")
    pealProgrmParser.analyses("name1") should be(new AlwaysTrue("name1", "cond1"))
  }

  @Test
  def testNotCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = !cond1\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(not cond1)")
  }

  @Test
  def testAndCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = cond1 && cond1\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(and cond1 cond1)")
  }

  @Test
  def testOrCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = cond1 || cond1\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or cond1 cond1)")
  }

  @Test
  def testTruthCondition() {
    val input = "POLICIES\n" +
      "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
      "POLICY_SETS\n" +
      "pSet1 = max(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = false\n" +
      "cond2 = true\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("true")
    pealProgrmParser.conds("cond1").synthesis(consts) should beZ3Model("false")
  }

  @Test
  def testCanParseDefaultInputInWebapp() {
    val input =
      "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "POLICY_SETS\npSet = max(b1, b2)" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMin() {
    val input =
      "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "POLICY_SETS\npSet = min(b1, b2)" +
        "CONDITIONS\ncond = pSet <= 0.5"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(or (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testDealWithMinAndGreaterThanTh() {
    val input =
      "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
        "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
        "POLICY_SETS\npSet = min(b1, b2)" +
        "CONDITIONS\ncond = 0.5 < pSet "

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    val pols = pealProgrmParser.pols
    pols("b1").rules.size should be(3)
    pols("b2").rules.size should be(3)

    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))")
  }

  @Test
  def testDealWithMultipleConditions() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1" +
      "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0 " +
      "POLICY_SETS\npSet1 = min(b1, b2)" +
      "pSet2 = min(b1, b2)" +
      "CONDITIONS\ncond1 = 0.5 < pSet1 " +
      "cond2 = pSet2 <= 0.5 "

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()

    pealProgrmParser.conds("cond1").synthesis(consts) should beZ3Model("(and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))")
    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))")
  }

  @Test
  def testCanDoExample1InEmail() {
    val input =
      "POLICIES\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.1)) default 0.6\n" +
        "POLICY_SETS\npSet = b2\n" +
        "CONDITIONS\ncond = pSet <= 0.5\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(and (or q4 q5 q6) (not false))")
  }

  @Test
  def testCanDoExample2InEmail() {
    val input = "POLICIES\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\nPOLICY_SETS\npSet = b2\nCONDITIONS\ncond = pSet <= 0.5\n"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (and q6 q5 q4)))")
  }

  @Test
  def testCanDoExample3InEmail() {
    val input = "POLICIES\nb2 = + ((q4 0.2) (q5 0.2) (q6 0.3)) default 0\nPOLICY_SETS\npSet = b2\nCONDITIONS\ncond = pSet <= 0.2"

    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds.values().head.synthesis(consts) should beZ3Model("(or (and (not q4) (not q5) (not q6)) (not (or q6 (and q5 q4))))")
  }

  @Test
  def testBrokenCaseForPlus() {
    val input = "POLICIES\nb0 = max ((q4 0.41)) default 0.67\nb1 = * ((q4 0.02)) default 0.58\nb2 = + ((q5 0.54) (q3 0.06)) default 0.44\nb3 = min ((q2 0.69)) default 0.62\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\nCONDITIONS\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or (and (or (not q4) false) (and q4 (not q4))) (and (and (or q5 q3) false) (or (not q2) (not false))))")
  }

  @Test
  def testM2HandleEdgeCaseCorrectly() {
    val input = "POLICIES\nb0 = max ((q4 0.41)) default 0.67\nb1 = * ((q4 1.0) (q3 0.6)) default 0.06\nb2 = + ((q5 0.54) (q3 0.06)) default 0.44\nb3 = min ((q2 0.69)) default 0.62\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\nCONDITIONS\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis2 = always_false? cond2"
    val pealProgrmParser = ParserHelper.getPealParser(input)
    pealProgrmParser.program()
    pealProgrmParser.conds("cond2").synthesis(consts) should beZ3Model("(or (and (or (not q4) false) (and (or q4 q3) (not q3))) (and (and (or q5 q3) false) (or (not q2) (not false))))")
  }

  //  @Test
  //  def testRepeatedVariable() {
  //    val input = "POLICIES\nb0 = + ((q3 x)) default x\nb1 = max ((q0 0.9920)) default x\nb2 = * ((q6 0.0489)) default 0.1423\nb3 = min ((q2 0.6755)) default 0.3968\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2\n"
  //    val pealProgrmParser = ParserHelper.getPealParser(input)
  //    pealProgrmParser.program()
  //    println(pealProgrmParser.pols)
  //    println(new ExtendedSynthesiser(input).generate())
  //  }
}
