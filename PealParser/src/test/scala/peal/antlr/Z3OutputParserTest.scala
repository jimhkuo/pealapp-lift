package peal.antlr

import org.junit.{After, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import peal.domain.z3.{PealAst, Term, Sat, Unsat}
import peal.util.Z3ModelMatcher
import peal.antlr.util.ParserHelper


class Z3OutputParserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val consts = Map[String, PealAst]("q0" -> Term("q0"), "q1" -> Term("q1"), "q2" -> Term("q2"), "q3" -> Term("q3"), "q4" -> Term("q4"), "q5" -> Term("q5"), "q6" -> Term("q6"))

  @Test
  def testIncludeCommentedInput() {
    val input = "Result of analysis [name7 = different? cond2 cond3]:\n" +
      "unsat\n" +
      ";; universe for MethodName:\n" +
      "(error \"line 73 column 10: model is not available\")"

    val parser = ParserHelper.getZ3OutputParser(input)

    parser.results()

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
    results("name8").defines(0).name should be("q5")
    results("name8").defines(0).objectType should be("Bool")
    results("name8").defines(0).value should be("true")
    results("name8").defines(1).name should be("different_name8")
    results("name8").defines(2).name should be("q6")
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
    results("name8").defines(0).name should be("q5")
    results("name8").defines(0).objectType should be("Real")
    results("name8").defines(0).value should be("0.0")
    results("name8").defines(1).name should be("q6")
    results("name8").defines(1).objectType should be("Real")
    results("name8").defines(1).value should be("(- 1.0)")
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

    results("name8").defines(0).value should be("(- (/ 1.0 2.0))")
  }
}
