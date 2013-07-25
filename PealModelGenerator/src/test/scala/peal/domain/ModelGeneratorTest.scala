package peal.domain

import org.junit.Test
import peal.model.RandomModelGenerator

class ModelGeneratorTest {

  @Test
  def test() {
    println(RandomModelGenerator.generate(4, 5, 4, 3, 2, 7, 0, 0))
  }

  @Test
  def testBuildLattice() {
    val n = 16

    val base = for (i <- 0 until n by 2) yield (i)
    val base2 = for (i <- 1 until n by 2) yield (i)

    val basePairs = base.zip(base2)
    println(basePairs)

    val first = for (i <- 0 until basePairs.size by 2) yield(i)
    val first2 = for (i <- 1 until basePairs.size by 2) yield(i)

    val firstPairs = first.zip(first2)
    println(firstPairs)

  }

}
