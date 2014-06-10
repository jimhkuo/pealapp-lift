package peal.model.util

import scala.collection.mutable.ListBuffer

object LatticeCreator {
  def createLattice(n : Int) = {
    val x = n * 4
    val l = (math.log(x) / math.log(2)).floor.toInt
    var m = math.pow(2, l).toInt
    var layer = 0
    val lattice = ListBuffer[Seq[(Int, Int)]]()
    while (m != 1) {
      if (layer == 0) {
        val lhs = for (i <- 0 until m by 2) yield i
        val rhs = for (i <- 0 until m by 2) yield i + 1
        val pairs = lhs.zip(rhs)
        lattice.append(pairs)
        m = pairs.size
      }
      else {
        val lhs = for (i <- 0 until m by 2) yield lattice(layer - 1)(i)._1
        val rhs = for (i <- 0 until m by 2) yield lattice(layer - 1)(i + 1)._2
        val pairs = lhs.zip(rhs)
        lattice.append(pairs)
        m = pairs.size
      }

      layer += 1
    }

    lattice
  }
}
