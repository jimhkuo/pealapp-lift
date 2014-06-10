package peal.model

object RandomScoreModelGenerator extends RandomModelGenerator {

 def generate(doDomainSpecific: Boolean, n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double): String = {

    val policies = createRandomScorePolicies(n, m0, m1, m2, m3, k)

    val pSets = createPolicySetMatrixWithAllFourOperators(n)

    val (finalPolicySet, lastBit) = lastSets(n, pSets.last._1)

    val conditions = createConditions(finalPolicySet, th, delta)

    val policiesAndConditions = policies + "\nPOLICY_SETS\n" + pSets.map(c => c._1 + " = " + c._2).mkString("\n") + "\n\n" + lastBit + conditions
    val domainSpecifics = if (doDomainSpecific) generateDomainSpecifics(k / 3, policiesAndConditions) else ""
    val analyses = "ANALYSES\nanalysis1 = always_true? cond1\nanalysis2 = always_false? cond2\nanalysis3 = different? cond1 cond2\n"

    policiesAndConditions + domainSpecifics + analyses
  }

}
