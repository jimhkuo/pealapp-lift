package peal.domain

import org.junit.Test
import peal.model.RandomModelGenerator

class ModelGeneratorTest {
  
  @Test
  def test() {
      RandomModelGenerator.generate(2,4,3,2,1,7,0,0)
  }

}
