package peal.antlr

import org.junit.Test
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import org.scalatest.junit.ShouldMatchersForJUnit

class ParserTest extends ShouldMatchersForJUnit {


  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  @Test
  def test() {
    val parser: PealProgramParser = getParser("cond = pSet <= 0.5\npSet = max(b1, b2)\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0")
    parser.program()


    println(parser.pols)

  }

}
