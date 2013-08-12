package peal.runner.actor

import akka.actor.Actor
import peal.model.RandomModelGenerator

class ModelGeneratorActor(n: Int, m0: Int, m1: Int, m2: Int, m3: Int, k: Int, th: Double, delta: Double) extends Actor {
  def receive = {
    case Run =>
      val result = RandomModelGenerator.generate(n, m0, m1, m2, m3, k, th, delta)
      sender ! result
  }
}
