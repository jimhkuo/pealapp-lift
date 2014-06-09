package peal.model

import peal.domain.{Rule, Pol, Predicate}
import peal.domain.operator.Operator
import scala.util.Random
import scala.collection.JavaConversions._


trait PolGenerator {

  def createPol(op: Operator, count: Int, predicates: Seq[Predicate]): Pol = {
    val tempPredicates = Random.shuffle(predicates)
    val rules = (0 until count).map(i => new Rule(tempPredicates(i), "%.4f".format(Random.nextDouble())))
    new Pol(rules, op, "%.4f".format(Random.nextDouble()))
  }

}
