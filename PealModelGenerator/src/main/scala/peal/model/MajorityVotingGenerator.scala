package peal.model

object MajorityVotingGenerator {
  def generateForCount(n: Int) = "POLICIES\nb1 = + (" +
    (for (i <- 0 until n) yield
      "(q" + i + " " + BigDecimal.valueOf(1.0 / n) + ")").mkString("") +
    " ) default 0\n" +
    "POLICY_SETS\npSet = b1\n" +
    "CONDITIONS\ncond = 0.5 < pSet\n" +
    "ANALYSES\n" +
    "analysis = always_true? cond\n"
}
