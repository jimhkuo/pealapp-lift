package peal.runner.actor

import akka.actor.Actor
import peal.model.RandomModelGenerator
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import scala.concurrent.Future

class Z3CallerActor extends Actor {

  private def getPealProgramParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  def receive = {
    case s: String =>
//      val pealProgrmParser = getPealProgramParser(input)
//      val z3 = MyZ3Context.is
//
//      try {
//        pealProgrmParser.program()
//        val predicateNames: Seq[String] = pealProgrmParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct
//        val constsMap = predicateNames.toSeq.distinct.map(t => (t, z3.mkBoolConst(t))).toMap
//        val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1)
//
////        (constsMap, pealProgrmParser.conds.toMap, pealProgrmParser.pSets.toMap, pealProgrmParser.analyses.toMap, domainSpecifics)


      println("received: " + s)
      sender ! s
  }
}
