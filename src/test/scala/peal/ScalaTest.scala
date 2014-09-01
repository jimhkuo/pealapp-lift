package peal

import java.io.File
import java.util.ArrayList
import java.util.Arrays

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit
import peal.domain.Rational

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.sys.process._
import scala.util.Try
import scala.xml.{Elem, Node, NodeSeq}
import scala.concurrent.ExecutionContext.Implicits.global

//Test cases for me to try out Scala features, these do not test the functionalities of PEALT
class ScalaTest extends ShouldMatchersForJUnit {

  def computNum: Unit => Int = x => 100

  @Test
  def testMultiplyRationals() {
    println(Rational("0.0", "1") * Rational("0.0", "1") * Rational(0.0, 1))
  }

  @Test
  def testDoubleToString() {
    val a = BigDecimal("0.0")
    val b = BigDecimal("0.0")
    val c = a * b * b
    println(c)
  }

  def doSomething2(i:Int)= {
    Try(i)
  }

  @Test
  def testFunction() {
    //      val x :Int = computNum
    //      val y :(=> Int) = computNum

    var map = Map[Int, () => Int](1 -> (() => 1))
    //      var map1 = Map[Int, () => Int](1 -> { 1 })
  }

  @Test
  def testImplicit() {
    trait Functor[F[_]] {
      def mapY[X, Y](f: X => Y): F[X] => F[Y]
    }

    implicit object JAL_Functor extends Functor[ArrayList] {
      def mapY[X, Y](f: X => Y) = (xs: ArrayList[X]) => {
        val ys = new ArrayList[Y]
        for (i <- 0 until xs.size) ys.add(f(xs.get(i)))
        ys
      }
    }

    implicit def fops[G[_] : Functor, A](fa: G[A]): Object {val witness: Functor[G]; def mapX[B](f: (A) => B): G[B]} = new {
      val witness = implicitly[Functor[G]]

      final def mapX[B](f: A => B): G[B] = witness.mapY(f)(fa)
    }

    val testList = new ArrayList(Arrays.asList("this", "is", "a", "test"))
    println(testList.mapX(_.toUpperCase))
  }

  trait NodeAppender {
    def appendNode(node: Node): NodeSeq => NodeSeq
  }

  implicit object NodeAppenderObj extends NodeAppender {
    override def appendNode(node: Node) = (nodes: NodeSeq) => {
      nodes :+ node
    }
  }

  implicit def fops(ns: NodeSeq) = new {
    val witness = implicitly[NodeAppender]

    final def append(node: Node): NodeSeq = {
      witness.appendNode(node)(ns)
    }
  }

  object MutableNodeSeq {
    def apply(nodes: NodeSeq) = new MutableNodeSeq(nodes)
  }

  class MutableNodeSeq(var nodes: NodeSeq) {
    def append(node: Node) {
      nodes = nodes.append(node)
    }

    override def toString = nodes.toString()
  }

  @Test
  def testNodeSeq() {
    val nodes = MutableNodeSeq(<span>hi</span>)
    println(nodes)
    println(nodes.append(<span>Hi2</span>))
    println(nodes)

  }

  @Test
  def testS() {
    println(s"aa${
      if (Set(1).nonEmpty) {
        " "
      }
    }")
    println(s"bb${
      if (Set().nonEmpty) {
        " "
      }
    }")
  }

  @Test
  def testOption() {
    println(Some(2).fold("none")(_.toString + " string"))
  }


  @Test
  def testFoldFuture() {
    val f: Future[String] = Future { sys.error("f error") }
    val g: Future[String] = Future { "g" }
    val h: Future[String] = Future { sys.error("h error") }
    val out = List(f,g,h).foldLeft[Future[String]](Future(sys.error("!")))((a, block) => block fallbackTo(a))

    println(Await.result(out, Duration.Zero))
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
