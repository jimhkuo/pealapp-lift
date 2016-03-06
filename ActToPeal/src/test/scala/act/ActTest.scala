package act

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class ActTest extends ShouldMatchersForJUnit {

  val m2 = MitLeaf("2", "secureRouter", 0.5, 35)
  val d2 = DetLeaf("2", "routerFirewallAlert", 0.7, 15)
  val and5 = AndDmAct("5", d2, m2)
  val not2 = NotLeaf("2", and5)
  val a2 = AttLeaf("2", "alterConfigurationViaCompromisedRouter", 0.4, 190, 275)
  val and2 = AndLeaf("2", a2, not2)
  val m1 = MitLeaf("1", "randomizeSequenceNumbers", 0.6, 30)
  val d1 = DetLeaf("1", "traceRouteCheck", 0.5, 10)
  val and4 = AndDmAct("4", d1, m1)
  val not1 = NotLeaf("1", and4)
  val m12 = MitLeaf("12", "MD5authentication", 0.5, 20)
  val d12 = DetLeaf("12", "TCPsequenceNumberCheck", 0.8, 10)
  val and7 = AndDmAct("7", d12, m12)
  val not3 = NotLeaf("3", and7)
  val a12 = AttLeaf("12", "TCPsequenceNumberAttack", 0.1, 150, 250)
  val and6 = AndLeaf("6", a12, not3)
  val a1123 = AttLeaf("1123", "keepAlive", 0.2, 100, 300)
  val a1122 = AttLeaf("1122", "open", 0.15, 70, 100)
  val a1121 = AttLeaf("1121", "notify", 0.1, 60, 130)
  val or3 = OrLeaf("3", a1121, a1122, a1123)
  val a111 = AttLeaf("111", "sendRSTmessageToTCPStack", 0.08, 50, 200)
  val or2 = OrLeaf("2", a111, or3)
  val and3 = AndLeaf("3", or2, and6)
  val and1 = AndLeaf("1", and3, not1)
  val bgp = OrLeaf("1", and1, and2)

  @Test
  def testCanConstructActTreeInNotes() {
    println(bgp)
    fail()
  }

  @Test
  def testGeneratePealInput() {
    val expected = "attack_success_probability = +((True prob_or_1_score)) default 0.0\n" +
      "prob_or_1 = +((True 1.0) (True -1.0*prob_or_1_aux_score)) default 0.0\n" +
      "prob_or_1_aux = *((True prob_or_1_aux1_score) (True prob_or_1_aux2_score)) default 0.0\n" +
      "prob_or_1_aux1 = +((True 1.0) (True -1.0*prob_and_1_score)) default 0.0\n" +
      "prob_or_1_aux2 = +((True 1.0) (True -1.0*prob_and_2_score)) default 0.0\n" +
      "prob_and_1 = *((True prob_and_3_score) (True prob_not_1_score)) default 0.0\n" +
      "prob_and_3 = *((True prob_or_2_score) (True prob_and_6_score)) default 0.0\n" +
      "prob_or_2 = +((True 1.0) (True -1.0*prob_or_2_aux_score)) default 0.0\n" +
      "prob_or_2_aux = *((True prob_or_2_aux1_score) (True prob_or_2_aux2_score)) default 0.0\n" +
      "prob_or_2_aux1 = +((True 1.0) (True -1.0*prob_att_leaf_111_score)) default 0.0\n" +
      "prob_or_2_aux2 = +((True 1.0) (True -1.0*prob_or_3_score)) default 0.0\n" +
      "prob_att_leaf_111 = +((sendRSTmessageToTCPStack 0.08)) default 0.0\n" +
      "prob_or_3 = +((True 1.0) (True -1.0*prob_or_3_aux_score)) default 0.0\n" +
      "prob_or_3_aux = *((True prob_or_3_aux1_score) (True prob_or_3_aux2_score) (True prob_or_3_aux3_score)) default 0.0\n" +
      "prob_or_3_aux1 = +((True 1.0) (True -1.0*prob_att_leaf_1121_score)) default 0.0\n" +
      "prob_or_3_aux2 = +((True 1.0) (True -1.0*prob_att_leaf_1122_score)) default 0.0\n" +
      "prob_or_3_aux3 = +((True 1.0) (True -1.0*prob_att_leaf_1123_score)) default 0.0\n" +
      "prob_att_leaf_1121 = +((notify 0.1)) default 0.0\n" +
      "prob_att_leaf_1122 = +((open 0.15)) default 0.0\n" +
      "prob_att_leaf_1123 = +((keepAlive 0.2)) default 0.0\n" +
      "prob_and_6 = *((True prob_att_leaf_12_score) (True prob_not_3_score)) default 0.0\n" +
      "prob_att_leaf_12 = +((TCPsequenceNumberAttack 0.1)) default 0.0\n" +
      "prob_not_3 = +((True 1.0) (True -1.0*prob_and_7_score)) default 0.0\n" +
      "prob_and_7 = *((True prob_det_leaf_12_score) (True prob_mit_leaf_12_score)) default 0.0\n" +
      "prob_det_leaf_12 = +((TCPsequenceNumberCheck 0.8)) default 0.0\n" +
      "prob_mit_leaf_12 = +((MD5authentication 0.5)) default 0.0\n" +
      "prob_not_1 = +((True 1.0) (True -1.0*prob_and_4_score)) default 0.0\n" +
      "prob_and_4 = *((True prob_det_leaf_1_score) (True prob_mit_leaf_1_score)) default 0.0\n" +
      "prob_det_leaf_1 = +((traceRouteCheck 0.5)) default 0.0\n" +
      "prob_mit_leaf_1 = +((randomizeSequenceNumbers 0.6)) default 0.0\n" +
      "prob_and_2 = *((True prob_att_leaf_2_score) (True prob_not_2_score)) default 0.0\n" +
      "prob_att_leaf_2 = +((alterConfigurationViaCompromisedRouter 0.4)) default 0.0\n" +
      "prob_not_2 = +((True 1.0) (True -1.0*prob_and_5_score)) default 0.0\n" +
      "prob_and_5 = *((True prob_det_leaf_2_score) (True prob_mit_leaf_2_score)) default 0.0\n" +
      "prob_det_leaf_2 = +((routerFirewallAlert 0.7)) default 0.0\n" +
      "prob_mit_leaf_2 = +((secureRouter 0.5)) default 0.0\n"

    val out = ActToPeal.execute(bgp)
    println(out)
    out should be(expected)
  }
}
