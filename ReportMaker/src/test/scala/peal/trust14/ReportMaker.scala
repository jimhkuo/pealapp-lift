package peal.trust14

import scala.io.Source
import org.junit.Test
import peal.domain.z3.{SatResult, Unsat, Sat}
import org.scalatest.junit.ShouldMatchersForJUnit

class ReportMaker extends ShouldMatchersForJUnit {
  val validResultLine = """([\d]+)-([\d]+)-.*""".r
  val satToken = """mez[*]?([\d]+)?t""".r
  val unsatToken = """(.*)u""".r

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

  private def process(source: Source) {
    val content = source.mkString
    source.close()
    computeAverage(content.split("\n"))
  }

  private def computeAverage(inputs: Seq[String]) {
    var a1 = 0d
    var sA1 = 0
    var sA2 = 0
    var sA3 = 0
    var uA1 = 0
    var uA2 = 0
    var uA3 = 0
    var numRecA1 = 0
    var numRecA2 = 0
    var numRecA3 = 0
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

  private def checkSat(s: String) : (SatResult, Option[Int]) = s match {
    case satToken(r) if r == null => (Sat, None)
    case satToken(r) => (Sat, Some(r.toInt))
    case unsatToken(_) => (Unsat, None)
  }

  @Test
  def testReg() {
    val validResultLine(i, j) = "10-1-10-1-1-0.5-0.1,300000,mezt,23.85,t,8.45,t,10.61,mezt,5.78,t,4.71,u,0,mezt,5.90,t,5.68,t,6.65,mezt,3.21,t,2.99,t,4.79,mezt,3.10,t,2.93,t,4.98,,0.00,139.97,20.34,0.00,0.00,0.00,0.00,0.00,0.00"
    val satToken(r) = "mezt"
    val satToken(s) = "mez*11t"
    val unsatToken(t) = "mezu"
    println(r)
    println(s)
  }

  @Test
  def testCanCheckSat() {
    checkSat("mezt")._1 should be(Sat)
    checkSat("mezu")._1 should be(Unsat)
  }

  @Test
  def testCanPullOutRecursionCount() {
    checkSat("mez*11t")._1 should be(Sat)
    checkSat("mez*11t")._2 should be(Some(11))
  }
}
