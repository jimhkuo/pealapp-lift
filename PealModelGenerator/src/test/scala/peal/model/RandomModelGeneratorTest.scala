package peal.model

import org.junit.Test

class RandomModelGeneratorTest {

  @Test
  def testRandomScoreAndNewLattice() {
    println(RandomScoreModelGenerator.generate(false, 6, 1, 1, 1, 1, 3*3, 0.5, 0.1))
  }

  @Test
  def testRandomConstant() {
    println(ConstantScoreModelGenerator.generate(false, 3, 4, 3, 2, 1, 3*3, 0.5, 0.1))
  }

  @Test
  def testRandomConstantWithDomain() {
    println(ConstantScoreModelGenerator.generate(true, 2, 4, 3, 2, 1, 3*3, 0.5, 0.1))
  }
}
