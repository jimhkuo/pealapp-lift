package peal.model.util

object LatticeCreator {

  def createLattice(n: Int) = {
    val x = n * 4
    val l = (math.log(x) / math.log(2)).floor.toInt
    val m = math.pow(2, l).toInt

    def buildLayers(m: Int, layers: List[Seq[(Int, Int)]]): List[Seq[(Int, Int)]] = {
      val pairs = layers match {
        case Nil => for (i <- 0 until m by 2) yield (i, i + 1)
        case _ => for (i <- 0 until m by 2) yield (layers.last(i)._1, layers.last(i + 1)._2)
      }

      if (pairs.size > 1) {
        buildLayers(pairs.size, layers :+ pairs)
      }
      else {
        layers :+ pairs
      }
    }

    buildLayers(m, List())
  }
}
