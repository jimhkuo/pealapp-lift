package peal.trust14

import scala.io.Source
import org.junit.{Ignore, Test}
import peal.domain.z3.{SatResult, Unsat, Sat}
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.mutable.ListBuffer

class ReportMaker extends ShouldMatchersForJUnit {
  val validResultLine = """([\d]+)-([\d]+)-.*""".r
  val satToken = """m?e?z?[*]?(\d+)?t""".r
  val unsatToken = """(.*)u""".r

  val source1 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-min.out", "UTF-8")
  val source2 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-max.out", "UTF-8")
  val source3 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-plus.out", "UTF-8")
  val source4 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-mul.out", "UTF-8")
  val source5 = Source.fromURL("http://www.doc.ic.ac.uk/~hk2109/peal_results/300000-verify-exp3-1000-all.out", "UTF-8")

  @Ignore("don't always run")
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
    var syn = 0d
    var z3 = 0d

    val verificationTime = ListBuffer(0d, 0d, 0d)
    val satResultCount = ListBuffer(0, 0, 0)
    val unsatResultCount = ListBuffer(0, 0, 0)
    val maxACount = ListBuffer(0, 0, 0)

    def processAnalysis(i: Int, token: String, timing: String) {
      checkSat(token) match {
        case (Sat, r) =>
          verificationTime(i) += timing.toDouble
          satResultCount(i) += 1
          r match {
            case Some(count) if count > maxACount(i) => maxACount(i) = count
            case _ =>
          }
        case (Unsat, _) => unsatResultCount(i) += 1
        case (_, _) =>
      }
    }

    inputs.foreach {
      line => line match {
        case validResultLine(x, y) =>
          val tokens = line.split(",")

          processAnalysis(0, tokens(2), tokens(3))
          processAnalysis(1, tokens(4), tokens(5))
          processAnalysis(2, tokens(6), tokens(7))
          processAnalysis(0, tokens(8), tokens(9))
          processAnalysis(1, tokens(10), tokens(11))
          processAnalysis(2, tokens(12), tokens(13))
          processAnalysis(0, tokens(14), tokens(15))
          processAnalysis(1, tokens(16), tokens(17))
          processAnalysis(2, tokens(18), tokens(19))
          processAnalysis(0, tokens(20), tokens(21))
          processAnalysis(1, tokens(22), tokens(23))
          processAnalysis(2, tokens(24), tokens(25))
          processAnalysis(0, tokens(26), tokens(27))
          processAnalysis(1, tokens(28), tokens(29))
          processAnalysis(2, tokens(30), tokens(31))

          syn += tokens(34).toDouble
          z3 += tokens(35).toDouble
        case _ =>
      }
    }

    println(formatTime(verificationTime(0) / satResultCount(0)) + " " + satResultCount(0) + " " + unsatResultCount(0) + " " + maxACount(0) + " " + formatTime(verificationTime(1) / satResultCount(1)) + " " + satResultCount(1) + " " + unsatResultCount(1) + " " + maxACount(1) + " " + formatTime(verificationTime(2) / satResultCount(2)) + " " + satResultCount(2) + " " + unsatResultCount(2) + " " + maxACount(2) + " " + formatTime(syn / 1000) + " " + formatTime(z3 / 1000))
  }

  private def formatTime(t: Double) = {
    "%.4f".format(t)
  }

  private def checkSat(s: String): (SatResult, Option[Int]) = s match {
    case satToken(r) if r == null => (Sat, None)
    case satToken(r) => (Sat, Some(r.toInt))
    case unsatToken(_) => (Unsat, None)
  }

  @Test
  def testReg() {
    val satToken(_) = "t"
    val validResultLine(i, j) = "10-1-10-1-1-0.5-0.1,300000,mezt,23.85,t,8.45,t,10.61,mezt,5.78,t,4.71,u,0,mezt,5.90,t,5.68,t,6.65,mezt,3.21,t,2.99,t,4.79,mezt,3.10,t,2.93,t,4.98,,0.00,139.97,20.34,0.00,0.00,0.00,0.00,0.00,0.00"
    val satToken(r) = "mezt"
    val satToken(s) = "mez*11t"
    val unsatToken(_) = "mezu"
    println(r)
    println(s)
  }

  @Test
  def testCanCheckSat() {
    checkSat("mezt")._1 should be(Sat)
    checkSat("t")._1 should be(Sat)
    checkSat("mezu")._1 should be(Unsat)
  }

  @Test
  def testCanPullOutRecursionCount() {
    checkSat("mez*11t")._1 should be(Sat)
    checkSat("mez*11t")._2 should be(Some(11))
  }
}
