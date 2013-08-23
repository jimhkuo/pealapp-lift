package peal.runner.actor

import akka.actor.Actor
import peal.lazysynthesis.LazySynthesiser


class LazySynthesiserActor() extends Actor {

  def receive = {
    case input: String =>
      val z3Input: String = new LazySynthesiser(input).generate()
      sender ! z3Input
  }
}
