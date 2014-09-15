package peal.specialisation

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.util.ConsoleLogger


class PolicySpecialisationMakerTest extends ShouldMatchersForJUnit {

  @Test
  def testRecursiveToTheEnd() {
//    ConsoleLogger.enable()
    val input = "POLICIES\npr_wood = min((teak 0.08) (birch 0.099) (walnut 0.07)) default 0.09\nfuel = +((gasoline 0.1) (coal 0.02) (wood pr_wood_score)) default 0\nignition = +((matches 0.2) (gas_stove 0.1) (electrical_sparc 0.05)) default 0\noxygen = +() default 1\nfire = *((Fuel fuel_score) (Ignition ignition_score) (Oxygen oxygen_score)) default 1\nPOLICY_SETS\npSet1 = fire\nCONDITIONS\ncond1 = 0.0734 < pSet1\ncond2 = 0.0735 < pSet1\ncond3 = pSet1 <= 0.00735\ncoal_cond = coal\ncond4 = !coal_cond\ncond5 = cond4 && cond1\nDOMAIN_SPECIFICS\n(assert (= Fuel (< 0 fuel_score)))\n(assert (= Oxygen (< 0 oxygen_score)))\n(assert (= Ignition (< 0 ignition_score)))\n(assert (and Fuel Ignition Oxygen))\nANALYSES\nname1 = satisfiable? cond1"
    val model = "Result of analysis [name1 = satisfiable? cond1]:\nsat\n(model \n  " +
      "(define-fun ignition_score () Real\n    (/ 7.0 20.0))\n  " +
      "(define-fun teak () Bool\n    false)\n  " +
      "(define-fun cond3 () Bool\n    false)\n  " +
      "(define-fun cond5 () Bool\n    false)\n  " +
      "(define-fun wood () Bool\n    true)\n  " +
      "(define-fun pr_wood_score () Real\n    (/ 9.0 100.0))\n  " +
      "(define-fun Fuel () Bool\n    true)\n  " +
      "(define-fun cond2 () Bool\n    false)\n  " +
      "(define-fun cond1 () Bool\n    true)\n  " +
      "(define-fun satisfiable_name1 () Bool\n    true)\n  " +
      "(define-fun fire_score () Real\n    (/ 147.0 2000.0))\n  (define-fun Oxygen () Bool\n    true)\n  (define-fun oxygen_score () Real\n    1.0)\n  (define-fun walnut () Bool\n    false)\n  (define-fun Ignition () Bool\n    true)\n  (define-fun cond4 () Bool\n    false)\n  (define-fun gas_stove () Bool\n    true)\n  (define-fun coal () Bool\n    true)\n  (define-fun electrical_sparc () Bool\n    true)\n  (define-fun fuel_score () Real\n    (/ 21.0 100.0))\n  (define-fun birch () Bool\n    false)\n  (define-fun matches () Bool\n    true)\n  (define-fun coal_cond () Bool\n    true)\n  (define-fun gasoline () Bool\n    true)\n)"
//    ConsoleLogger.log(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    println(out.text)
    out.text should be("fire = * (([Fuel Ignition Oxygen] 0.0735)) default 1" +
      "fuel = + (([gasoline coal wood] 0.21)) default 0" +
      "ignition = + (([matches gas_stove electrical_sparc] 0.35)) default 0" +
      "oxygen = + () default 1" +
      "pr_wood = min () default 0.09")
  }

  @Test
  def testBrokenDueToMissingDefinition() {
    val input = "POLICIES\nb0 = min ((q14 0*vl) (q0 0.7850)) default 0.8919\nb1 = * ((q6 0.6819) (q7 0.7271)) default 0.5390\nb2 = * ((q12 0.3504) (q0 0.4032)) default 3*vp\nb3 = + ((q3 0.3078) (q7 0.1332)) default 0.7163\nb4 = max ((q3 0.0948 [-0.1435,0.4347]) (q11 0.1327)) default 0.8418\nb5 = * ((q3 0.3235) (q9 2*v0 [-0.3561,0.7747])) default 0*vy\nb6 = max ((q14 0.5613) (q4 0.6564)) default 0.6351\nb7 = + ((q8 vx) (q2 0.9709)) default 0.5696 [-0.8854,0.0560]\nb8 = * ((q14 0.7031) (q5 0.6469)) default 0.3753\nb9 = min ((q11 0.3598) (q7 0.4311)) default 0.0868\nb10 = * ((q10 0.1041) (q12 0.6119)) default 0.8983\nb11 = + ((q1 0.4650) (q4 0.6019)) default 0.3478\nb12 = min ((q3 3*vu [-0.2797,0.6717]) (q9 2*vu)) default 0.0223\nb13 = max ((q5 0.9802) (q2 0.9236)) default 0.8039\nb14 = min ((q6 2*vk) (q13 0.4516)) default 2*vl\nb15 = max ((q13 0.3230) (q12 0.9485)) default 0.6186\nb16 = + ((q1 0.8562 [-0.9047,0.6472]) (q11 3*vb)) default 0.6468\nb17 = + ((q4 2*vl) (q11 0.8956)) default 0.5290\nb18 = min ((q6 0.0261) (q4 0.8287)) default 0.9865\nb19 = max ((q0 0.0663) (q10 0.5418)) default 0.7368\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np8_9 = min(b8,b9)\np10_11 = min(b10,b11)\np12_13 = min(b12,b13)\np14_15 = min(b14,b15)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np8_11 = max(p8_9,p10_11)\np12_15 = max(p12_13,p14_15)\np0_7 = +(p0_3,p4_7)\np8_15 = +(p8_11,p12_15)\np0_15 = *(p0_7,p8_15)\np16_17 = min(b16, b17)\np18_19 = +(b18, b19)\np0_15_0 = min(p0_15,p16_17)\np0_15_1 = max(p0_15_0,p18_19)\nCONDITIONS\ncond1 = 0.50 < p0_15_1\ncond2 = 0.60 < p0_15_1\nANALYSES\nanalysis1 = always_false? cond1\nanalysis2 = always_false? cond2"
    val model = "Result of analysis [analysis1 = always_false? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    false)\n  (define-fun b12_q3_U () Real\n    (- (/ 2797.0 10000.0)))\n  (define-fun b1_score () Real\n    (/ 539.0 1000.0))\n  (define-fun b14_score () Real\n    (/ 1897.0 2000.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b16_q1_U () Real\n    (- (/ 9047.0 10000.0)))\n  (define-fun b3_score () Real\n    (/ 7163.0 10000.0))\n  (define-fun v0 () Real\n    (/ 21602091.0 200000000.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q9 () Bool\n    true)\n  (define-fun vl () Real\n    (/ 1897.0 4000.0))\n  (define-fun vb () Real\n    (/ 26065697.0 50000000.0))\n  (define-fun b7_default_U () Real\n    (- (/ 4427.0 5000.0)))\n  (define-fun q3 () Bool\n    false)\n  (define-fun b8_score () Real\n    (/ 3753.0 10000.0))\n  (define-fun b2_score () Real\n    (/ 219.0 625.0))\n  (define-fun b4_q3_U () Real\n    (/ 379.0 10000.0))\n  (define-fun b12_score () Real\n    (/ 223.0 10000.0))\n  (define-fun q12 () Bool\n    true)\n  (define-fun q6 () Bool\n    false)\n  (define-fun b18_score () Real\n    (/ 8287.0 10000.0))\n  (define-fun b11_score () Real\n    (/ 6019.0 10000.0))\n  (define-fun b4_score () Real\n    (/ 1327.0 10000.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 78197091.0 50000000.0))\n  (define-fun b6_score () Real\n    (/ 1641.0 2500.0))\n  (define-fun q8 () Bool\n    true)\n  (define-fun b19_score () Real\n    (/ 2709.0 5000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    (/ 8919.0 10000.0))\n  (define-fun q10 () Bool\n    true)\n  (define-fun q14 () Bool\n    false)\n  (define-fun b7_score () Real\n    (/ 1641.0 2500.0))\n  (define-fun b15_score () Real\n    (/ 1897.0 2000.0))\n  (define-fun b17_score () Real\n    (/ 18441.0 10000.0))\n  (define-fun vx () Real\n    (/ 1641.0 2500.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b10_score () Real\n    (/ 6369879.0 100000000.0))\n  (define-fun b13_score () Real\n    (/ 8039.0 10000.0))\n  (define-fun vu () Real\n    (/ 223.0 20000.0))\n  (define-fun always_false_analysis1 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun b9_score () Real\n    (/ 1799.0 5000.0))\n  (define-fun b5_score () Real\n    (- (/ 14007909.0 100000000.0)))\n  (define-fun b5_q9_U () Real\n    (- (/ 3561.0 10000.0)))\n)\nResult of analysis [analysis2 = always_false? cond2]:\nsat\n(model \n  (define-fun q0 () Bool\n    false)\n  (define-fun q13 () Bool\n    false)\n  (define-fun b12_q3_U () Real\n    (- (/ 2797.0 10000.0)))\n  (define-fun b1_score () Real\n    (/ 539.0 1000.0))\n  (define-fun b14_score () Real\n    (/ 1897.0 2000.0))\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun b16_q1_U () Real\n    (- (/ 9047.0 10000.0)))\n  (define-fun b3_score () Real\n    (/ 7163.0 10000.0))\n  (define-fun v0 () Real\n    (/ 21602091.0 200000000.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun q9 () Bool\n    true)\n  (define-fun vl () Real\n    (/ 1897.0 4000.0))\n  (define-fun vb () Real\n    (/ 26065697.0 50000000.0))\n  (define-fun b7_default_U () Real\n    (- (/ 4427.0 5000.0)))\n  (define-fun q3 () Bool\n    false)\n  (define-fun b8_score () Real\n    (/ 3753.0 10000.0))\n  (define-fun b2_score () Real\n    (/ 219.0 625.0))\n  (define-fun b4_q3_U () Real\n    (/ 379.0 10000.0))\n  (define-fun b12_score () Real\n    (/ 223.0 10000.0))\n  (define-fun q12 () Bool\n    true)\n  (define-fun q6 () Bool\n    false)\n  (define-fun b18_score () Real\n    (/ 8287.0 10000.0))\n  (define-fun b11_score () Real\n    (/ 6019.0 10000.0))\n  (define-fun b4_score () Real\n    (/ 1327.0 10000.0))\n  (define-fun q7 () Bool\n    false)\n  (define-fun b16_score () Real\n    (/ 78197091.0 50000000.0))\n  (define-fun b6_score () Real\n    (/ 1641.0 2500.0))\n  (define-fun q8 () Bool\n    true)\n  (define-fun b19_score () Real\n    (/ 2709.0 5000.0))\n  (define-fun q11 () Bool\n    true)\n  (define-fun b0_score () Real\n    (/ 8919.0 10000.0))\n  (define-fun q10 () Bool\n    true)\n  (define-fun q14 () Bool\n    false)\n  (define-fun b7_score () Real\n    (/ 1641.0 2500.0))\n  (define-fun b15_score () Real\n    (/ 1897.0 2000.0))\n  (define-fun b17_score () Real\n    (/ 18441.0 10000.0))\n  (define-fun vx () Real\n    (/ 1641.0 2500.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b10_score () Real\n    (/ 6369879.0 100000000.0))\n  (define-fun b13_score () Real\n    (/ 8039.0 10000.0))\n  (define-fun vu () Real\n    (/ 223.0 20000.0))\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_false_analysis2 () Bool\n    true)\n  (define-fun b9_score () Real\n    (/ 1799.0 5000.0))\n  (define-fun b5_q9_U () Real\n    (- (/ 3561.0 10000.0)))\n  (define-fun b5_score () Real\n    (- (/ 14007909.0 100000000.0)))\n)"
    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis1")
    println(out.text)
    out.text should be("b0 = min () default 0.8919" +
      "b1 = * () default 0.539" +
      "b10 = * (([q10 q12] 0.06369879)) default 0.8983" +
      "b11 = + (([q4] 0.6019)) default 0.3478" +
      "b12 = min (([q9] 0.0223)) default 0.0223" +
      "b13 = max () default 0.8039b14 = min () default 0.9485" +
      "b15 = max (([q12] 0.9485)) default 0.6186" +
      "b16 = + (([q11] 1.56394182)) default 0.6468" +
      "b17 = + (([q4 q11] 1.8441)) default 0.529" +
      "b18 = min (([q4] 0.8287)) default 0.9865" +
      "b19 = max (([q10] 0.5418)) default 0.7368" +
      "b2 = * (([q12] 0.3504)) default ???" +
      "b3 = + () default 0.7163" +
      "b4 = max (([q11] 0.1327)) default 0.8418" +
      "b5 = * (([q9] -0.14007909)) default 0" +
      "b6 = max (([q4] 0.6564)) default 0.6351" +
      "b7 = + (([q8] 0.6564)) default -0.3158" +
      "b8 = * () default 0.3753" +
      "b9 = min (([q11] 0.3598)) default 0.0868")

  }

  @Test
  def testRecursiveAnalysisByScoresQ5True() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = + ((q0 0.5)(q1 0.5) ) default 0\nb2 = + ((q3 0.5)(q4 0.5) ) default 0\nb3 = + ((q5 b2_score)) default 0.9\nPOLICY_SETS\npSet = b3\nCONDITIONS\ncond = 0.5 < pSet\nANALYSES\nanalysis = always_true? cond"
    val model = "Result of analysis [analysis = always_true? cond]:\nsat\n(model \n  (define-fun q5 () Bool\n    true)\n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q4 () Bool\n    false)\n  (define-fun always_true_analysis () Bool\n    false)\n  (define-fun cond () Bool\n    false)\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun b2_score () Real\n    0.0)\n  (define-fun q1 () Bool\n    true)\n)"
    ConsoleLogger.log(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis")
    ConsoleLogger.log(out.text)
    out.text should be("b2 = + () default 0b3 = + (([q5] 0)) default 0.9")
  }

  @Test
  def testRecursiveAnalysisByScores() {
    ConsoleLogger.enable()
    val input = "POLICIES\nb1 = + ((q0 0.5)(q1 0.5) ) default 0\nb2 = + ((q3 0.5)(q4 0.5) ) default 0\nb3 = + ((q5 b2_score)) default 0\nPOLICY_SETS\npSet = b3\nCONDITIONS\ncond = 0.5 < pSet\nANALYSES\nanalysis = always_true? cond\nb1 = + ((q0 0.5)(q1 0.5) ) default 0\nb2 = + ((q3 0.5)(q4 0.5) ) default 0\nb3 = + () default b1_score + b2_score\nPOLICY_SETS\npSet = b3\nCONDITIONS\ncond = 0.5 < pSet\nANALYSES\nanalysis = always_true? cond"
    val model = "Result of analysis [analysis = always_true? cond]:\nsat\n(model \n  (define-fun q5 () Bool\n    false)\n  (define-fun q0 () Bool\n    false)\n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun q4 () Bool\n    false)\n  (define-fun always_true_analysis () Bool\n    false)\n  (define-fun cond () Bool\n    false)\n  (define-fun b3_score () Real\n    0.0)\n  (define-fun b2_score () Real\n    0.0)\n  (define-fun q1 () Bool\n    true)\n)"
    ConsoleLogger.log(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis")
    ConsoleLogger.log(out.text)
    out.text should be("b3 = + () default 0")
  }

  @Test
  def testMultipleRecursiveAnalysisByDefaultScore() {
    ConsoleLogger.enable()
    val input = "POLICIES\nb1 = + ((q0 0.5)(q1 0.5) ) default 0\nb2 = + ((q3 0.5)(q4 0.5) ) default 0\nb3 = + () default b1_score + b2_score\nPOLICY_SETS\npSet = b3\nCONDITIONS\ncond = 0.5 < pSet\nANALYSES\nanalysis = always_true? cond"
    val model = "Result of analysis [analysis = always_true? cond]:\nsat\n(model \n  (define-fun q0 () Bool\n    false)\n  (define-fun b1_score () Real\n    0.0)\n  (define-fun q3 () Bool\n    false)\n  (define-fun q4 () Bool\n    true)\n  (define-fun always_true_analysis () Bool\n    false)\n  (define-fun cond () Bool\n    false)\n  (define-fun b3_score () Real\n    (/ 1.0 2.0))\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun q1 () Bool\n    false)\n)"
    ConsoleLogger.log(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis")
    ConsoleLogger.log(out.text)
    out.text should be("b1 = + () default 0b2 = + (([q4] 0.5)) default 0b3 = + () default 0.5")
  }

  @Test
  def testRecursiveAnalysisByDefaultScore() {
    ConsoleLogger.enable()
    val input = "POLICIES\nb1 = + ((q0 0.5)(q1 0.5) ) default 0\nb2 = + () default b1_score\nPOLICY_SETS\npSet = b2\nCONDITIONS\ncond = 0.5 < pSet\nANALYSES\nanalysis = always_true? cond"
    val model = "Result of analysis [analysis = always_true? cond]:\nsat\n(model \n  (define-fun cond () Bool\n    false)\n  (define-fun q0 () Bool\n    false)\n  (define-fun b2_score () Real\n    (/ 1.0 2.0))\n  (define-fun b1_score () Real\n    (/ 1.0 2.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_analysis () Bool\n    false)\n)"

    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis")
    out.text should be("b1 = + (([q1] 0.5)) default 0b2 = + () default 0.5")
  }

  @Test
  def testPredicateCondition() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nfuel = +((gasoline 0.1) (coal 0.02) (wood 0.09)) default 0\nignition = +((matches 0.2) (gas_stove 0.1) (electrical_sparc 0.05)) default 0\noxygen = +() default 1\nfire = *((True fuel_score) (True ignition_score) (True oxygen_score)) default 1\nPOLICY_SETS\npSet1 = fire\nCONDITIONS\ncond45 = coal\nANALYSES\nname3 = satisfiable? cond45"
    val model = "Result of analysis [name3 = satisfiable? cond45]:\nsat\n(model \n  (define-fun satisfiable_name3 () Bool\n    true)\n  (define-fun ignition_score () Real\n    (/ 7.0 20.0))\n  (define-fun gas_stove () Bool\n    true)\n  (define-fun wood () Bool\n    true)\n  (define-fun cond45 () Bool\n    true)\n  (define-fun coal () Bool\n    true)\n  (define-fun fire_score () Real\n    (/ 147.0 2000.0))\n  (define-fun electrical_sparc () Bool\n    true)\n  (define-fun True () Bool\n    true)\n  (define-fun matches () Bool\n    true)\n  (define-fun fuel_score () Real\n    (/ 21.0 100.0))\n  (define-fun oxygen_score () Real\n    1.0)\n  (define-fun gasoline () Bool\n    true)\n)"

    val out = new PolicySpecialisationMaker(input).doIt(model, "name3")
    out.text should be("") //no policy involved
  }

  @Test
  def testAndCondition() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nfuel = +((gasoline 0.1) (coal 0.02) (wood 0.09)) default 0\nignition = +((matches 0.2) (gas_stove 0.1) (electrical_sparc 0.05)) default 0\noxygen = +() default 1\nfire = *((True fuel_score) (True ignition_score) (True oxygen_score)) default 1\nPOLICY_SETS\npSet1 = fuel\nCONDITIONS\ncond3 = pSet1 <= 0.0735\ncond45 = coal\ncond4 = cond45 && cond3\nDOMAIN_SPECIFICS\n(assert True)\nANALYSES\nname3 = satisfiable? cond4"
    val model = "Result of analysis [name3 = satisfiable? cond4]:\nsat\n(model \n  (define-fun ignition_score () Real\n    (/ 1.0 4.0))\n  (define-fun cond3 () Bool\n    true)\n  (define-fun wood () Bool\n    false)\n  (define-fun fire_score () Real\n    (/ 1.0 200.0))\n  (define-fun True () Bool\n    true)\n  (define-fun oxygen_score () Real\n    1.0)\n  (define-fun satisfiable_name3 () Bool\n    true)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun gas_stove () Bool\n    false)\n  (define-fun coal () Bool\n    true)\n  (define-fun cond45 () Bool\n    true)\n  (define-fun electrical_sparc () Bool\n    true)\n  (define-fun fuel_score () Real\n    (/ 1.0 50.0))\n  (define-fun matches () Bool\n    true)\n  (define-fun gasoline () Bool\n    false)\n)"
    val out = new PolicySpecialisationMaker(input).doIt(model, "name3")
    ConsoleLogger.log1(out.text)
    out.text should be("fuel = + (([coal] 0.02)) default 0")
  }

  @Test
  def testOrCondition() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nfuel = +((gasoline 0.1) (coal 0.02) (wood 0.09)) default 0\nignition = +((matches 0.2) (gas_stove 0.1) (electrical_sparc 0.05)) default 0\noxygen = +() default 1\nfire = *((True fuel_score) (True ignition_score) (True oxygen_score)) default 1\nPOLICY_SETS\npSet1 = fuel\nCONDITIONS\ncond3 = pSet1 <= 0.0735\ncond45 = coal\ncond4 = cond45 || cond3\nDOMAIN_SPECIFICS\n(assert True)\nANALYSES\nname3 = satisfiable? cond4"
    val model = "Result of analysis [name3 = satisfiable? cond4]:\nsat\n(model \n  (define-fun ignition_score () Real\n    0.0)\n  (define-fun cond3 () Bool\n    false)\n  (define-fun wood () Bool\n    true)\n  (define-fun fire_score () Real\n    0.0)\n  (define-fun True () Bool\n    true)\n  (define-fun oxygen_score () Real\n    1.0)\n  (define-fun satisfiable_name3 () Bool\n    true)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun gas_stove () Bool\n    false)\n  (define-fun coal () Bool\n    true)\n  (define-fun cond45 () Bool\n    true)\n  (define-fun electrical_sparc () Bool\n    false)\n  (define-fun fuel_score () Real\n    (/ 21.0 100.0))\n  (define-fun matches () Bool\n    false)\n  (define-fun gasoline () Bool\n    true)\n)"
    val out = new PolicySpecialisationMaker(input).doIt(model, "name3")
    ConsoleLogger.log1(out.text)
    out.text should be("fuel = + (([gasoline coal wood] 0.21)) default 0")
  }

  @Test
  def testNotCondition() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nfuel = +((gasoline 0.1) (coal 0.02) (wood 0.09)) default 0\nignition = +((matches 0.2) (gas_stove 0.1) (electrical_sparc 0.05)) default 0\noxygen = +() default 1\nfire = *((True fuel_score) (True ignition_score) (True oxygen_score)) default 1\nPOLICY_SETS\npSet1 = fuel\nCONDITIONS\ncond45 = coal\ncond4 = !cond45 \nDOMAIN_SPECIFICS\n(assert True)\nANALYSES\nname3 = satisfiable? cond4"
    val model = "Result of analysis [name3 = satisfiable? cond4]:\nsat\n(model \n  (define-fun gasoline () Bool\n    true)\n  (define-fun wood () Bool\n    true)\n  (define-fun fire_score () Real\n    (/ 133.0 2000.0))\n  (define-fun True () Bool\n    true)\n  (define-fun oxygen_score () Real\n    1.0)\n  (define-fun satisfiable_name3 () Bool\n    true)\n  (define-fun cond4 () Bool\n    true)\n  (define-fun gas_stove () Bool\n    true)\n  (define-fun coal () Bool\n    false)\n  (define-fun cond45 () Bool\n    false)\n  (define-fun electrical_sparc () Bool\n    true)\n  (define-fun fuel_score () Real\n    (/ 19.0 100.0))\n  (define-fun matches () Bool\n    true)\n  (define-fun ignition_score () Real\n    (/ 7.0 20.0))\n)"
    val out = new PolicySpecialisationMaker(input).doIt(model, "name3")
    ConsoleLogger.log1(out.text)
    out.text should be("")
  }

  @Test
  def testSomeBottomPredicates() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = max ((isLuxuryCar 150000) (isSedan 60000) (isCompact 30000)) default 50000\nb2 = min ((hasUSLicense 0.9) (hasUKLicense 0.6) (hasEULicense 0.7) (hasOtherLicense 0.4 [-0.1,0.1])) default 0\nb3 = max ((someOffRoadDriving 0.8) (onlyCityUsage 0.4) (onlyLongDistanceUsage 0.2) (mixedUsage 0.25)) default 0.3\nb4 = + ((accidentFreeForYears 0.05*x) (speaksEnglish 0.05) (travelsAlone -0.2) (femaleDriver 0.1)) default 0\nb_minOne = + () default -1\nPOLICY_SETS\npSet0 = +(b2,b_minOne)\npSet1 = *(b1,pSet0)\npSet_b4 = b4\nCONDITIONS\ncond1 = pSet1 <= 50000\ncond2 = 0.4 < pSet_b4\ncond3 = cond1 && cond2\ncond4 = 0.6 < pSet_b4\ncond5 = cond1 && cond4\nDOMAIN_SPECIFICS\n(assert (and (<= 0 x) (<= x 10)))\n(assert (or (not isLuxuryCar) (not someOffRoadDriving)))\n(assert (and (implies isLuxuryCar (and (not isSedan) (not isCompact))) (implies isSedan (and (not isLuxuryCar) (not isCompact))) (implies isCompact (and (not isSedan) (not isLuxuryCar)))))\n(assert (implies onlyCityUsage (not mixedUsage)))\n(assert (implies onlyLongDistanceUsage (not mixedUsage)))\n(assert (implies onlyCityUsage (not someOffRoadDriving)))\n(assert (implies onlyLongDistanceUsage (not someOffRoadDriving)))\nANALYSES\nname1 = satisfiable? cond3"
    val model = "Result of analysis [name1 = satisfiable? cond3]:\nsat\n(model \n  (define-fun onlyLongDistanceUsage () Bool\n    false)\n  (define-fun speaksEnglish () Bool\n    true)\n  (define-fun isLuxuryCar () Bool\n    false)\n  (define-fun cond3 () Bool\n    true)\n  (define-fun cond5 () Bool\n    false)\n  (define-fun isCompact () Bool\n    false)\n  (define-fun hasOtherLicense () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun satisfiable_name1 () Bool\n    true)\n  (define-fun onlyCityUsage () Bool\n    false)\n  (define-fun b_minOne_score () Real\n    (- 1.0))\n  (define-fun b2_hasOtherLicense_U () Real\n    0.0)\n  (define-fun b1_score () Real\n    50000.0)\n  (define-fun b3_score () Real\n    (/ 1.0 4.0))\n  (define-fun isSedan () Bool\n    false)\n  (define-fun travelsAlone () Bool\n    true)\n  (define-fun cond4 () Bool\n    false)\n  (define-fun accidentFreeForYears () Bool\n    true)\n  (define-fun mixedUsage () Bool\n    true)\n  (define-fun femaleDriver () Bool\n    true)\n  (define-fun b2_score () Real\n    (/ 2.0 5.0))\n  (define-fun someOffRoadDriving () Bool\n    false)\n  (define-fun x () Real\n    10.0)\n  (define-fun b4_score () Real\n    (/ 9.0 20.0))\n)"

    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1", Set("hasUSLicense", "hasUKLicense", "hasEULicense"))
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = max () default 50000" +
      "b2 = min (([hasOtherLicense] 0.4)) default 0" +
      "b4 = + (([accidentFreeForYears speaksEnglish travelsAlone femaleDriver] 0.45)) default 0" +
      "b_minOne = + () default -1")
  }

  @Test
  def testCanOutputAnalysisAllRulesFalse() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"

    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = min () default 1")
  }

  @Test
  def testCanOutputAnalysisOneRuleTrueOneFalseMin() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = min ((q1 0.6) (q2 0.4)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = min (([q1] 0.6)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisOneRuleTrueOneBottomMax() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = max ((q1 0.8) (q2 0.8)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.65\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = max (([q1] 0.8) (q2? 0.8)) default 0")
  }

  @Test
  def testCanOutputAnalysisBothRulesTruePlus() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = + ((q1 0.5) (q2 0.1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = + (([q1 q2] 0.6)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisBothRulesTrueMul() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 0.8) (q2 0.8)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.65 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun q2 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = * (([q1 q2] 0.64)) default 0")
  }

  @Test
  def testCanOutputAnalysisOneRuleNotDefinedMul() {
    ConsoleLogger.enable(2)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = always_true? cond1"
    val model = "Result of analysis [name1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun q1 () Bool\n    true)\n  (define-fun always_true_name1 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = * (([q1] 1) (q2? 1)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisAlwaysFalse() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = always_false? cond1"
    val model = "Result of analysis [name1 = always_false? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun always_false_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = * () default 0.5")
  }

  @Test
  def testCanOutputAnalysisSatisfiable() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.9\nANALYSES\nname1 = satisfiable? cond1"
    val model = "Result of analysis [name1 = satisfiable? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun satisfiable_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q2 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = * () default 0.5")
  }

  @Test
  def testCanOutputAnalysisDifferent() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nb2 = * ((q3 1) (q4 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.9\ncond2 = pSet2 <= 0.8\nANALYSES\nname1 = different? cond1 cond2"
    val model = "Result of analysis [name1 = different? cond1 cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun different_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = * () default 0.5b2 = * (([q3] 1) (q4? 1)) default 0.5")
  }

  @Test
  def testCanOutputAnalysisImplies() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = * ((q1 1) (q2 1)) default 0.5\nb2 = * ((q3 1) (q4 1)) default 0.5\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.9\ncond2 = pSet2 <= 0.8\nANALYSES\nname1 = implies? cond1 cond2"
    val model = "Result of analysis [name1 = implies? cond1 cond2]:\nsat\n(model \n  (define-fun cond1 () Bool\n    true)\n  (define-fun implies_name1 () Bool\n    true)\n  (define-fun q1 () Bool\n    false)\n  (define-fun q3 () Bool\n    true)\n  (define-fun q2 () Bool\n    false)\n  (define-fun cond2 () Bool\n    false)\n)"
    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = * () default 0.5b2 = * (([q3] 1) (q4? 1)) default 0.5")
  }

  @Test
  def testCanAnalyseExtended() {
//    ConsoleLogger.enable(1)
    val input = "POLICIES\nb0 = * ((q5 0.9880)) default 0.6085\nb1 = min ((q3 0.6166) (q4 0.9412) (q5 0.2340) (q0 2*vb)) default 0.1661\nb2 = max ((q5 0.1117) (q3 0.1726) (q4 3*vi [-0.9282,0.0551])) default 0.2029\nb3 = + ((q2 0.8261) (q1 0.3104)) default 2*vt\nb4 = min ((q0 0.8647) (q5 2*v5) (q2 0.0736) (q4 0.1695)) default 0.9265\nb5 = max ((q2 0.1412) (q0 0.2796) (q4 0.3937)) default 2*vs\nb6 = + ((q2 0.2751 [-0.6356,0.6508]) (q3 3*v7 [-0.5388,0.2106])) default 0.2814 [-0.1182,0.4017]\nb7 = * ((q2 0.5470)) default 0.6631\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\np0_7 = +(p0_3,p4_7)\n\nCONDITIONS\ncond1 = 0.50 < p0_7\ncond2 = 0.60 < p0_7\nANALYSES\nanalysis1 = always_true? cond1"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    (/ 421.0 1250.0))\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun z3name!12 () Real\n    0.0)\n  (define-fun q4 () Bool\n    true)\n  (define-fun cond2 () Bool\n    false)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b3_score () Real\n    (/ 8261.0 10000.0))\n  (define-fun vb () Real\n    (/ 421.0 2500.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun z3name!1 () Real\n    (/ 421.0 1250.0))\n  (define-fun z3name!6 () Real\n    (/ 1217.0 2000.0))\n  (define-fun q3 () Bool\n    false)\n  (define-fun z3name!7 () Real\n    (/ 8261.0 10000.0))\n  (define-fun b2_score () Real\n    (/ 477.0 1250.0))\n  (define-fun z3name!2 () Real\n    (/ 477.0 1250.0))\n  (define-fun v7 () Real\n    (/ 28.0 1875.0))\n  (define-fun z3name!3 () Real\n    (/ 74.0 625.0))\n  (define-fun b4_score () Real\n    (/ 46.0 625.0))\n  (define-fun b2_q4_U () Real\n    0.0)\n  (define-fun vs () Real\n    (/ 3937.0 20000.0))\n  (define-fun z3name!0 () Real\n    (/ 477.0 1250.0))\n  (define-fun vi () Real\n    (/ 159.0 1250.0))\n  (define-fun b6_score () Real\n    (/ 74.0 625.0))\n  (define-fun v5 () Real\n    (/ 37.0 625.0))\n  (define-fun z3name!13 () Real\n    (/ 74.0 625.0))\n  (define-fun b0_score () Real\n    (/ 1217.0 2000.0))\n  (define-fun b7_score () Real\n    (/ 547.0 1000.0))\n  (define-fun z3name!10 () Real\n    (/ 547.0 1000.0))\n  (define-fun z3name!4 () Real\n    (/ 46.0 625.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b6_q2_U () Real\n    (- (/ 1567.0 10000.0)))\n  (define-fun b6_q3_U () Real\n    0.0)\n  (define-fun q2 () Bool\n    true)\n  (define-fun z3name!8 () Real\n    0.0)\n  (define-fun b5_score () Real\n    (/ 3937.0 10000.0))\n  (define-fun b6_default_U () Real\n    (- (/ 591.0 5000.0)))\n  (define-fun z3name!5 () Real\n    (/ 74.0 625.0))\n  (define-fun vt () Real\n    (/ 8261.0 20000.0))\n  (define-fun z3name!11 () Real\n    (/ 74.0 625.0))\n  (define-fun z3name!9 () Real\n    (/ 8261.0 10000.0))\n)"
//    ConsoleLogger.log(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis1")
    println(out.text)
    out.text should be("b0 = * () default 0.6085" +
      "b1 = min (([q4 q0] 0.3368)) default 0.1661" +
      "b2 = max (([q4] 0.3816)) default 0.2029" +
      "b3 = + (([q2] 0.8261)) default 0.8261" +
      "b4 = min (([q0 q2 q4] 0.0736)) default 0.9265" +
      "b5 = max (([q2 q0 q4] 0.3937)) default 0.3937" +
      "b6 = + (([q2] 0.1184)) default 0.1632" +
      "b7 = * (([q2] 0.547)) default 0.6631")
  }

  @Test
  def testCanAnalyseExtended2() {
    val input = "POLICIES\nb0 = max ((q5 0.6971) (q3 0.1338) (q2 0.3440)) default vk\nb1 = * ((q4 0.8689)) default 0*vj\nb2 = + ((q5 0.7756) (q2 0.4698)) default 0.6451\nb3 = max ((q5 0*vf) (q1 0*v0) (q3 0.1135 [-0.7627,0.3058])) default 0.9094\nb4 = + ((q2 0.4641 [-0.6645,0.1467]) (q4 0.2861 [-0.1972,0.3757])) default 0.1326\nb5 = * ((q4 0.8916)) default 0.2009\nb6 = min ((q1 0.2133) (q5 0.8770) (q3 vx) (q2 0*va)) default 0.2424\nb7 = min ((q0 0.0585) (q4 0.1866) (q1 0.9557) (q2 0.7567)) default 0.7636\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\n\nCONDITIONS\ncond1 = p4_7 < p0_3\nANALYSES\nanalysis1 = always_true? cond1\n"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    false)\n  (define-fun b1_score () Real\n    0.0)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b4_q4_U () Real\n    0.0)\n  (define-fun vk () Real\n    (/ 43.0 125.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun z3name!12 () Real\n    0.0)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b3_score () Real\n    (/ 1753.0 10000.0))\n  (define-fun q1 () Bool\n    false)\n  (define-fun z3name!1 () Real\n    0.0)\n  (define-fun z3name!6 () Real\n    0.0)\n  (define-fun q3 () Bool\n    true)\n  (define-fun z3name!7 () Real\n    0.0)\n  (define-fun b2_score () Real\n    (/ 2349.0 5000.0))\n  (define-fun z3name!2 () Real\n    (/ 1753.0 10000.0))\n  (define-fun z3name!3 () Real\n    0.0)\n  (define-fun b4_score () Real\n    (/ 1881.0 5000.0))\n  (define-fun z3name!13 () Real\n    (/ 1881.0 5000.0))\n  (define-fun z3name!0 () Real\n    (/ 1753.0 10000.0))\n  (define-fun b6_score () Real\n    0.0)\n  (define-fun b0_score () Real\n    (/ 43.0 125.0))\n  (define-fun b7_score () Real\n    (/ 7567.0 10000.0))\n  (define-fun b4_q2_U () Real\n    (- (/ 879.0 10000.0)))\n  (define-fun z3name!10 () Real\n    (/ 2009.0 10000.0))\n  (define-fun z3name!4 () Real\n    (/ 2009.0 10000.0))\n  (define-fun vx () Real\n    0.0)\n  (define-fun q5 () Bool\n    false)\n  (define-fun b3_q3_U () Real\n    (/ 309.0 5000.0))\n  (define-fun z3name!11 () Real\n    (/ 1881.0 5000.0))\n  (define-fun q2 () Bool\n    true)\n  (define-fun z3name!8 () Real\n    (/ 2349.0 5000.0))\n  (define-fun b5_score () Real\n    (/ 2009.0 10000.0))\n  (define-fun z3name!5 () Real\n    (/ 2009.0 10000.0))\n  (define-fun z3name!9 () Real\n    (/ 2349.0 5000.0))\n)"
    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis1")
    out.text should be("b0 = max (([q3 q2] 0.344)) default 0.344" +
      "b1 = * () default 0" +
      "b2 = + (([q2] 0.4698)) default 0.6451" +
      "b3 = max (([q3] 0.1753)) default 0.9094" +
      "b4 = + (([q2] 0.3762)) default 0.1326" +
      "b5 = * () default 0.2009" +
      "b6 = min (([q3 q2] 0)) default 0.2424" +
      "b7 = min (([q2] 0.7567)) default 0.7636")
  }

  @Test
  def testCanAnalyseExtended3() {
    val input = "POLICIES\nb0 = max ((q5 0.6971) (q3 0.1338) (q2 0.3440)) default vk\nb1 = * ((q4 0.8689)) default 0*vj\nb2 = + ((q5 0.7756) (q2 0.4698)) default 0.6451\nb3 = max ((q5 0*vf) (q1 0*v0) (q3 0.1135 [-0.7627,0.3058])) default 0.9094\nb4 = + ((q2 0.4641 [-0.6645,0.1467]) (q4 0.2861 [-0.1972,0.3757])) default 0.1326\nb5 = * ((q4 0.8916)) default 0.2009\nb6 = min ((q1 0.2133) (q5 0.8770) (q3 vx) (q2 0*va)) default 0.2424\nb7 = min ((q0 0.0585) (q4 0.1866) (q1 0.9557) (q2 0.7567)) default 0.7636\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np4_5 = min(b4,b5)\np6_7 = min(b6,b7)\np0_3 = max(p0_1,p2_3)\np4_7 = max(p4_5,p6_7)\n\nCONDITIONS\ncond1 = p4_7 <= p0_3\nANALYSES\nanalysis1 = always_true? cond1\n"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun q0 () Bool\n    true)\n  (define-fun b1_score () Real\n    0.0)\n  (define-fun always_true_analysis1 () Bool\n    false)\n  (define-fun b4_q4_U () Real\n    0.0)\n  (define-fun vk () Real\n    (/ 2013.0 20000.0))\n  (define-fun q4 () Bool\n    false)\n  (define-fun z3name!12 () Real\n    0.0)\n  (define-fun cond1 () Bool\n    false)\n  (define-fun b3_score () Real\n    (/ 1989.0 20000.0))\n  (define-fun q1 () Bool\n    true)\n  (define-fun z3name!1 () Real\n    (/ 663.0 5000.0))\n  (define-fun z3name!6 () Real\n    0.0)\n  (define-fun q3 () Bool\n    true)\n  (define-fun z3name!7 () Real\n    0.0)\n  (define-fun b2_score () Real\n    (/ 6451.0 10000.0))\n  (define-fun z3name!2 () Real\n    (/ 663.0 5000.0))\n  (define-fun b4_score () Real\n    (/ 663.0 5000.0))\n  (define-fun z3name!3 () Real\n    (/ 1989.0 20000.0))\n  (define-fun z3name!13 () Real\n    (/ 663.0 5000.0))\n  (define-fun z3name!0 () Real\n    (/ 117.0 2000.0))\n  (define-fun b6_score () Real\n    (/ 2133.0 10000.0))\n  (define-fun b0_score () Real\n    (/ 669.0 5000.0))\n  (define-fun b7_score () Real\n    (/ 117.0 2000.0))\n  (define-fun b4_q2_U () Real\n    (- (/ 4641.0 10000.0)))\n  (define-fun z3name!10 () Real\n    (/ 2009.0 10000.0))\n  (define-fun z3name!4 () Real\n    0.0)\n  (define-fun vx () Real\n    (/ 2133.0 10000.0))\n  (define-fun q5 () Bool\n    false)\n  (define-fun b3_q3_U () Real\n    (- (/ 281.0 20000.0)))\n  (define-fun z3name!11 () Real\n    0.0)\n  (define-fun q2 () Bool\n    false)\n  (define-fun z3name!8 () Real\n    0.0)\n  (define-fun b5_score () Real\n    (/ 2009.0 10000.0))\n  (define-fun z3name!5 () Real\n    (/ 1989.0 20000.0))\n  (define-fun z3name!9 () Real\n    (/ 6451.0 10000.0))\n)"
    val out = new PolicySpecialisationMaker(input).doIt(model, "analysis1")
    out.text should be("b0 = max (([q3] 0.1338)) default 0.10065" +
      "b1 = * () default 0" +
      "b2 = + () default 0.6451" +
      "b3 = max (([q1 q3] 0.09945)) default 0.9094" +
      "b4 = + () default 0.1326" +
      "b5 = * () default 0.2009" +
      "b6 = min (([q1 q3] 0.2133)) default 0.2424" +
      "b7 = min (([q0 q1] 0.0585)) default 0.7636")
  }

  @Test
  def testCanAnalyseExtendedOr() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = min () default 1\nb2 = min () default 1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = pSet2 <= 0.5\ncond3 = cond1 || cond2 \nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n  (define-fun cond3 () Bool\n    false)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun b2_score () Real\n    1.0)\n  (define-fun cond2 () Bool\n    false)\n)"

    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = min () default 1" +
      "b2 = min () default 1")
  }

  @Test
  def testCanAnalyseExtendedAnd() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = min () default 1\nb2 = min () default 1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = pSet2 <= 0.5\ncond3 = cond1 && cond2 \nANALYSES\nname1 = always_true? cond3"
    val model = "Result of analysis [name1 = always_true? cond3]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun always_true_name1 () Bool\n    false)\n  (define-fun cond3 () Bool\n    false)\n  (define-fun b1_score () Real\n    1.0)\n  (define-fun b2_score () Real\n    1.0)\n  (define-fun cond2 () Bool\n    false)\n)"

    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = min () default 1" +
      "b2 = min () default 1")
  }

  @Test
  def testMulPolicySet() {
    ConsoleLogger.enable(1)
    val input = "POLICIES\nb1 = max ((isLuxuryCar 150000) (isSedan 60000) (isCompact 30000)) default 50000\nb2 = min ((hasUSLicense 0.9) (hasUKLicense 0.6) (hasEULicense 0.7) (hasOtherLicense 0.4 [-0.1,0.1])) default 0\nb3 = max ((someOffRoadDriving 0.8) (onlyCityUsage 0.4) (onlyLongDistanceUsage 0.2) (mixedUsage 0.25)) default 0.3\nb4 = + ((accidentFreeForYears 0.05*x) (speaksEnglish 0.05) (travelsAlone -0.2) (femaleDriver 0.1)) default 0\nb_minOne = + () default -1\nPOLICY_SETS\npSet0 = +(b2,b_minOne)\npSet1 = *(b1,pSet0)\npSet_b4 = b4\nCONDITIONS\ncond1 = pSet1 <= 50000\ncond2 = 0.4 < pSet_b4\ncond3 = cond1 && cond2\ncond4 = 0.6 < pSet_b4\ncond5 = cond1 && cond4\nDOMAIN_SPECIFICS\n(assert (and (<= 0 x) (<= x 10)))\n(assert (or (not isLuxuryCar) (not someOffRoadDriving)))\n(assert (and (implies isLuxuryCar (and (not isSedan) (not isCompact))) (implies isSedan (and (not isLuxuryCar) (not isCompact))) (implies isCompact (and (not isSedan) (not isLuxuryCar)))))\n(assert (implies onlyCityUsage (not mixedUsage)))\n(assert (implies onlyLongDistanceUsage (not mixedUsage)))\n(assert (implies onlyCityUsage (not someOffRoadDriving)))\n(assert (implies onlyLongDistanceUsage (not someOffRoadDriving)))\nANALYSES\nname1 = satisfiable? cond3"
    val model = "Result of analysis [name1 = satisfiable? cond3]:\nsat\n(model \n  (define-fun onlyLongDistanceUsage () Bool\n    false)\n  (define-fun speaksEnglish () Bool\n    true)\n  (define-fun isLuxuryCar () Bool\n    false)\n  (define-fun cond3 () Bool\n    true)\n  (define-fun cond5 () Bool\n    false)\n  (define-fun isCompact () Bool\n    false)\n  (define-fun hasOtherLicense () Bool\n    true)\n  (define-fun cond2 () Bool\n    true)\n  (define-fun cond1 () Bool\n    true)\n  (define-fun satisfiable_name1 () Bool\n    true)\n  (define-fun onlyCityUsage () Bool\n    false)\n  (define-fun b_minOne_score () Real\n    (- 1.0))\n  (define-fun b2_hasOtherLicense_U () Real\n    0.0)\n  (define-fun b1_score () Real\n    50000.0)\n  (define-fun b3_score () Real\n    (/ 1.0 4.0))\n  (define-fun isSedan () Bool\n    false)\n  (define-fun travelsAlone () Bool\n    true)\n  (define-fun cond4 () Bool\n    false)\n  (define-fun accidentFreeForYears () Bool\n    true)\n  (define-fun mixedUsage () Bool\n    true)\n  (define-fun femaleDriver () Bool\n    true)\n  (define-fun b2_score () Real\n    (/ 2.0 5.0))\n  (define-fun someOffRoadDriving () Bool\n    false)\n  (define-fun x () Real\n    10.0)\n  (define-fun b4_score () Real\n    (/ 9.0 20.0))\n)"

    ConsoleLogger.log2(input)
    val out = new PolicySpecialisationMaker(input).doIt(model, "name1")
    ConsoleLogger.log1(out.text)
    out.text should be("b1 = max () default 50000" +
      "b2 = min (([hasOtherLicense] 0.4) (hasUSLicense? 0.9) (hasUKLicense? 0.6) (hasEULicense? 0.7)) default 0" +
      "b4 = + (([accidentFreeForYears speaksEnglish travelsAlone femaleDriver] 0.45)) default 0" +
      "b_minOne = + () default -1")
  }
}
