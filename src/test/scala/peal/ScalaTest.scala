package peal

import org.junit.Test
import peal.domain.{Predicate, Rule, Pol}
import peal.domain.operator.Min
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._
import scala.sys.process._
import java.io.File
import peal.antlr.util.ParserHelper

class ScalaTest extends ShouldMatchersForJUnit {

  @Test
  def test() {
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

  @Test
  def testForMap() {

    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.5), new Rule(new Predicate("q3"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q4"), 0.5), new Rule(new Predicate("q5"), 0.5), new Rule(new Predicate("q6"), 0.5)), Min, 0)
    val pols = Map("1" -> p1, "2" -> p2)

    val rules = pols.values.map(pol => pol.rules)
    println(rules)
    val flatRules = pols.values.flatMap(pol => pol.rules)
    println(flatRules)
    val q = flatRules.map(r => r.q.name).toSeq.distinct
    println(q)
    q should be(List("q1", "q2", "q3", "q4", "q5", "q6"))
  }

  @Test
  def testFor() {

    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.5), new Rule(new Predicate("q3"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q4"), 0.5), new Rule(new Predicate("q5"), 0.5), new Rule(new Predicate("q6"), 0.5)), Min, 0)
    val pols = Map("1" -> p1, "2" -> p2)

    val n = for (
      pol <- pols.values;
      rule <- pol.rules
    ) yield rule.q.name

    println(n)

    n should be(List("q1", "q2", "q3", "q4", "q5", "q6"))

    val n1 = for (
      pol <- pols.values;
      rules <- pol.rules;
      name <- rules.q.name // of course! it breaks name apart
    ) yield name

    println(n1)

  }

}
