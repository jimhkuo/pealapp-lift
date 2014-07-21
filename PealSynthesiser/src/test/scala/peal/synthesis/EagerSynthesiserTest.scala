package peal.synthesis

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.util.Z3ModelMatcher
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global


class EagerSynthesiserTest extends ShouldMatchersForJUnit with Z3ModelMatcher {

  @Test
  def testCanDealWithDefaultInput() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\n" +
      "CONDITIONS\n" +
      "cond1 = pSet1 <= 0.5\n" +
      "cond2 = 0.6 < pSet2\n" +
      "cond3 = 0.5 < pSet2\n" +
      "cond4 = 0.4 < pSet2\n" +
      "DOMAIN_SPECIFICS\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1\nname3 = satisfiable? cond2\nname4 = equivalent? cond1 cond2\nname5 = different? cond1 cond2"
    val output = new EagerSynthesiser(input).generate()
    output should beZ3Model("(declare-const q5 Bool)\n" +
      "(declare-const q4 Bool)\n" +
      "(declare-const q2 Bool)\n" +
      "(declare-const q3 Bool)\n" +
      "(declare-const q1 Bool)\n" +
      "(declare-const q6 Bool)\n" +
      "(declare-const cond2 Bool)\n" +
      "(declare-const cond3 Bool)\n" +
      "(declare-const cond4 Bool)\n" +
      "(declare-const cond1 Bool)\n" +
      "(assert (= cond1 (and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))))\n(assert (= cond2 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond3 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond4 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) (and q6 q5 q4)))))\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond1 Bool)\n(assert (= always_true_alwaysTrue_cond1 cond1))\n(assert (not always_true_alwaysTrue_cond1))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond1 = always_false? cond1]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond1 Bool)\n(assert (= always_false_alwaysFalse_cond1 cond1))\n(assert always_false_alwaysFalse_cond1)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond2 = always_true? cond2]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond2 Bool)\n(assert (= always_true_alwaysTrue_cond2 cond2))\n(assert (not always_true_alwaysTrue_cond2))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond2 = always_false? cond2]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond2 Bool)\n(assert (= always_false_alwaysFalse_cond2 cond2))\n(assert always_false_alwaysFalse_cond2)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond3 = always_true? cond3]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond3 Bool)\n(assert (= always_true_alwaysTrue_cond3 cond3))\n(assert (not always_true_alwaysTrue_cond3))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond3 = always_false? cond3]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond3 Bool)\n(assert (= always_false_alwaysFalse_cond3 cond3))\n(assert always_false_alwaysFalse_cond3)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond4 = always_true? cond4]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond4 Bool)\n(assert (= always_true_alwaysTrue_cond4 cond4))\n(assert (not always_true_alwaysTrue_cond4))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond4 = always_false? cond4]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond4 Bool)\n(assert (= always_false_alwaysFalse_cond4 cond4))\n(assert always_false_alwaysFalse_cond4)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name2 = always_false? cond1]:\")\n(push)\n(declare-const always_false_name2 Bool)\n(assert (= always_false_name2 cond1))\n(assert always_false_name2)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name3 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond2))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name4 = equivalent? cond1 cond2]:\")\n(push)\n(declare-const equivalent_name4 Bool)\n(assert (= equivalent_name4 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert equivalent_name4)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name5 = different? cond1 cond2]:\")\n(push)\n(declare-const different_name5 Bool)\n(assert (= different_name5 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert different_name5)\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testCanSkipComments() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\n" +
      "CONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\n" +
      "DOMAIN_SPECIFICS\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\n" +
      "%aaa\n" +
      "ANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1\n" +
      "name3 = satisfiable? cond2\nname4 = equivalent? cond1 cond2\nname5 = different? cond1 cond2"
    val output = new EagerSynthesiser(input).generate()
    println(output)
    output should beZ3Model("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q3 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond3 Bool)\n(declare-const cond4 Bool)\n(declare-const cond1 Bool)\n(assert (= cond1 (and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))))\n(assert (= cond2 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond3 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond4 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) (and q6 q5 q4)))))\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond1 Bool)\n(assert (= always_true_alwaysTrue_cond1 cond1))\n(assert (not always_true_alwaysTrue_cond1))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond1 = always_false? cond1]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond1 Bool)\n(assert (= always_false_alwaysFalse_cond1 cond1))\n(assert always_false_alwaysFalse_cond1)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond2 = always_true? cond2]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond2 Bool)\n(assert (= always_true_alwaysTrue_cond2 cond2))\n(assert (not always_true_alwaysTrue_cond2))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond2 = always_false? cond2]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond2 Bool)\n(assert (= always_false_alwaysFalse_cond2 cond2))\n(assert always_false_alwaysFalse_cond2)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond3 = always_true? cond3]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond3 Bool)\n(assert (= always_true_alwaysTrue_cond3 cond3))\n(assert (not always_true_alwaysTrue_cond3))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond3 = always_false? cond3]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond3 Bool)\n(assert (= always_false_alwaysFalse_cond3 cond3))\n(assert always_false_alwaysFalse_cond3)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond4 = always_true? cond4]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond4 Bool)\n(assert (= always_true_alwaysTrue_cond4 cond4))\n(assert (not always_true_alwaysTrue_cond4))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond4 = always_false? cond4]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond4 Bool)\n(assert (= always_false_alwaysFalse_cond4 cond4))\n(assert always_false_alwaysFalse_cond4)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name2 = always_false? cond1]:\")\n(push)\n(declare-const always_false_name2 Bool)\n(assert (= always_false_name2 cond1))\n(assert always_false_name2)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name3 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond2))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name4 = equivalent? cond1 cond2]:\")\n(push)\n(declare-const equivalent_name4 Bool)\n(assert (= equivalent_name4 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert equivalent_name4)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name5 = different? cond1 cond2]:\")\n(push)\n(declare-const different_name5 Bool)\n(assert (= different_name5 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert different_name5)\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testCanSkipComments2() {
    val input = "POLICIES\n% policy that accumulates trust indicators for a PayPal type payment transaction\nb1 = + ((lowCostTransaction 0.3) (enoughMutualFriends 0.1) (enoughMutualFriendsNormalized 0.2)) default 0\n% policy that extracts the worst possible signal for distrusting such a transaction\nb2 = min ((highCostTransaction 0.1) (aFriendOfAliceUnfriendedBob 0.2) (aFriendOfAliceVouchesForBob 0.6)) default 1\nPOLICY_SETS\n% policy set that conservatively (given threshold type th < cond below) combines both policies above\npSet1 = min(b1,b2)\n" +
      "CONDITIONS\n% asking whether combined trust score is above a certain threshold\ncond1 = 0.5 < pSet1\n% asking whether the combined trust score is above a higher such threshold\ncond2 = 0.6 < pSet1\n" +
      "DOMAIN_SPECIFICS\n% the amount of the pondered online transaction\n(declare-const amountAlicePays Real)\n% the number of friends that payer and payee share\n(declare-const numberOfMutualFriends Real)\n% number of friends of payee\n(declare-const numberOfBobsFriends Real)\n% definition of what a low cost transaction represents\n(assert (= lowCostTransaction (< amountAlicePays 100)))\n% definition of what a high cost transaction represents\n% note that these transaction types are not logical negations of each other\n(assert (= highCostTransaction (< 1000 amountAlicePays)))\n% definition of that it means to have enough mutual friends for this intended transaction\n(assert (= enoughMutualFriends (< 4 numberOfMutualFriends)))\n% a variant of such a definition that may be more resilient to abnormal values, e.g. for celebrities\n(assert (= enoughMutualFriendsNormalized (< numberOfBobsFriends (* 100 numberOfMutualFriends))))\nANALYSES\n% asking whether the combined trust score can exceed threshold 0.5\nname1 = satisfiable? cond1\n% asking whether the above condition is always true, which would suggest a specification error\nname2 = always_true? cond1\n% asking whether the above condition is always false, which would suggest a specification error\nname3 = always_false? cond1\n% asking whether one threshold behavior implies another, should be the case as 0.5 < 0.6 holds\nname4 = implies? cond2 cond1\n% asking whether both threshold behaviors are equivalent, this may be true or false in general\n% but it is false in this instance\nname5 = equivalent? cond1 cond2"
    val output = new EagerSynthesiser(input).generate()
    println(output)
    output should beZ3Model("(declare-const enoughMutualFriendsNormalized Bool)\n(declare-const enoughMutualFriends Bool)\n(declare-const lowCostTransaction Bool)\n(declare-const aFriendOfAliceVouchesForBob Bool)\n(declare-const highCostTransaction Bool)\n(declare-const aFriendOfAliceUnfriendedBob Bool)\n(declare-const cond2 Bool)\n(declare-const cond1 Bool)\n(assert (= cond1 (and (and (or lowCostTransaction enoughMutualFriends enoughMutualFriendsNormalized) (and lowCostTransaction enoughMutualFriendsNormalized enoughMutualFriends)) (or (and (not highCostTransaction) (not aFriendOfAliceUnfriendedBob) (not aFriendOfAliceVouchesForBob)) (not (or highCostTransaction aFriendOfAliceUnfriendedBob))))))\n(assert (= cond2 (and (and (or lowCostTransaction enoughMutualFriends enoughMutualFriendsNormalized) false) (or (and (not highCostTransaction) (not aFriendOfAliceUnfriendedBob) (not aFriendOfAliceVouchesForBob)) (not (or highCostTransaction aFriendOfAliceUnfriendedBob aFriendOfAliceVouchesForBob))))))\n(declare-const amountAlicePays Real)\n(declare-const numberOfMutualFriends Real)\n(declare-const numberOfBobsFriends Real)\n(assert (= lowCostTransaction (< amountAlicePays 100)))\n(assert (= highCostTransaction (< 1000 amountAlicePays)))\n(assert (= enoughMutualFriends (< 4 numberOfMutualFriends)))\n(assert (= enoughMutualFriendsNormalized (< numberOfBobsFriends (* 100 numberOfMutualFriends))))\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond1 Bool)\n(assert (= always_true_alwaysTrue_cond1 cond1))\n(assert (not always_true_alwaysTrue_cond1))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond1 = always_false? cond1]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond1 Bool)\n(assert (= always_false_alwaysFalse_cond1 cond1))\n(assert always_false_alwaysFalse_cond1)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond2 = always_true? cond2]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond2 Bool)\n(assert (= always_true_alwaysTrue_cond2 cond2))\n(assert (not always_true_alwaysTrue_cond2))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond2 = always_false? cond2]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond2 Bool)\n(assert (= always_false_alwaysFalse_cond2 cond2))\n(assert always_false_alwaysFalse_cond2)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = satisfiable? cond1]:\")\n(push)\n(declare-const satisfiable_name1 Bool)\n(assert (= satisfiable_name1 cond1))\n(assert satisfiable_name1)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name2 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name2 Bool)\n(assert (= always_true_name2 cond1))\n(assert (not always_true_name2))\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name3 = always_false? cond1]:\")\n(push)\n(declare-const always_false_name3 Bool)\n(assert (= always_false_name3 cond1))\n(assert always_false_name3)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name4 = implies? cond2 cond1]:\")\n(push)\n(declare-const implies_name4 Bool)\n(assert (= implies_name4 (and cond2 (not cond1))))\n(assert implies_name4)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name5 = equivalent? cond1 cond2]:\")\n(push)\n(declare-const equivalent_name5 Bool)\n(assert (= equivalent_name5 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert equivalent_name5)\n(check-sat)\n(get-model)\n(pop)")
  }

  @Test
  def testScalaFuture() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\n" +
      "CONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = 0.5 < pSet2\ncond4 = 0.4 < pSet2\n" +
      "DOMAIN_SPECIFICS\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1\nname3 = satisfiable? cond2\nname4 = equivalent? cond1 cond2\nname5 = different? cond1 cond2"

    val outputFuture = future {
      new EagerSynthesiser(input).generate()
    }

    val output = Await.result(outputFuture, 5000 millis)

    output should beZ3Model ("(declare-const q5 Bool)\n(declare-const q4 Bool)\n(declare-const q2 Bool)\n(declare-const q3 Bool)\n(declare-const q1 Bool)\n(declare-const q6 Bool)\n(declare-const cond2 Bool)\n(declare-const cond3 Bool)\n(declare-const cond4 Bool)\n(declare-const cond1 Bool)\n(assert (= cond1 (and (and (or q1 q2 q3) (or q1 q2)) (or (and (not q4) (not q5) (not q6)) (not false)))))\n(assert (= cond2 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond3 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) false))))\n(assert (= cond4 (and (or (and (not q1) (not q2) (not q3)) (not (or q1 q2))) (and (or q4 q5 q6) (and q6 q5 q4)))))\n(declare-const x Real)\n(declare-const y Real)\n(assert (= q1 (< x (+ y 1))))\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond1 Bool)\n(assert (= always_true_alwaysTrue_cond1 cond1))\n(assert (not always_true_alwaysTrue_cond1))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond1 = always_false? cond1]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond1 Bool)\n(assert (= always_false_alwaysFalse_cond1 cond1))\n(assert always_false_alwaysFalse_cond1)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond2 = always_true? cond2]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond2 Bool)\n(assert (= always_true_alwaysTrue_cond2 cond2))\n(assert (not always_true_alwaysTrue_cond2))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond2 = always_false? cond2]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond2 Bool)\n(assert (= always_false_alwaysFalse_cond2 cond2))\n(assert always_false_alwaysFalse_cond2)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond3 = always_true? cond3]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond3 Bool)\n(assert (= always_true_alwaysTrue_cond3 cond3))\n(assert (not always_true_alwaysTrue_cond3))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond3 = always_false? cond3]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond3 Bool)\n(assert (= always_false_alwaysFalse_cond3 cond3))\n(assert always_false_alwaysFalse_cond3)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysTrue_cond4 = always_true? cond4]:\")\n(push)\n(declare-const always_true_alwaysTrue_cond4 Bool)\n(assert (= always_true_alwaysTrue_cond4 cond4))\n(assert (not always_true_alwaysTrue_cond4))\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of vacuity check [alwaysFalse_cond4 = always_false? cond4]:\")\n(push)\n(declare-const always_false_alwaysFalse_cond4 Bool)\n(assert (= always_false_alwaysFalse_cond4 cond4))\n(assert always_false_alwaysFalse_cond4)\n(check-sat)\n(get-model)\n(pop)\n" +
      "(echo \"Result of analysis [name1 = always_true? cond1]:\")\n(push)\n(declare-const always_true_name1 Bool)\n(assert (= always_true_name1 cond1))\n(assert (not always_true_name1))\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name2 = always_false? cond1]:\")\n(push)\n(declare-const always_false_name2 Bool)\n(assert (= always_false_name2 cond1))\n(assert always_false_name2)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name3 = satisfiable? cond2]:\")\n(push)\n(declare-const satisfiable_name3 Bool)\n(assert (= satisfiable_name3 cond2))\n(assert satisfiable_name3)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name4 = equivalent? cond1 cond2]:\")\n(push)\n(declare-const equivalent_name4 Bool)\n(assert (= equivalent_name4 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert equivalent_name4)\n(check-sat)\n(get-model)\n(pop)\n(echo \"Result of analysis [name5 = different? cond1 cond2]:\")\n(push)\n(declare-const different_name5 Bool)\n(assert (= different_name5 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n(assert different_name5)\n(check-sat)\n(get-model)\n(pop)")
  }
}
