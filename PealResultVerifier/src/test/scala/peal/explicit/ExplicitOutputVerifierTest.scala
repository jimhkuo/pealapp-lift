package peal.explicit

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.synthesis.EagerSynthesiser
import peal.z3.Z3Caller
import peal.domain.{ThreeWayBoolean, PealTrue}


class ExplicitOutputVerifierTest extends ShouldMatchersForJUnit {

  @Test
  def testBrokenModelBottom() {
    val input = "POLICIES\nb0 = min () default 0.4942\nb1 = + ((q3 0.2134)) default 0.6567\nPOLICY_SETS\np0_1 = min(b0,b1)\nCONDITIONS\ncond1 = 0.50 < p0_1\nANALYSES\nanalysis1 = always_true? cond1"
    val model = "Result of analysis [analysis1 = always_true? cond1]:\nsat\n(model \n  (define-fun cond1 () Bool\n    false)\n  (define-fun always_true_analysis1 () Bool\n    false)\n)"
    new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueMaxGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1,b2)\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1"
    //    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueMinGreaterThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = min(b1,b2)\nCONDITIONS\ncond1 = 0.7 < pSet1\nANALYSES\nname1 = always_true? cond1"
    //    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueLessThanTh() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.7\nANALYSES\nname1 = always_true? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueAndConditions() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.5)) default 0.1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.7\ncond2 = 0 < pSet2\ncond3 = cond1 && cond2\nANALYSES\nname3 = always_true? cond3"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name3")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysTrueOrConditions() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.5)) default 0\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.5)) default 0.1\nPOLICY_SETS\npSet1 = b1\npSet2 = b2\nCONDITIONS\ncond1 = pSet1 <= 0.7\ncond2 = 0.1 < pSet2\ncond3 = cond1 || cond2\nANALYSES\nname3 = always_true? cond3"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name3")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyPlusAndAlwaysFalse() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testCanDealWithTwoAnalyses() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_false? cond1\nname2 = always_false? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyMin() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = pSet1 <= 0.5\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond1"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyMax() {
    val input = "POLICIES\nb1 = max ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test
  def testCanVerifyMul() {
    val input = "POLICIES\nb1 = * ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\nANALYSES\nname1 = always_true? cond1\nname2 = always_false? cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
    new ExplicitOutputVerifier(input).verifyModel(model, "name2")._1 should be(PealTrue)
  }

  @Test(expected = classOf[RuntimeException])
  def testCanDealWithUnsatCase() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.2\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.1 < pSet1\nANALYSES\nname1 = always_true? cond1"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test(expected = classOf[RuntimeException])
  def testCanDealWithUnsatCase2() {
    val input = "POLICIES\nb1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\nPOLICY_SETS\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\nCONDITIONS\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\ncond3 = cond1 && cond2\nANALYSES\nname1 = always_false? cond3"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "name1")._1 should be(PealTrue)
  }

  @Test
  def testBroken() {
    val input = "POLICIES\nb0 = * ((q0 0.9416) (q5 0.2881)) default 0.7321\nb1 = max ((q1 0.953) (q6 0.8343) (q2 0.8586) (q3 0.3076)) default 0.7995\nb2 = min ((q3 0.1408)) default 0.5171\nb3 = + ((q2 0.9152) (q4 0.9161) (q6 0.521)) default 0.9626\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")._1 should be(PealTrue)
  }

  @Test
  def testBroken2ExposeVerifierComparison() {
    val input = "POLICIES\nb0 = + ((q1509 0.859)) default 0.6553\nb1 = * ((q1060 0.1562)) default 0.4096\nb2 = min ((q360 0.0304) (q52 0.9625) (q1121 0.9237) (q268 0.7327) (q1243 0.9659) (q152 0.866) (q1137 0.6537) (q584 0.8826) (q256 0.7457) (q264 0.7741) (q679 0.6113) (q1196 0.8463) (q607 0.6644) (q38 0.5113) (q55 0.1003) (q704 0.3242) (q1339 0.1568) (q769 0.9856) (q724 0.4085) (q140 0.5619) (q447 0.2203) (q1076 0.3342) (q517 0.0033) (q654 0.136) (q1407 0.659) (q1088 0.8462) (q1191 0.9079) (q639 0.5447) (q1285 0.6944) (q1190 0.3217) (q871 0.6547) (q556 0.5688) (q341 0.6384) (q494 0.8964) (q758 0.372) (q978 0.378) (q149 0.7392) (q591 0.2599) (q226 0.4968) (q748 0.3946) (q1184 0.0201) (q600 0.6247) (q604 0.3262) (q209 0.0026) (q568 0.9144) (q122 0.7673) (q526 0.1236) (q1294 0.2883) (q807 0.7656) (q590 0.9555) (q512 0.7734) (q406 0.1353) (q194 0.8126) (q153 0.5975) (q1167 0.0463) (q446 0.1601) (q1325 0.1045) (q434 0.0094) (q350 0.3786) (q259 0.1718) (q165 0.9056) (q536 0.7791) (q1494 0.6961) (q46 0.0668) (q1057 0.5359) (q1149 0.8264) (q1514 0.2209) (q1231 0.327) (q920 0.2724) (q1289 0.408) (q712 0.3872) (q632 0.5825) (q1389 0.8847) (q399 0.994) (q775 0.9609) (q308 0.0179) (q1052 0.6174) (q1355 0.1593) (q243 0.2126) (q1433 0.7567) (q65 0.9473) (q1359 0.3715) (q245 0.5202) (q119 0.741) (q282 0.2401) (q171 0.8347) (q425 0.0953) (q1051 0.0377) (q815 0.5131) (q1398 0.3526) (q595 0.6217) (q880 0.9505) (q735 0.867) (q272 0.5121) (q314 0.4668) (q312 0.7931) (q859 0.7958) (q1142 0.3562) (q1378 0.7885) (q707 0.689) (q1151 0.5034) (q1401 0.114) (q760 0.2969) (q1506 0.3025) (q426 0.5598) (q1444 0.4882) (q1492 0.6473) (q1447 0.9762) (q1318 0.7185) (q1193 0.4599) (q1207 0.447) (q1421 0.355) (q262 0.4777) (q1438 0.565) (q948 0.6362) (q141 0.2084) (q1078 0.0217) (q1515 0.1214) (q1284 0.5686) (q495 0.8277) (q1342 0.5031) (q664 0.9403) (q1247 0.4632) (q708 0.0739) (q1200 0.2404) (q1275 0.6437) (q462 0.0211) (q901 0.8752) (q214 0.2944) (q31 0.8265) (q1104 0.9134) (q1119 0.7365) (q608 0.0612) (q1410 0.9524) (q1272 0.5302) (q158 0.6091) (q297 0.9744) (q1085 0.0526) (q493 0.8525) (q1373 0.2932) (q1266 0.1419) (q921 0.0097) (q565 0.561) (q480 0.3965) (q1533 0.7617) (q266 0.449) (q108 0.5244) (q1423 0.7575) (q178 0.4577) (q1074 0.0388) (q1516 0.6273) (q1157 0.1553) (q826 0.2572) (q1236 0.3017) (q1146 0.4903) (q721 0.075) (q1360 0.7884) (q1049 0.8804) (q502 0.0099) (q365 0.2769) (q904 0.4235) (q897 0.5664) (q97 0.8073) (q22 0.9223) (q207 0.5096) (q115 0.3002) (q1454 0.6072) (q1400 0.0538) (q424 0.5397) (q138 0.7848) (q997 0.2941) (q333 0.2954) (q594 0.096) (q745 0.3113) (q326 0.89) (q855 0.7417) (q1311 0.6688) (q635 0.8937) (q1139 0.9809) (q1303 0.1405) (q1115 0.9986) (q869 0.8305) (q451 0.1841) (q578 0.6349) (q488 0.4215) (q1084 0.3687) (q601 0.0918) (q943 0.5312) (q725 0.8045) (q443 0.2323) (q398 0.752) (q1056 0.7835) (q28 0.0526) (q1098 0.3937) (q537 0.1574) (q838 0.1457) (q1344 0.8403) (q1062 0.6195) (q1192 0.3978) (q858 0.2324) (q539 0.1741) (q357 0.9982) (q740 0.1047) (q483 0.0492) (q1061 0.6295) (q743 0.8745) (q304 0.5664) (q215 0.9094) (q387 0.6211) (q74 0.3527) (q1508 0.4222) (q803 0.0331) (q1218 0.863) (q1513 0.8099) (q896 0.162) (q1476 0.543) (q438 0.2552) (q677 0.6648) (q1018 0.7481) (q626 0.0176) (q1474 0.7938) (q378 0.9825) (q1001 0.5553) (q690 0.2371) (q302 0.7405) (q343 0.3668) (q723 0.4792) (q1123 0.2695) (q455 0.2158) (q233 0.9894) (q1427 0.5054) (q86 0.5829) (q298 0.5774) (q548 0.5358) (q562 0.2475) (q1145 0.44) (q1215 0.4368) (q1499 0.3109) (q345 0.9664) (q1346 0.4367) (q975 0.9298) (q543 0.7388) (q288 0.3632) (q628 0.6766) (q92 0.9056) (q219 0.0176) (q20 0.0032) (q1283 0.7414) (q1128 0.03) (q656 0.7433) (q557 0.5713) (q450 0.3121) (q1354 0.404) (q216 0.9071) (q715 0.288) (q1143 0.0222) (q310 0.1973) (q232 0.7847) (q1452 0.7186) (q1244 0.8862) (q987 0.9628) (q1436 0.8885) (q436 0.9007) (q991 0.4337) (q660 0.7553) (q809 0.3334) (q1465 0.1807) (q1189 0.5013) (q580 0.9139) (q213 0.6785) (q1028 0.4057) (q787 0.4892) (q1035 0.3818) (q1004 0.5504) (q330 0.1443) (q641 0.769) (q1034 0.7359) (q576 0.1282) (q789 0.4279) (q105 0.8495) (q903 0.679) (q1175 0.8758) (q405 0.0949) (q110 0.8488) (q358 0.6057) (q1440 0.7449) (q849 0.2002) (q1210 0.8009) (q593 0.9964) (q23 0.4388) (q133 0.1051) (q1164 0.3349) (q790 0.8153) (q1383 0.227) (q484 0.8056) (q1352 0.3011) (q1483 0.7937) (q1181 0.2331) (q700 0.7733) (q659 0.9939) (q504 0.464) (q893 0.0766) (q717 0.0865) (q830 0.7217) (q1102 0.0774) (q887 0.2469) (q1449 0.9093) (q1490 0.6071) (q192 0.0858) (q680 0.6079) (q1523 0.443) (q476 0.6674) (q844 0.697) (q1066 0.9907) (q89 0.3142) (q257 0.4221) (q702 0.1235) (q159 0.9169) (q1405 0.9948) (q347 0.4983) (q902 0.5362) (q657 0.1132) (q984 0.2535) (q151 0.9678) (q814 0.4078) (q354 0.6289) (q878 0.8268) (q828 0.4372) (q1113 0.8832) (q581 0.0468) (q386 0.4696) (q366 0.2347) (q452 0.0528) (q16 0.4904) (q1321 0.293) (q647 0.3885) (q587 0.5162) (q117 0.875) (q1081 0.8326) (q1009 0.4776) (q872 0.4279) (q291 0.1216) (q1254 0.6011) (q202 0.8488) (q335 0.2892) (q1082 0.8619) (q900 0.9763) (q885 0.1415) (q319 0.1161) (q1063 0.4962) (q1518 0.1494) (q935 0.5378) (q942 0.2523) (q143 0.3362) (q669 0.5172) (q293 0.6667) (q72 0.0182) (q1040 0.9643) (q865 0.3397) (q749 0.6281) (q965 0.5936) (q177 0.8698) (q624 0.7042) (q1388 0.1762) (q1106 0.0148) (q379 0.8859) (q1288 0.8384) (q56 0.7035) (q544 0.9351) (q1300 0.9447) (q754 0.5969) (q397 0.2992) (q1517 0.8441) (q363 0.1902) (q290 0.5088) (q1059 0.8239) (q946 0.7708) (q238 0.547) (q1068 0.1198) (q1197 0.6347) (q1199 0.1074) (q489 0.4277) (q136 0.4866) (q1065 0.3264) (q763 0.0791) (q524 0.381) (q25 0.5902) (q986 0.6099) (q1353 0.5615) (q204 0.1315) (q756 0.1221) (q538 0.2412) (q155 0.3979) (q985 0.6441) (q731 0.5674) (q716 0.7354) (q123 0.1085) (q605 0.5545) (q518 0.7607) (q867 0.4506) (q868 0.8943) (q722 0.5553) (q337 0.1209) (q812 0.5137) (q1124 0.143) (q1116 0.0977) (q1489 0.5861) (q24 0.9719) (q27 0.2165) (q953 0.0758) (q271 0.8654) (q21 0.5026) (q829 0.1669) (q1072 0.998) (q732 0.9965) (q1369 0.9903) (q353 0.1621) (q253 0.5167) (q416 0.3602) (q1222 0.2883) (q1531 0.8324) (q413 0.0896) (q1320 0.2382) (q709 0.5234) (q42 0.4475) (q150 0.9721) (q1058 0.7201) (q374 0.3727) (q76 0.8222) (q1069 0.7076) (q718 0.2694) (q1262 0.8445) (q1391 0.1148) (q113 0.771) (q6 0.5365) (q1162 0.2281) (q876 0.2287) (q1204 0.0484) (q1201 0.0748) (q1526 0.0444) (q734 0.3601) (q1223 0.3889) (q962 0.4554) (q705 0.0228) (q982 0.3407) (q615 0.5546) (q1010 0.9067) (q1259 0.3599) (q421 0.8193) (q1165 0.5095) (q277 0.0104) (q59 0.7726) (q1250 0.9988) (q198 0.14) (q87 0.1722) (q229 0.1489) (q571 0.5956) (q418 0.1796) (q458 0.5587) (q294 0.9216) (q822 0.9297) (q806 0.3215) (q53 0.9411) (q793 0.6404) (q529 0.5648) (q603 0.2475) (q211 0.4167) (q773 0.6057) (q609 0.7188) (q783 0.5743) (q1366 0.2974) (q1144 0.8377) (q316 0.7434) (q907 0.526) (q437 0.544) (q1299 0.1039) (q1163 0.3317) (q1496 0.4763) (q390 0.0049) (q334 0.2054) (q145 0.7538) (q91 0.3733) (q1345 0.3728) (q1463 0.2294) (q13 0.3449) (q1125 0.4595) (q1002 0.212) (q630 0.0171) (q460 0.6726) (q804 0.63) (q427 0.6303) (q1327 0.667) (q1312 0.296) (q870 0.2323) (q818 0.1668) (q275 0.4947) (q554 0.7974) (q1511 0.1073) (q687 0.2529) (q368 0.5082) (q1007 0.8124) (q1302 0.1922) (q403 0.1659) (q104 0.1161) (q989 0.1161) (q1535 0.0648) (q137 0.7375) (q620 0.4898) (q394 0.4307) (q552 0.3926) (q1328 0.2092) (q1361 0.7221)) default 0.5149\nb3 = max ((q783 0.7136)) default 0.5654\nPOLICY_SETS\np0_1 = min(b0,b1)\np2_3 = min(b2,b3)\np0_3 = max(p0_1,p2_3)\n\nCONDITIONS\ncond1 = 0.50 < p0_3\ncond2 = 0.60 < p0_3\nANALYSES\nanalysis1 = always_true? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val z3RawOutput = Z3Caller.call(z3SMTInput)
    new ExplicitOutputVerifier(input).verifyModel(z3RawOutput, "analysis1")._1 should be(PealTrue)
  }

  @Test
  def testSingleStageProducesInconclusive() {
    val input = "POLICIES\nb16 = min ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = max ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\nANALYSES\nanalysis1 = always_true? cond1"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testDifferent() {
    val input = "POLICIES\nb16 = max ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = + ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\ncond2 = p16_17 <= 0.5\nANALYSES\nanalysis1 = different? cond1 cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testEquivalent() {
    val input = "POLICIES\nb16 = max ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = + ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\ncond2 = p16_17 <= 0.5\nANALYSES\nanalysis1 = equivalent? cond1 cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testImplies() {
    val input = "POLICIES\nb16 = max ((q2 0.0046) (q6 0.5937) (q1 0.3835) (q0 0.8867)) default 0.3731\nb17 = + ((q5 0.0179) (q2 0.7602) (q1 0.2951)) default 0.1127\nPOLICY_SETS\np16_17 = min(b16, b17)\nCONDITIONS\ncond1 = 0.50 < p16_17\ncond2 = p16_17 <= 0.5\nANALYSES\nanalysis1 = implies? cond1 cond2"
    println(input)
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val verifyModel = new ExplicitOutputVerifier(input).verifyModel(model, "analysis1")
    verifyModel._1 should be(PealTrue)
    println("Modified predicates: " + verifyModel._2)
  }

  @Test
  def testSatisfiable() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = satisfiable? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()
    val model = Z3Caller.call(z3SMTInput)
    val out: (ThreeWayBoolean, Set[String]) = new ExplicitOutputVerifier(input).verifyModel(model, "name1")
    out._1 should be(PealTrue)
    println(out._2)
  }
}
