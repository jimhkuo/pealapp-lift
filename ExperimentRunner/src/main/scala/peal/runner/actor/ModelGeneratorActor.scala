package peal.runner.actor

import akka.actor.Actor
import peal.model.RandomModelGenerator

class ModelGeneratorActor(params: String) extends Actor{
  def receive = {
    case Run =>
      val result = RandomModelGenerator.generate(params.split(Array(' ', ',')).filterNot(_ == ""): _*)
      sender ! result
  }
}
