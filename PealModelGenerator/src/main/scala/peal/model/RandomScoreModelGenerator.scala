package peal.model

import scala.util.Random

object RandomScoreModelGenerator extends RandomModelGenerator {

  def generate(doDomainSpecific: Boolean, n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {

    val policies = createRandomScorePolicies(n, m0, m1, m2, m3, k)

    val pSets = createPolicySetMatrixWithAllFourOperators(n)

    val (finalPolicySet, lastBit) = lastSetsWithAllFourOperators(n, pSets.last._1)

    val conditions = createConditions(finalPolicySet, th, delta)

    val policiesAndConditions = policies + "\nPOLICY_SETS\n" + pSets.map(c => c._1 + " = " + c._2).mkString("\n") + "\n\n" + lastBit + conditions
    val domainSpecifics = if (doDomainSpecific) generateDomainSpecifics(k / 3, policiesAndConditions) else ""

    val analysis = "analysis1 = always_true? cond1\n" + "analysis2 = always_false? cond2\n" + "analysis3 = different? cond1 cond2\n"
    //    val analysis = Random.nextInt(3) match {
    //      case 0 => "analysis1 = always_true? cond1\n"
    //      case 1 => "analysis2 = always_false? cond2\n"
    //      case 2 => "analysis3 = different? cond1 cond2\n"
    //    }
    val analyses = "ANALYSES\n" + analysis

    policiesAndConditions + domainSpecifics + analyses
  }

}
