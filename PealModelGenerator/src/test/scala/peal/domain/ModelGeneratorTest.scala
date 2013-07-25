package peal.domain

import org.junit.Test
import peal.model.RandomModelGenerator

class ModelGeneratorTest {
  
  @Test
  def test() {
      RandomModelGenerator.generate(2,3,0,0,0,7,0,0)
  }

}
