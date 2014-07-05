package peal

import java.io.File

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit

import scala.sys.process._

class ScalaTest extends ShouldMatchersForJUnit {

  @Test
  def testS() {
      println(s"aa${if (Set(1).nonEmpty) {" "}}")
      println(s"bb${if (Set().nonEmpty) {" "}}")
  }
  @Test
  def testOption() {
      println(Some(2).fold("none")(_.toString + " string"))
  }

  @Test
  def testLeft() {
//    val m = collection.mutable.Map(1 -> 1, 2 -> 2)
//    m += (3 -> 3)
////    m = m + (3 -> 3) //not allowed
//    println(m)
//
//    var i = 10
//    i += 1
//
//    println(i)
//
    println(List[Int]().foldLeft(1)(_ + _))
  }

  @Test
  def testRound() {

    val bigDecimal: BigDecimal = BigDecimal(1.23456789)
    println(bigDecimal.setScale(2, BigDecimal.RoundingMode.HALF_UP))
  }

  @Test
  def testHead() {
    println(List("a1", "b2").head)
  }

  @Test
  def testEmptySet() {
    println(List[Int]().filter(_ < 0).forall(_ > 0))
  }

  @Test
  def testToInt() {
    println("1.0".toDouble.toInt)
  }

  @Test
  def testEmptySum() {
    List[Int]().sum should be(0)
  }

  @Test
  def testEmptyProduct() {
    List[Int]().product should be(1)
  }

  @Test
  def testList() {
    println(List(1, 2, 3).toSet)
    println(List(1, 2, 3).toSet())

  }

  @Test
  def testPlus() {
    val i = 0.06d
    val j = 0.54d
    println(i)
    println(j)
    println("%.2f".format(i + j).toDouble)
  }

  @Test
  def testFinally() {
    try {
      println("try")
      //      System.exit(-1)
      return
      //      throw new RuntimeException("error")
    }
    finally {
      println("finally")
    }
  }

  @Test
  def testProcess() {
    println("ls".!!)
    println(Process(Seq("bash", "-c", "z3 -h"), None, "PATH" -> "/Users/jkuo/tools/z3/bin").!!)
    //    println("z3".!!)
  }

  @Test
  def testFile() {
    var f = File.createTempFile("z3file", "")
    ("echo hi" #> f).!!
    println(f.getAbsolutePath)
  }


}
