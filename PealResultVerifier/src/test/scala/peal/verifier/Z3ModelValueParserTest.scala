package peal.verifier

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.{Ignore, Test}
import peal.domain.{PealTrue, PealFalse}


class Z3ModelValueParserTest extends ShouldMatchersForJUnit {

   @Test
   def testCanParseThreeWayBool() {
     Z3ModelValueParser.parse("false") should be (Right(PealFalse))
     Z3ModelValueParser.parse("true") should be (Right(PealTrue))
   }

  @Ignore("wip")
  @Test
  def testCanParseNumber() {
    Z3ModelValueParser.parse("(/ 1.0 2.0)") should be (Left(BigDecimal(0.5)))
  }
 }
