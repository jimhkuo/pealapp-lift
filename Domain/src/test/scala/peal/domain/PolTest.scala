package peal.domain

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import scala.collection.JavaConversions._
import peal.domain.operator.{Mul, Plus, Max, Min}
import scala.util.Random

class PolTest extends ShouldMatchersForJUnit {

  @Test
  def testCanOutput() {
    new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1).toString should be("min ((q1 0.5)) default 1.0")
    new Pol(List(new Rule(new Predicate("q2"), 0.1), new Rule(new Predicate("q1"), 0.5)), Max, 0.5).toString should be("max ((q2 0.1) (q1 0.5)) default 0.5")
    new Pol(List(new Rule(new Predicate("q3"), 0.5), new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q4"), 0.1)), Plus, 1).toString should be("+ ((q3 0.5) (q1 0.5) (q4 0.1)) default 1.0")
    new Pol(List(new Rule(new Predicate("q4"), 0.5)), Mul, 1).toString should be("* ((q4 0.5)) default 1.0")
  }

  @Test
  def testConstructRandomModel() {
    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q2"), 0.1), new Rule(new Predicate("q1"), 0.5)), Max, 0.5)
    val p3 = new Pol(List(new Rule(new Predicate("q3"), 0.5), new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q4"), 0.1)), Plus, 1)
    val p4 = new Pol(List(new Rule(new Predicate("q4"), 0.5)), Mul, 1)

    val list = List(p1, p2, p3, p4)
    println(list)
    val shuffled = Random.shuffle(list)
    println(shuffled)
  }

  @Test
  def testCanTakeVariable() {
    new Pol(List(new Rule(new Predicate("q1"), 0.5)), Min, new Score(Right(VariableFormula("x")), None))
  }
}
