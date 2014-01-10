package peal.verify

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.antlr.util.ParserHelper
import scala.collection.JavaConversions._


class ExplicitVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testCanVerifySimpleModel() {
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"

    val z3OutputParser = ParserHelper.getZ3OutputParser(model.mkString(""))
    val z3Models = z3OutputParser.results().toMap

    println(z3Models("name1").defines)

  }

}
