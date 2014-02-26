package peal.trust14

import scala.io.Source
import org.junit.Test

class ReportMaker {
  val validResultLine = """([-\d]+)-([-\d]+)-.*""".r

  val source1 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-min.out", "UTF-8")
  val source2 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-max.out", "UTF-8")
  val source3 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-plus.out", "UTF-8")
  val source4 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-mul.out", "UTF-8")
  val source5 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-all.out", "UTF-8")

  @Test
  def testGenerate() {
      process(source1)
      process(source2)
      process(source3)
      process(source4)
      process(source5)
  }

  def process(source : Source) {
    val content = source.mkString
    source.close()
    computAverage(content.split("\n"))
  }

  def computAverage(inputs : Seq[String]) {
    var a1 = 0d
    var a2 = 0d
    var a3 = 0d
    var syn = 0d
    var z3 = 0d


    inputs.foreach {
      line => line match {
        case validResultLine(x, y) =>
          val tokens = line.split(",")
          a1 += tokens(3).toDouble
          a2 += tokens(5).toDouble
          a3 += tokens(7).toDouble
          a1 += tokens(9).toDouble
          a2 += tokens(11).toDouble
          a3 += tokens(13).toDouble
          a1 += tokens(15).toDouble
          a2 += tokens(17).toDouble
          a3 += tokens(19).toDouble
          a1 += tokens(21).toDouble
          a2 += tokens(23).toDouble
          a3 += tokens(25).toDouble
          a1 += tokens(27).toDouble
          a2 += tokens(29).toDouble
          a3 += tokens(31).toDouble

          syn += tokens(34).toDouble
          z3 += tokens(35).toDouble
        case _ =>
      }

    }
    println(formatTime(a1 / 5000) + " " + formatTime(a2 / 5000) + " " + formatTime(a3 / 5000) + " " + formatTime(syn / 1000) + " " + formatTime(z3 / 1000))


  }

  private def formatTime(t: Double) = {
    "%.4f".format(t)
  }

  @Test
  def testReg() {
      val validResultLine(i, j) = "10-1-10-1-1-0.5-0.1,300000,mezt,23.85,t,8.45,t,10.61,mezt,5.78,t,4.71,u,0,mezt,5.90,t,5.68,t,6.65,mezt,3.21,t,2.99,t,4.79,mezt,3.10,t,2.93,t,4.98,,0.00,139.97,20.34,0.00,0.00,0.00,0.00,0.00,0.00"
  }

}
