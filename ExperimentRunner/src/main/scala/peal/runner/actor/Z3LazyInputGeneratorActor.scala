package peal.runner.actor

import akka.actor.Actor
import z3.scala.Z3Context
import peal.lazysynthesis.LazySynthesiser


class Z3LazyInputGeneratorActor(z3: Z3Context) extends Actor {

  def receive = {
    case input: String =>
      val z3Input: String = new LazySynthesiser(z3, input).generate()
      sender ! z3Input
  }
}
