package peal.antlr

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.antlr.util.ParserHelper
import peal.domain.z3._
import peal.util.{ConsoleLogger, Z3ModelMatcher}

import scala.collection.JavaConversions._


class Z3OutputParserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test
  def testIncludeCommentedInput() {
    val input = "Result of analysis [name8 = different? cond2 cond4]:\nsat\n(model \n  ;; universe for MethodName:\n  ;;   MethodName!val!0 MethodName!val!1 \n  ;; -----------\n  ;; definitions for universe elements:\n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  ;; cardinality constraint:\n  (forall ((x MethodName)) (or (= x MethodName!val!0) (= x MethodName!val!1)))\n  ;; -----------\n  (define-fun q0 () Bool\n    false)\n  (define-fun q7 () Bool\n    true)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun n0 () MethodName\n    MethodName!val!1)\n  (define-fun q8 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun x2 () Real\n    0.0)\n  (define-fun x1 () Real\n    (- (/ 2657671643161391.0 5000000000000000.0)))\n  (define-fun q5 () Bool\n    true)\n  (define-fun n1 () MethodName\n    MethodName!val!0)\n  (define-fun q3 () Bool\n    true)\n  (define-fun a1 () Int\n    0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun a0 () Int\n    0)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x0 () Real\n    0.0)\n  (define-fun calledBy ((x!1 MethodName)) Bool\n    false)\n)\nunsat\nsat\n(model \n  ;; universe for MethodName:\n  ;;   MethodName!val!0 MethodName!val!1 \n  ;; -----------\n  ;; definitions for universe elements:\n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  ;; cardinality constraint:\n  (forall ((x MethodName)) (or (= x MethodName!val!0) (= x MethodName!val!1)))\n  ;; -----------\n  (define-fun q0 () Bool\n    false)\n  (define-fun q7 () Bool\n    true)\n  (define-fun n0 () MethodName\n    MethodName!val!1)\n  (define-fun a2 () Int\n    0)\n  (define-fun q8 () Bool\n    true)\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun x2 () Real\n    0.0)\n  (define-fun x1 () Real\n    (- (/ 2657671643161391.0 5000000000000000.0)))\n  (define-fun q5 () Bool\n    true)\n  (define-fun different_analysis3 () Bool\n    true)\n  (define-fun n1 () MethodName\n    MethodName!val!0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a1 () Int\n    (- 1))\n  (define-fun q2 () Bool\n    true)\n  (define-fun a0 () Int\n    (- 1))\n  (define-fun q6 () Bool\n    true)\n  (define-fun x0 () Real\n    0.0)\n  (define-fun calledBy!4 ((x!1 MethodName)) Bool\n    (ite (= x!1 MethodName!val!1) true\n      false))\n  (define-fun k!3 ((x!1 MethodName)) MethodName\n    (ite (= x!1 MethodName!val!0) MethodName!val!0\n      MethodName!val!1))\n  (define-fun calledBy ((x!1 MethodName)) Bool\n    (calledBy!4 (k!3 x!1)))\n)"
    val parser = ParserHelper.getZ3OutputParser(input)

    parser.results()
  }

  @Test
  def testBroken() {
    val input = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q360 () Bool\n    true)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n)"
    val parser = ParserHelper.getZ3OutputParser(input)

    println(parser.results())
  }

  @Test
  def testCanHandleOtherTitles() {
    val input =
      "Result of analysis [analysis1 = always_true? cond1]:\n" +
        "sat\n(model \n  " +
        "(define-fun cond1 () Bool\n    false)\n  " +
        "(define-fun q0 () Bool\n    false)\n  " +
        "(define-fun always_true_analysis1 () Bool\n    false)\n " +
        "(define-fun q1 () Bool\n    false)\n " +
        "(define-fun q4 () Bool\n    true)\n" +
        "(define-fun cond2 () Bool\n    false)\n)\n" +
        "Result of analysis [analysis2 = always_false? cond2]:\n" +
        "sat\n(model \n  (define-fun cond1 () Bool\n    true)\n " +
        "(define-fun always_false_analysis2 () Bool\n    true)\n" +
        "(define-fun q1 () Bool\n    true)\n " +
        "(define-fun q2 () Bool\n    true)\n  " +
        "(define-fun cond2 () Bool\n    true)\n)\n" +
        "Result of vacuity check [vacuityCheck = always_false? cond2]:\n" +
        "sat\n(model \n  (define-fun cond1 () Bool\n    true)\n " +
        "(define-fun always_false_analysis2 () Bool\n    true)\n" +
        "(define-fun q1 () Bool\n    true)\n " +
        "(define-fun q2 () Bool\n    true)\n  " +
        "(define-fun cond2 () Bool\n    true)\n)\n" +
        "Result of analysis [analysis3 = different? cond1 cond2]:\n" +
        "unsat\n" +
        "(error \"line 35 column 10: model is not available\")"
    val parser = ParserHelper.getZ3OutputParser(input)

    val analyses: Map[String, Model] = parser.results().toMap
    println(analyses)
    analyses.size should be(4)
    analyses("vacuityCheck").satResult should be(Sat)
    analyses("analysis1").satResult should be(Sat)
    analyses("analysis2").satResult should be(Sat)
    analyses("analysis3").satResult should be(Unsat)
  }

  @Test
  def testBrokenDeclareFunCase() {
    //    ConsoleLogger.enable()
    val input = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  ;; universe for MethodName:\n  ;;   MethodName!val!0 MethodName!val!1 \n  ;; -----------\n  ;; definitions for universe elements:\n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  ;; cardinality constraint:\n  (forall ((x MethodName)) (or (= x MethodName!val!0) (= x MethodName!val!1)))\n  ;; -----------\n  (define-fun q0 () Bool\n    false)\n  (define-fun n0 () MethodName\n    MethodName!val!1)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun q8 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun x2 () Real\n    (/ 1180.0 8807.0))\n  (define-fun x1 () Real\n    (/ 1180.0 5977.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun n2 () MethodName\n    MethodName!val!0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun a1 () Int\n    0)\n  (define-fun a0 () Int\n    0)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x0 () Real\n    0.0)\n  (define-fun calledBy ((x!1 MethodName)) Bool\n  false)\n)"
    ConsoleLogger.log(input)
    val parser = ParserHelper.getZ3OutputParser(input)

    val results = parser.results().toString
    println(results)
    results should be("{analysis1=Sat\n () = \nq0 (Bool) = false\nn0 (MethodName) = MethodName!val!1\nalways_true_analysis1 (Bool) = false\na2 (Int) = 0\nq8 (Bool) = true\nq4 (Bool) = true\ncond2 (Bool) = false\ncond1 (Bool) = false\nx2 (Real) = 1180.0/8807.0\nx1 (Real) = 1180.0/5977.0\nq1 (Bool) = false\nq5 (Bool) = true\nq3 (Bool) = true\nn2 (MethodName) = MethodName!val!0\nq2 (Bool) = false\na1 (Int) = 0\na0 (Int) = 0\nq6 (Bool) = true\nx0 (Real) = 0.0\ncalledBy (Bool) = false}")
  }

  @Test
  def testBrokenDeclareFunCase2() {
    ConsoleLogger.enable()
    val input = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n" +
      "  ;; universe for MethodName:\n" +
      "  ;;   MethodName!val!2 MethodName!val!3 MethodName!val!0 MethodName!val!1 \n" +
      "  ;; -----------\n" +
      "  ;; definitions for universe elements:\n" +
      "  (declare-fun MethodName!val!2 () MethodName)\n" +
      "  (declare-fun MethodName!val!3 () MethodName)\n" +
      "  (declare-fun MethodName!val!0 () MethodName)\n" +
      "  (declare-fun MethodName!val!1 () MethodName)\n" +
      "  ;; cardinality constraint:\n" +
      "  (forall ((x MethodName))\n" +
      "          (or (= x MethodName!val!2)\n" +
      "              (= x MethodName!val!3)\n" +
      "              (= x MethodName!val!0)\n" +
      "              (= x MethodName!val!1)))\n" +
      "  ;; -----------\n  " +
      "(define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    true)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun q4 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun x2 () Real\n    0.0)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q9 () Bool\n    false)\n  (define-fun n3 () MethodName\n    MethodName!val!2)\n  (define-fun q3 () Bool\n    false)\n  (define-fun a1 () Int\n    1)\n  (define-fun n4 () MethodName\n    MethodName!val!1)\n  (define-fun q6 () Bool\n    true)\n  (define-fun q12 () Bool\n    true)\n  (define-fun a3 () Int\n    (- 1))\n  (define-fun a4 () Int\n    0)\n  (define-fun q7 () Bool\n    true)\n  (define-fun n0 () MethodName\n    MethodName!val!0)\n  (define-fun q8 () Bool\n    false)\n  (define-fun q11 () Bool\n    true)\n  (define-fun q10 () Bool\n    true)\n  (define-fun q14 () Bool\n    true)\n  (define-fun x1 () Real\n    (- (/ 565.0 4467.0)))\n  (define-fun q5 () Bool\n    true)\n  (define-fun n1 () MethodName\n    MethodName!val!3)\n  (define-fun q2 () Bool\n    false)\n  (define-fun x3 () Real\n    (/ 226.0 269.0))\n  (define-fun a0 () Int\n    0)\n  (define-fun x4 () Real\n    (/ 1281985.0 639144.0))\n  (define-fun x0 () Real\n    (- (/ 113.0 1000.0)))\n  (define-fun calledBy ((x!1 MethodName)) Bool\n    false)\n)"
    ConsoleLogger.log(input)
    val parser = ParserHelper.getZ3OutputParser(input)

    val results = parser.results().toString
    println(results)
    results should be("{analysis1=Sat\n () = \nq0 (Bool) = false\nq13 (Bool) = true\nalways_true_analysis1 (Bool) = false\na2 (Int) = 0\nq4 (Bool) = false\ncond2 (Bool) = false\ncond1 (Bool) = false\nx2 (Real) = 0.0\nq1 (Bool) = false\nq9 (Bool) = false\nn3 (MethodName) = MethodName!val!2\nq3 (Bool) = false\na1 (Int) = 1\nn4 (MethodName) = MethodName!val!1\nq6 (Bool) = true\nq12 (Bool) = true\na3 (Int) = -1\na4 (Int) = 0\nq7 (Bool) = true\nn0 (MethodName) = MethodName!val!0\nq8 (Bool) = false\nq11 (Bool) = true\nq10 (Bool) = true\nq14 (Bool) = true\nx1 (Real) = -565.0/4467.0\nq5 (Bool) = true\nn1 (MethodName) = MethodName!val!3\nq2 (Bool) = false\nx3 (Real) = 226.0/269.0\na0 (Int) = 0\nx4 (Real) = 1281985.0/639144.0\nx0 (Real) = -113.0/1000.0\ncalledBy (Bool) = false}")
  }

  @Test
  def testBrokenDeclareFunCase3() {
    ConsoleLogger.enable()
    val input = "Result of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n" +
      "  ;; universe for MethodName:\n" +
      "  ;;   MethodName!val!2 MethodName!val!0 MethodName!val!1 \n" +
      "  ;; -----------\n" +
      "  ;; definitions for universe elements:\n" +
      "  (declare-fun MethodName!val!2 () MethodName)\n" +
      "  (declare-fun MethodName!val!0 () MethodName)\n" +
      "  (declare-fun MethodName!val!1 () MethodName)\n" +
      "  ;; cardinality constraint:\n" +
      "  (forall ((x MethodName))\n" +
      "          (or (= x MethodName!val!2)\n              (= x MethodName!val!0)\n              (= x MethodName!val!1)))\n" +
      "  ;; -----------\n" +
      "  (define-fun q13 () Bool\n    true)\n" +
      "  (define-fun q0 () Bool\n    true)\n" +
      "  (define-fun q4 () Bool\n    false)\n" +
      "  (define-fun cond2 () Bool\n    true)\n" +
      "  (define-fun cond1 () Bool\n    true)\n" +
      "  (define-fun x2 () Real\n    (- (/ 967.0 741.0)))\n" +
      "  (define-fun q1 () Bool\n    true)\n" +
      "  (define-fun q9 () Bool\n    true)\n" +
      "  (define-fun q3 () Bool\n    false)\n" +
      "  (define-fun n2 () MethodName\n    MethodName!val!0)\n" +
      "  (define-fun a1 () Int\n    0)\n" +
      "  (define-fun n4 () MethodName\n    MethodName!val!1)\n" +
      "  (define-fun q6 () Bool\n    true)\n" +
      "  (define-fun q12 () Bool\n    true)\n" +
      "  (define-fun a4 () Int\n    0)\n" +
      "  (define-fun a3 () Int\n    (- 1))\n" +
      "  (define-fun q7 () Bool\n    true)\n" +
      "  (define-fun n0 () MethodName\n    MethodName!val!2)\n" +
      "  (define-fun q8 () Bool\n    false)\n" +
      "  (define-fun q11 () Bool\n    true)\n" +
      "  (define-fun q10 () Bool\n    true)\n" +
      "  (define-fun q14 () Bool\n    true)\n" +
      "  (define-fun x1 () Real\n    0.0)\n" +
      "  (define-fun q5 () Bool\n    true)\n" +
      "  (define-fun n1 () MethodName\n    MethodName!val!0)\n" +
      "  (define-fun q2 () Bool\n    false)\n" +
      "  (define-fun always_false_analysis2 () Bool\n    true)\n" +
      "  (define-fun x3 () Real\n    (- (/ 3868.0 7935.0)))\n" +
      "  (define-fun a0 () Int\n    0)\n" +
      "  (define-fun x4 () Real\n    (/ 1934.0 241.0))\n" +
      "  (define-fun x0 () Real\n    (- (/ 2781092.0 4959375.0)))\n" +
      "  (define-fun calledBy!4 ((x!1 MethodName)) Bool\n    (ite (= x!1 MethodName!val!0) true\n      false))\n" +
      "  (define-fun k!3 ((x!1 MethodName)) MethodName\n    (ite (= x!1 MethodName!val!0) MethodName!val!0\n    (ite (= x!1 MethodName!val!2) MethodName!val!2\n      MethodName!val!1)))\n" +
      "  (define-fun calledBy ((x!1 MethodName)) Bool\n    (calledBy!4 (k!3 x!1)))\n" +
      ")"

    ConsoleLogger.log(input)
    val parser = ParserHelper.getZ3OutputParser(input)

    val results = parser.results().toString
    println(results)
    results should be("{analysis2=Sat\n () = \nq13 (Bool) = true\nq0 (Bool) = true\nq4 (Bool) = false\ncond2 (Bool) = true\ncond1 (Bool) = true\nx2 (Real) = -967.0/741.0\nq1 (Bool) = true\nq9 (Bool) = true\nq3 (Bool) = false\nn2 (MethodName) = MethodName!val!0\na1 (Int) = 0\nn4 (MethodName) = MethodName!val!1\nq6 (Bool) = true\nq12 (Bool) = true\na4 (Int) = 0\na3 (Int) = -1\nq7 (Bool) = true\nn0 (MethodName) = MethodName!val!2\nq8 (Bool) = false\nq11 (Bool) = true\nq10 (Bool) = true\nq14 (Bool) = true\nx1 (Real) = 0.0\nq5 (Bool) = true\nn1 (MethodName) = MethodName!val!0\nq2 (Bool) = false\nalways_false_analysis2 (Bool) = true\nx3 (Real) = -3868.0/7935.0\na0 (Int) = 0\nx4 (Real) = 1934.0/241.0\nx0 (Real) = -2781092.0/4959375.0\ncalledBy!4 (Bool) = \nk!3 (MethodName) = \ncalledBy (Bool) = }")
  }

  @Test
  def testCanDealWithBrackets() {
    ConsoleLogger.enable()
    val input = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n" +
      "  (define-fun q0 () Bool\n    false)\n" +
      "  (define-fun always_true_analysis1 () Bool\n    false)\n" +
      //      "  (define-fun a2 () Int\n    0)\n" +
      "  (define-fun q8 () Bool\n    true)\n" +
      "  (define-fun q4 () Bool\n    true)\n" +
      "  (define-fun cond2 () Bool\n    false)\n" +
      "  (define-fun cond1 () Bool\n    false)\n" +
      "  (define-fun x2 () Real\n    (/ 1180.0 8807.0))\n" +
      "  (define-fun x1 () Real\n    (/ 1180.0 5977.0))\n" +
      "  (define-fun q1 () Bool\n    false)\n" +
      "  (define-fun q5 () Bool\n    true)\n" +
      "  (define-fun q3 () Bool\n    true)\n" +
      "  (define-fun q2 () Bool\n    false)\n" +
      "  (define-fun a1 () Int\n    0)\n" +
      //      "  (define-fun a0 () Int\n    0)\n" +
      "  (define-fun q6 () Bool\n    true)\n" +
      "  (define-fun x0 () Real\n    0.0)\n" +
      "  (define-fun calledBy ((x!1 MethodName)) Bool\n false)\n)" +
      ")"
    ConsoleLogger.log(input)
    val parser = ParserHelper.getZ3OutputParser(input)

    println(parser.results())
  }

  @Test
  def testSimpleUnsatInput() {
    val input = "Result of analysis [name7 = different? cond2 cond3]:\n" +
      "unsat\n" +
      "(error \"line 73 column 10: model is not available\")"

    val parser = ParserHelper.getZ3OutputParser(input)

    val results = parser.results()
    results should contain key ("name7")
    results("name7").satResult should be(Unsat)
  }

  @Test
  def testSimpleSatInput() {
    val input = "Result of analysis [name8 = different? cond2 cond4]:\n" +
      "sat\n" +
      "(model \n  (define-fun q5 () Bool\n    true)\n" +
      "  (define-fun different_name8 () Bool\n    true)\n" +
      "  (define-fun q6 () Bool\n    true)\n)"

    val parser = ParserHelper.getZ3OutputParser(input)

    val results = parser.results()
    results should contain key ("name8")
    results("name8").satResult should be(Sat)
    results("name8").assignments(0).name should be("q5")
    results("name8").assignments(0).objectType should be("Bool")
    results("name8").assignments(0).value should be("true")
    results("name8").assignments(1).name should be("different_name8")
    results("name8").assignments(2).name should be("q6")
  }

  @Test
  def testCanDealWithReal() {
    val input = "Result of analysis [name8 = different? cond2 cond4]:\n" +
      "sat\n" +
      "(model \n" +
      "  (define-fun q5 () Real\n   0.0)\n" +
      "  (define-fun q6 () Real\n    (- 1.0))\n" +
      ")"

    val parser = ParserHelper.getZ3OutputParser(input)

    val results = parser.results()
    results should contain key ("name8")
    results("name8").satResult should be(Sat)
    results("name8").assignments(0).name should be("q5")
    results("name8").assignments(0).objectType should be("Real")
    results("name8").assignments(0).value should be("0.0")
    results("name8").assignments(1).name should be("q6")
    results("name8").assignments(1).objectType should be("Real")
    results("name8").assignments(1).value should be("-1.0")
  }

  @Test
  def testCanDealWithNestedModel() {
    val input = "Result of analysis [name8 = different? cond2 cond4]:\n" +
      "sat\n" +
      "(model \n" +
      "  (define-fun q5 () Real\n   (- (/ 1.0 2.0)))\n" +
      ")"

    val parser = ParserHelper.getZ3OutputParser(input)

    val results = parser.results()

    results("name8").assignments(0).value should be("-1.0/2.0")
  }
}
