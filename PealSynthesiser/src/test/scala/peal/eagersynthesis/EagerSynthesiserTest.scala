package peal.eagersynthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.util.Z3ModelMatcher
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global


class EagerSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Test
  def testCanDealWithBrokenCase() {
    val input = "POLICIES\nb0 = max ((q5 0.2870553923966146)) default 0.2754709964081127\nb1 = min ((q2 0.17422516354876516) (q3 0.16400770504931794)) default 0.6527881417615584\nb2 = + ((q1 0.39297033422957317)) default 0.5850051353683534\nb3 = + ((q0 0.898241844672469)) default 0.1801862485549579\nb4 = max ((q0 0.4927654111714618)) default 0.63593014089844\nb5 = min ((q0 0.24074142095675122) (q2 0.3326955917865865)) default 0.6900310664667637\nb6 = * ((q2 0.7290851225535752)) default 0.16271959366997735\nb7 = * ((q0 0.18268848997170217)) default 0.6256318055534886\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = min(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nDOMAIN_SPECIFICS\n(declare-const x0 Real)\n(declare-const x1 Real)\n(declare-const a0 Int)\n(declare-const a1 Int)\n(declare-sort MethodName)\n(declare-fun calledBy (MethodName) Bool)\n(assert (forall ((n MethodName) (m MethodName)) (or (= m n) (implies (calledBy m) (not (calledBy n))))))\n(declare-const n0 MethodName)\n(declare-const n1 MethodName)\n(assert (= q0 (calledBy n1)))\n(assert (= q1 (calledBy n0)))\n(assert (= q2 (< a0 (+ a1 0.28037263734860873))))\n(assert (= q3 (< a0 (+ a0 0.900254236640555))))\n(assert (= q4 (< x1 (* x1 0.871479880468587))))\n(assert (= q5 (< x1 (* x1 0.10401949740081284))))\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2"
    val output = new EagerSynthesiser().generate(input)
    println(output)
//    output should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q3 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond3 Bool)\n(declare-const cond4 Bool)\n(declare-const cond1 Bool)\n(assert (= cond1 (and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))))\n(assert (= cond2 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond3 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond4 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) (and q6 q5 q4)))))\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name2 = always_false? cond1]:\")\n(push)\n(declare-const always_false_name2 Bool)\n(assert (= always_false_name2 cond1))\n(assert always_false_name2)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name3 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond2))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name4 = equivalent? cond1 cond2]:\")\n(push)\n(declare-const equivalent_name4 Bool)\n(assert (= equivalent_name4 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert equivalent_name4)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name5 = different? cond1 cond2]:\")\n(push)\n(declare-const different_name5 Bool)\n(assert (= different_name5 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert different_name5)\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testCanDealWithDefaultInput() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1\nname3 = satisfiable? cond2\nname4 = equivalent? cond1 cond2\nname5 = different? cond1 cond2"
    val output = new EagerSynthesiser().generate(input)
    output should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q3 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond3 Bool)\n(declare-const cond4 Bool)\n(declare-const cond1 Bool)\n(assert (= cond1 (and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))))\n(assert (= cond2 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond3 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond4 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) (and q6 q5 q4)))))\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\n(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name2 = always_false? cond1]:\")\n(push)\n(declare-const always_false_name2 Bool)\n(assert (= always_false_name2 cond1))\n(assert always_false_name2)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name3 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond2))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name4 = equivalent? cond1 cond2]:\")\n(push)\n(declare-const equivalent_name4 Bool)\n(assert (= equivalent_name4 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert equivalent_name4)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name5 = different? cond1 cond2]:\")\n(push)\n(declare-const different_name5 Bool)\n(assert (= different_name5 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert different_name5)\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testScalaFuture() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\nDOMAIN_SPECIFICS\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1\nname3 = satisfiable? cond2\nname4 = equivalent? cond1 cond2\nname5 = different? cond1 cond2"

    val outputFuture = future {
      new EagerSynthesiser().generate(input)
    }

    val output = Await.result(outputFuture, 5000 millis)
//    outputFuture onSuccess{
//      case x => println(x)
//    }
//    val output = outputFuture.map(o => o)

    println(output)

  }
}
