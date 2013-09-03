package peal.runner.actor

import akka.actor.Actor
import peal.lazysynthesis.LazySynthesiser


class LazySynthesiserActor() extends Actor {

  def receive = {
    case input: String =>
      sender ! new LazySynthesiser(input).generate()
  }
}
