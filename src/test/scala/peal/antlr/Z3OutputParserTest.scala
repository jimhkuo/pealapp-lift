package peal.antlr

import org.junit.{After, Test}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import z3.scala.{Z3AST, Z3Config, Z3Context}
import peal.util.Z3ModelMatcher


class Z3OutputParserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {
  val z3: Z3Context = new Z3Context(new Z3Config("MODEL" -> true))
  val consts = Map[String, Z3AST]("q1" -> z3.mkBoolConst("q1"), "q2" -> z3.mkBoolConst("q2"), "q3" -> z3.mkBoolConst("q3"), "q4" -> z3.mkBoolConst("q4"), "q5" -> z3.mkBoolConst("q5"), "q6" -> z3.mkBoolConst("q6"))

  @After def tearDown() {
    z3.delete()
  }

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new Z3OutputLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new Z3OutputParser(tokenStream)
  }

  @Test
  def testSimpleUnsatInput() {
    val input = "Result of analysis [name7 = different? cond2 cond3]:\n" +
      "unsat\n" +
      "(error \"line 73 column 10: model is not available\")"

    val parser = getParser(input)

    parser.results()

  }
}
