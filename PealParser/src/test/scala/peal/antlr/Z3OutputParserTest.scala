package peal.antlr

import org.junit.{Ignore, After, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import peal.domain.z3.{PealAst, Term, Sat, Unsat}
import peal.util.{ConsoleLogger, Z3ModelMatcher}
import peal.antlr.util.ParserHelper


class Z3OutputParserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Ignore("work in progress")
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
  def testBroken2() {
    ConsoleLogger.enable()
    val originalInput = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  ;; universe for MethodName:\n  ;;   MethodName!val!0 MethodName!val!1 \n  ;; -----------\n  ;; definitions for universe elements:\n  (declare-fun MethodName!val!0 () MethodName)\n  (declare-fun MethodName!val!1 () MethodName)\n  ;; cardinality constraint:\n  (forall ((x MethodName)) (or (= x MethodName!val!0) (= x MethodName!val!1)))\n  ;; -----------\n  (define-fun q0 () Bool\n    false)\n  (define-fun n0 () MethodName\n    MethodName!val!1)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun a2 () Int\n    0)\n  (define-fun q8 () Bool\n    true)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun x2 () Real\n    (/ 1180.0 8807.0))\n  (define-fun x1 () Real\n    (/ 1180.0 5977.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q5 () Bool\n    true)\n  (define-fun q3 () Bool\n    true)\n  (define-fun n2 () MethodName\n    MethodName!val!0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun a1 () Int\n    0)\n  (define-fun a0 () Int\n    0)\n  (define-fun q6 () Bool\n    true)\n  (define-fun x0 () Real\n    0.0)\n  (define-fun calledBy ((x!1 MethodName)) Bool\n" +
      "    false)\n)"
    val input = originalInput.split("\n").filterNot(_.trim.startsWith(";;")).mkString("\n")
    ConsoleLogger.log(input)
    val parser = ParserHelper.getZ3OutputParser(input)

    println(parser.results())
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
//      "  (define-fun a1 () Int\n    0)\n" +
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
