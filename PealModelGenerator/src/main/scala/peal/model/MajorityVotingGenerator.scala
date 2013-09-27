package peal.model

object MajorityVotingGenerator {
  def generateForCount(n: Int) = "b1 = + (" +
    (for (i <- 0 until n) yield
      "(q" + i + " " + BigDecimal.valueOf(1.0 / n) + ")").mkString("") +
    " ) default 0\n" +
    "pSet = b1\n" +
    "cond = 0.5 < pSet\n" +
    "ANALYSES\n" +
    "analysis = always_true? cond\n"
}
