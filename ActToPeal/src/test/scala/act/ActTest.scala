package act

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class ActTest extends ShouldMatchersForJUnit {

  @Test
  def testCanConstructActTreeInNotes() {
    //    BGP_reset_session = ({ type = OR, name = "or1", children = [and1, and2] )
    //      and1 = { type = AND, name = "1", children = [and3, not1] }
    //      and3 = { type = AND, name = "3", comment="a1: send message to router causing reset", children = [or2, and6] }
    //      or2 = { type = OR, name = "2", children = [a111, a112] }
    //      a111 = { type = ATTACK_LEAF, name = "111", comment = "a111: Send RST message to TCP stack", pred = "sendRSTmessageToTCPStack", probability = 0.08, cost = 50, impact = 200 }
    //      or3 = { type = OR, name = "3", comment="a112: Send BGP message", children = [a1121, a1122, a1123] }
    //      a1121 = { type = ATTACK_LEAF, name = "1121", comment = "a1121: Notify", pred = "notify", probability = 0.1, cost = 60, impact = 130 }
    //      a1122 = { type = ATTACK_LEAF, name = "1122", comment = "a1122: Open", pred = "open", probability = 0.15, cost = 70, impact = 100 }
    //      a1123 = { type = ATTACK_LEAF, name = "1123", comment = "a1123: Keep alive", pred = "keepAlive", probability = 0.2, cost = 100, impact = 300 }
    //      and6 = { type = AND, name = "6", children = [a12, not3] }
    //      a12 = { type = ATTACK_LEAF, name = "12", comment = "a12: TCP sequence number attack", pred = "TCPsequenceNumberAttack", probability = 0.1, cost = 150, impact = 250 }
    //      not3 = { type = NOT, name = "3", children = [and7] }
    //      and7 = { type = AND, name = "7", children = [d12, m12] }
    //      d12 = { type = DETECTION_LEAF, name = "12", comment = "d12: TCP sequence number check", pred = "TCPsequenceNumberCheck", probability = 0.8, securityInvestmentCost = 10 }
    //      m12 = { type = MITIGATION_LEAF, name = "12", comment = "m12: MD5 authentication", pred = "MD5authentication", probability = 0.5, securityInvestmentCost = 20 }
    //      not1 = { type = NOT, name = "1", children = [and4] }
    //      and4 = { type = AND, name = "4", children = [d1, m1] }
    //      d1 = { type = DETECTION_LEAF, name = "1", comment = "d1: Trace-route check", pred = "traceRouteCheck", probability = 0.5, securityInvestmentCost = 10 }
    //      m1 = { type = MITIGATION_LEAF, name = "1", comment = "m1: Randomize sequence numbers", pred = "randomizeSequenceNumbers", probability = 0.6, securityInvestmentCost = 30 }
    //      and2 = { type = AND, name = "2", children = [a2, not2] }
    //      a2 = { type = ATTACK_LEAF, name = "2", comment = "a2: Alter configuration via compromised router", pred = "alterConfigurationViaCompromisedRouter", probability = 0.4, cost = 190, impact = 275 }
    //      not2 = { type = NOT, name = "2", children = [and5] }
    //      and5 = {type = AND, name = "5", children = [d2, m2] }
    //      d2 = { type = DETECTION_LEAF, name = "2", comment = "d2: Router firewall alert", pred = "routerFirewallAlert", probability = 0.7, securityInvestmentCost = 15 }
    //      m2 = { type = MITIGATION_LEAF, name = "2", comment = "m2: Secure router", pred = "secureRouter", probability = 0.5, securityInvestmentCost = 35 }

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
    val bgp = OrLeaf("or1", and1, and2)
  }

}
