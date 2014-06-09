package peal.model

import org.junit.Test

class RandomModelGeneratorTest {

  @Test
  def testRandomScore() {
    println(RandomScoreModelGenerator.generate(false, 3, 4, 3, 2, 1, 3*3, 0.5, 0.1))
  }

  @Test
  def testRandomConstant() {
    println(ConstantScoreModelGenerator.generate(false, 3, 4, 3, 2, 1, 3*3, 0.5, 0.1))
  }

  @Test
  def testRandomConstantWithDomain() {
    println(ConstantScoreModelGenerator.generate(true, 2, 4, 3, 2, 1, 3*3, 0.5, 0.1))
  }

  @Test
  def testBuildLattice() {
    val x = 16

    var n = math.pow(2, math.sqrt(x).toInt).toInt

    println(n)

    while (n != 1) {
      val base = for (i <- 0 until n by 2) yield (i)
      val base2 = for (i <- 1 until n by 2) yield (i)
      val basePairs = base.zip(base2)
      println(basePairs)

      n = basePairs.size
    }

  }

}
