package peal.domain

import org.junit.Test
import peal.model.RandomModelGenerator

class ModelGeneratorTest {
  
  @Test
  def test() {
      println(RandomModelGenerator.generate(4,5,4,3,2,7,0,0))
  }

}
