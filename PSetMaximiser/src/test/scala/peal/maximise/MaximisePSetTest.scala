package peal.maximise

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class MaximisePSetTest extends ShouldMatchersForJUnit {

  @Test
  def testSomething() {
    val input = "POLICIES\ngoal = +((True or1_score)) default 1.0\nor1 = +((True 1.0) (True -1.0*or1_aux_score)) default 1.0\nor1_aux = *((True or1_aux1_score) (True or1_aux2_score)) default 1.0\nor1_aux1 = +((True 1.0) (True -1.0*and1_score)) default 1.0\nor1_aux2 = +((True 1.0) (True -1.0*and2_score)) default 1.0\nand1 = *((True and3_score) (True not1_score)) default 1.0\nand3 = *((True or2_score) (True and6_score)) default 1.0\nor2 = +((True 1.0) (True -1.0*or2_aux_score)) default 1.0\nor2_aux = *((True or2_aux1_score) (True or2_aux2_score)) default 1.0\nor2_aux1 = +((True 1.0) (True -1.0*a111_score)) default 1.0\nor2_aux2 = +((True 1.0) (True -1.0*or3_score)) default 1.0\na111 = +((sendRSTmessageToTCPStack 0.08)) default 0.0\nor3 = +((True 1.0) (True -1.0*or3_aux_score)) default 1.0\nor3_aux = *((True or3_aux1_score) (True or3_aux2_score) (True or3_aux3_score)) default 1.0\nor3_aux1 = +((True 1.0) (True -1.0*a1121_score)) default 1.0\nor3_aux2 = +((True 1.0) (True -1.0*a1122_score)) default 1.0\nor3_aux3 = +((True 1.0) (True -1.0*a1123_score)) default 1.0\na1121 = +((notify 0.1)) default 0.0\na1122 = +((open 0.15)) default 0.0\na1123 = +((keepAlive 0.2)) default 0.0\nand6 = *((True a12_score) (True not3_score)) default 1.0\na12 = +((TCPsequenceNumberAttack 0.1)) default 0.0\nnot3 = +((True 1.0) (True -1.0*and7_score)) default 1.0\nand7 = +((TCPsequenceNumberCheck and7_aux1_score)) default 0.0\nand7_aux1 = +((MD5authentication and7_aux2_score)) default 0.0\nand7_aux2 = *((True 0.8) (True 0.5)) default 1.0\nnot1 = +((True 1.0) (True -1.0*and4_score)) default 1.0\nand4 = +((traceRouteCheck and4_aux1_score)) default 0.0\nand4_aux1 = +((randomizeSequenceNumbers and4_aux2_score)) default 0.0\nand4_aux2 = *((True 0.5) (True 0.6)) default 1.0\nand2 = *((True a2_score) (True not2_score)) default 1.0\na2 = +((alterConfigurationViaCompromisedRouter 0.4)) default 0.0\nnot2 = +((True 1.0) (True -1.0*and5_score)) default 1.0\nand5 = +((routerFirewallAlert and5_aux1_score)) default 0.0\nand5_aux1 = +((secureRouter and5_aux2_score)) default 0.0\nand5_aux2 = *((True 0.7) (True 0.5)) default 1.0\ncost_a111 = +((sendRSTmessageToTCPStack 50.0)) default 0.0\ncost_a1121 = +((notify 60.0)) default 0.0\ncost_a1122 = +((open 70.0)) default 0.0\ncost_a1123 = +((keepAlive 100.0)) default 0.0\ncost_a12 = +((TCPsequenceNumberAttack 150.0)) default 0.0\ncost_a2 = +((alterConfigurationViaCompromisedRouter 190.0)) default 0.0\ncost_overall = +((True cost_a111_score) (True cost_a1121_score) (True cost_a1122_score) (True cost_a1123_score) (True cost_a2_score) (True cost_a12_score)) default 0.0\nimpact_or2 = max((sendRSTmessageToTCPStack 200.0) (notify 130.0) (open 100.0) (keepAlive 300.0)) default 0.0\nimpact_and3 = +((True impact_or2_score) (TCPsequenceNumberAttack 250.0)) default 0.0\nimpact_and1 = +((True impact_and3_score)) default 0.0\nimpact_and2 = +((alterConfigurationViaCompromisedRouter 275.0)) default 0.0\nimpact_overall = max((True impact_and1_score) (True impact_and2_score)) default 0.0\nmetric_aux1 = +((True 2*impact_overall_score) (True -1*cost_overall_score)) default 0.0\nmetric_aux2 = max((True 0.0) (True metric_aux1_score)) default 0.0\nmetric = *((True goal_score) (True metric_aux2_score)) default 0.0\n" +
      "POLICY_SETS\n" +
      "pSet_goal = goal\npSet_cost_overall =  cost_overall\npSet_impact_overall =  impact_overall\npSet_metric = metric\n" +
      "CONDITIONS\n" +
      "c8 = 271.919999999999 < pSet_metric\n" +
      "DOMAIN_SPECIFICS\n" +
      "(assert True)\n" +
      "ANALYSES\n" +
      "name8 = satisfiable? c8"
    println(input)
    val expected = 271.92
  }

}
