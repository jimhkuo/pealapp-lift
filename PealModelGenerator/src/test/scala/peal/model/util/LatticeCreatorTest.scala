package peal.model.util

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class LatticeCreatorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanBuildLattice1Layer() {
    LatticeCreator.createLattice(1) should be (List(Vector((0,1), (2,3)), Vector((0,3))))
  }
}
