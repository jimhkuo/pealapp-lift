package peal.model.util

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

class LatticeCreatorTest extends ShouldMatchersForJUnit {

  @Test
  def testCanBuildLattice1Layer() {
    LatticeCreator.createLattice(1) should be (List(Vector((0,1), (2,3)), Vector((0,3))))
  }

  @Test
  def testCanBuildLattice6Layers() {
    LatticeCreator.createLattice(6) should be (List(Vector((0,1), (2,3), (4,5), (6,7), (8,9), (10,11), (12,13), (14,15)), Vector((0,3), (4,7), (8,11), (12,15)), Vector((0,7), (8,15)), Vector((0,15))))
  }
}
