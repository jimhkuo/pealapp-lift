package peal

import org.junit.Test
import peal.domain.{Predicate, Rule, Pol}
import peal.domain.operator.Min
import org.scalatest.junit.ShouldMatchersForJUnit
import scala.collection.JavaConversions._


class ScalaTest extends ShouldMatchersForJUnit {

  @Test
  def testFor() {

    val p1 = new Pol(List(new Rule(new Predicate("q1"), 0.5), new Rule(new Predicate("q2"), 0.5), new Rule(new Predicate("q3"), 0.5)), Min, 1)
    val p2 = new Pol(List(new Rule(new Predicate("q4"), 0.5), new Rule(new Predicate("q5"), 0.5), new Rule(new Predicate("q6"), 0.5)), Min, 0)

    val pols = Map("1" -> p1, "2" -> p2)

    val q = pols.values.flatMap(pol => pol.rules.toList).map(r => r.q.name).toSeq.distinct
    val s = for (name <- q) yield name
    println(s)

    val r = for (
      pol <- pols.values;
      rule <- pol.rules
    ) yield rule

    val t = for (name <- q) yield name
    println(t)

    val r1 = for (
      pol <- pols.values;
      rules <- pol.rules;
      name <- rules.q.name // of course! it breaks name apart
    ) yield name

    println(r1)

  }

}
