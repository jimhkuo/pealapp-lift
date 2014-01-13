package peal.verify

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.synthesis.EagerSynthesiser
import peal.z3.Z3Caller


class ExplicitOutputVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testCanVerifySimpleModel() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1,b1)\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()

//    println(z3SMTInput)

    val model = Z3Caller.call(z3SMTInput)

//    println(model)


    new ExplicitOutputVerifier(input).verifyModel(model, "name1")
  }
}
