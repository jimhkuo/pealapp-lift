package peal.runner

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test

class ResultAnalyserTest extends ShouldMatchersForJUnit {

//  val spec = """([-\w]+) ([-\d ]+)[,]G[,](?s).*[,]W0 [(]([-\d]+) nodes[)]:[,]W1 [(]([-\d]+) nodes[)]:[,]([-\d.]+)(?s).*[,]P(?s).*[,]([-\w.]+)[,](?s).*""".r
  val spec = """Result of analysis \[([-\w. =?]+)\]:""".r

  @Test
  def testCanDealWithZ3Model() {
//    val input = "Result of analysis [analysis1 = always_true? cond1]:\n\nsat\n\n(model \n\n  (define-fun cond1 () Bool\n\n    false)\n\n  (define-fun q5 () Bool\n\n    false)\n\n  (define-fun q0 () Bool\n\n    false)\n\n  (define-fun always_true_analysis1 () Bool\n\n    false)"
    val input = "Result of analysis [analysis1 = always_true? cond1]:"

    val spec(name) = input
    println(name)

    name should be ("analysis1 = always_true? cond1")
  }
}
