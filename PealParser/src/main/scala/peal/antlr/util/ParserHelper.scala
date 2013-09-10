package peal.antlr.util

import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{Z3OutputParser, Z3OutputLexer, PealProgramParser, PealProgramLexer}

object ParserHelper {

  def getPealParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  def getZ3OutputParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new Z3OutputLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new Z3OutputParser(tokenStream)
  }
}
